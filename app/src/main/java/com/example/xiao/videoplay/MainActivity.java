package com.example.xiao.videoplay;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = (VideoView) findViewById(R.id.videoView);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
        Log.i("xc","path="+path);
        /**
         * 本地视频播放
         */
        mVideoView.setVideoPath("");

        /**
         * 网络视频播放
         */
        mVideoView.setVideoURI(Uri.parse(""));

        /**
         * 使用MediaController控制VideoView的播放
         */
        MediaController controller = new MediaController(this);

        /**
         * 建立VideoView和MediaController关联
         */
        mVideoView.setMediaController(controller);
        controller.setMediaPlayer(mVideoView);

        /**
         * 获取系统音频服务
         */
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取音乐最大音量
        int curVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//设备当前的音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,100,0); //设置音量

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //横屏
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);//移除半屏状态
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        }else{//竖屏

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//移除全屏状态
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);//设置半屏
        }

    }
}
