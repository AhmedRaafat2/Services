package com.google.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;

public class PlayService extends Service {

    String url = "https://server8.mp3quran.net/ahmad_huth/001.mp3";
    MediaPlayer player = new MediaPlayer();



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(url);
            player.prepare(); // might take long! (for buffering, etc)
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    //دى بقى بتاعة التحكم فى ان السيرفس تفضل شغالة بعد ما اقفل الابليكيشن او لا
    //عن طريق التحكم فى الفلاجز
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
//        return START_NOT_STICKY;
//        return START_REDELIVER_INTENT;
//        return START_STICKY_COMPATIBILITY;

    }
}
