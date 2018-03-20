package edu.hkust.cse.phoneAdapter.context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class ContextManagerTime extends IntentService {

    private Handler mHandler;
    private static boolean running;
    private String mTime;
    private Calendar mCal;
    private SimpleDateFormat mTimeFormat;
    private boolean mStop;

    public ContextManagerTime(String name) {
        super("ContextManagerTime");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCal=Calendar.getInstance();
        mTimeFormat=new SimpleDateFormat("HH:mm:ss");
    }

    public static boolean isRunning(){
        return ContextManagerTime.running;
    }

    @Override
    public void onDestroy() {

        stopForeground(true);
        ContextManagerTime.running = false;
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub

        while(!mStop){

            mTime=mTimeFormat.format(mCal.getTime());
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent();
                    i.setAction("edu.hkust.cse.phoneAdapter.newContext");
                    i.putExtra(ContextName.TIME,mTime);
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
}