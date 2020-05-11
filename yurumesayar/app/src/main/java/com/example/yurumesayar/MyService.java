package com.example.yurumesayar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static com.example.yurumesayar.pedometer.simpleSwitch;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MyService extends Service {
    static boolean kontrol=true;
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";
    public MyService() {

    }



    @Override
    public void onRebind(Intent intent) {

        super.onRebind(intent);
    }




    @Override
    public void onDestroy() {



        super.onDestroy();
    }



    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startForeground();
        return super.onStartCommand(intent, flags, 1);
       // return START_STICKY;
    }


    private void startForeground() {
        if(kontrol){




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "sss";
            String description = "getString(R.string.channel_description)";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);

            try{
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);}
            catch (Exception e){
                return;
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_message)

                .setContentTitle("Su içme hatırlatıcısı")
                .setContentText("Su içmeyi unutma")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Su içmeyi unutma"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

            timer.start();
    }}

    public CountDownTimer timer= new CountDownTimer(5000,20){


        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("asd",Boolean.toString(kontrol));

        }

        @Override
        public void onFinish() {

            Log.d("asdd",Boolean.toString(kontrol));

            startForeground();
        }
    }.start();



}
