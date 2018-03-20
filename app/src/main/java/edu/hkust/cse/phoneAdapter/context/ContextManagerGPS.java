package edu.hkust.cse.phoneAdapter.context;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;



public class ContextManagerGPS extends IntentService{

    private boolean mGpsAvailable;
    private String mLocation;
    private LocationManager mLocManager;
    private LocationListener mLocListener;
    private double mSpeed;
    private Handler mHandler;
    private static boolean running;
    private long mLastTime;
    private String mLastLocation;

    public ContextManagerGPS() {
        super("ContextManagerGPS");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mLocManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        mLocListener=new MyLocationListener();
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, mLocListener);
        mHandler=new Handler();
        ContextManagerGPS.running = true;
    }

    public static boolean isRunning(){
        return ContextManagerGPS.running;
    }

    @Override
    public void onDestroy() {
        try{
            mLocManager.removeUpdates(mLocListener);
        } catch(Exception e){

        }

        stopForeground(true);
        ContextManagerGPS.running = false;
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent();
                i.setAction("edu.hkust.cse.phoneAdapter.newContext");
                i.putExtra(ContextName.GPS_AVAILABLE, mGpsAvailable);
                i.putExtra(ContextName.GPS_LOCATION, mLocation);
                i.putExtra(ContextName.GPS_SPEED, mSpeed);
                sendBroadcast(i);
            }
        });
        try{
            Thread.sleep(120000);
        } catch(Exception e){
            Log.e("edu.hkust.cse.phoneAdapter.error", "Thread sleep exception");
        }
    }

    public class MyLocationListener implements LocationListener{


        @Override
        public void onLocationChanged(Location loc) {
			/*
			 * sense the current location, caculate speed, and set the current time
			 */
            mLocation=loc.getLatitude()+","+loc.getLongitude();

			/* calculate speed and update current time */
            long curTime=System.currentTimeMillis();
            if(mLastTime!=0 && mLastLocation!=null){
                int duration=(int)(curTime-mLastTime);
                mSpeed=calculateSpeed(mLastLocation, mLocation, duration);
            } else{
                mSpeed=-1;
            }

            mLastLocation=mLocation;
            mLastTime=curTime;
        }

        @Override
        public void onProviderDisabled(String arg0) {
			/* set mLocation to null, set last location to null and update last time to 0 */
            mGpsAvailable=false;
            mLocation=null;
            mLastLocation=null;
            mLastTime=0;
        }

        @Override
        public void onProviderEnabled(String arg0) {
            mGpsAvailable=true;
        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

        }

        private double calculateSpeed(String lastLoc, String curLoc, int duration){
            double d=AdaptationManager.calculateDist(lastLoc, curLoc);
            double result=d/(duration/1000.0);
            return result;
        }
    }
}
