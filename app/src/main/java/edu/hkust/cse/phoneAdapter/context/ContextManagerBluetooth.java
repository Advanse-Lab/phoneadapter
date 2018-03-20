package edu.hkust.cse.phoneAdapter.context;

import java.util.ArrayList;

import android.app.IntentService;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


public class ContextManagerBluetooth extends IntentService{

    private ArrayList<String> mBtDeviceList;
    private BluetoothAdapter mBtAdapter;
    private MyBroadcastReceiver mReceiver;
    private Handler mHandler;
    private boolean mStop;
    private static boolean running;

    public ContextManagerBluetooth(String name) {
        super("ContextManagerBluetooth");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mBtDeviceList=new ArrayList<String>();
        mHandler=new Handler();
        mBtAdapter=BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter==null){
            Toast.makeText(getApplicationContext(), "Bluetooth is not supported on this device!", Toast.LENGTH_SHORT).show();
        } else{
            mBtAdapter.enable();
        }
        mReceiver=new MyBroadcastReceiver();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("edu.hkust.cse.phoneAdapter.stopService");
        if(mBtAdapter != null){
            if(mBtAdapter.isEnabled()){
                iFilter.addAction(BluetoothDevice.ACTION_FOUND);
                iFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                iFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            }
        }
        registerReceiver(mReceiver, iFilter);
        mStop=false;
        ContextManagerBluetooth.running = true;
    }

    public static boolean isRunning(){
        return ContextManagerBluetooth.running;
    }

    @Override
    public void onDestroy() {
        try{
            unregisterReceiver(mReceiver);
        } catch(Exception e){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Failed to unregister broadcast receiver!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        stopForeground(true);
        ContextManagerBluetooth.running = false;
        super.onDestroy();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub

        while(!mStop){

            if(mBtAdapter != null){
				/* start a new thread to perform Bluetooth device search */
                if(!mBtAdapter.isEnabled()){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "enabling bt adapter.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mBtAdapter.enable();
                }
                mBtAdapter.cancelDiscovery();
                mBtAdapter.startDiscovery();
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent();
                    i.setAction("edu.hkust.cse.phoneAdapter.newContext");
                    i.putExtra(ContextName.BT_DEVICE_LIST, transformListToArray(mBtDeviceList));
                    i.putExtra(ContextName.BT_COUNT, mBtDeviceList.size());
                    sendBroadcast(i);

                }
            });
            try{
                Thread.sleep(120000);
            } catch(Exception e){
                Log.e("edu.hkust.cse.phoneAdapter.error", "Thread sleep exception");
            }
        }
    }

    private String[] transformListToArray(ArrayList<String> list){
        String[] s=new String[list.size()];
        for(int i=0;i<list.size();i++){
            s[i]=list.get(i);
        }
        return s;
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context c, Intent i) {
            String action=i.getAction();
            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
                mBtDeviceList=new ArrayList<String>();
            } else if( action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device=i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(!listContainsMac(mBtDeviceList, device.getAddress())){
                    mBtDeviceList.add(device.getAddress());
                }
            } else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
            } else if(action.equals("edu.hkust.cse.phoneAdapter.stopService")){
                mStop=true;
                stopSelf();
            } else {
            }
        }
    }

    private boolean listContainsMac(ArrayList<String> macList, String mac){
        for(int i=0;i<macList.size();i++){
            if(macList.get(i).equals(mac)){
                return true;
            }
        }
        return false;
    }
}
