package com.smilepasta.urchin.ui.demo;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.ui.common.basic.BasicFragment;
import com.smilepasta.urchin.utils.DialogUtil;
import com.smilepasta.urchin.widget.PlayPauseView;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioFragment extends BasicFragment {


    private PlayPauseView playPauseView;
    private MediaPlayer mediaPlayer;

    public AudioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        playPauseView = view.findViewById(R.id.ppv_audioview);
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
            DialogUtil.progress(mContext);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 装载完毕回调
                    DialogUtil.cancel();
                    playPauseView.setVisibility(View.VISIBLE);
                }

            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playPauseView.pause();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
}
