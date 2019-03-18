package com.smilepasta.urchin.ui.demo;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.TextBarActivity;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.widget.PlayPauseView;

import java.io.IOException;

/**
 * Author: huangxiaoming
 * Date: 2019/3/15
 * Desc: 播放音乐
 * Version: 1.0
 */
public class AudioActivity extends TextBarActivity {


    private PlayPauseView playPauseView;
    private MediaPlayer mediaPlayer;

    public AudioActivity() {
        // Required empty public constructor
    }


    private void initView() {
        playPauseView = findViewById(R.id.ppv_audioview);
        playPauseView.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            @Override
            public void play() {
                mediaPlayer.start();
            }

            @Override
            public void pause() {
                mediaPlayer.pause();
            }
        });
        initMediaplayer();
    }

    private void initMediaplayer() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://cdn.smilepasta.com/images/music/wobuzhudeta.mp3");
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();
            DialogUtil.progress(this);
            mediaPlayer.setOnPreparedListener(mp -> {
                // 装载完毕回调
                DialogUtil.cancel();
                playPauseView.setVisibility(View.VISIBLE);
            });
            mediaPlayer.setOnCompletionListener(mp -> playPauseView.pause());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void stopMediaPlayer() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            playPauseView.pause();
        }
    }

    @Override
    public void onDestroy() {
        stopMediaPlayer();
        super.onDestroy();
    }

    @Override
    protected void menuIconAction(Bundle bundle) {

    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AudioActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultConfig(R.layout.activity_demo_audio, getString(R.string.audio));

        initView();
    }
}
