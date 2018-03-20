package edu.hkust.cse.phoneAdapter.context;

import java.util.Calendar;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class ContextManagerWeekDay extends IntentService {

    private Handler mHandler;
    private static boolean running;
    private Calendar mCal;
    private boolean mStop;
    private String mWeekday;

    public ContextManagerWeekDay(String name) {
        super("ContextManagerWeekDay");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCal=Calendar.getInstance();

    }

    public static boolean isRunning(){
        return ContextManagerWeekDay.running;
    }

    @Override
    public void onDestroy() {

        stopForeground(true);
        ContextManagerWeekDay.running = false;
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub

        while(!mStop){

            mCal=Calendar.getInstance();
            switch(mCal.get(Calendar.DAY_OF_WEEK)){
                case 1:
                    mWeekday="sunday";
                    break;
                case 2:
                    mWeekday="monday";
                    break;
                case 3:
                    mWeekday="tuesday";
                    break;
                case 4:
                    mWeekday="wednesday";
                    break;
                case 5:
                    mWeekday="thursday";
                    break;
                case 6:
                    mWeekday="friday";
                    break;
                case 7:
                    mWeekday="saturday";
                    break;
                default:
                    break;
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent();
                    i.setAction("edu.hkust.cse.phoneAdapter.newContext");
                    i.putExtra(ContextName.WEEKDAY, mWeekday);
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
