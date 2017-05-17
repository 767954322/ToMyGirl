package com.mxmh.crystallization.acyivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.ImageView;
import android.media.MediaPlayer;

import com.mxmh.crystallization.R;
import com.mxmh.crystallization.utils.ImageUtils;
import com.mxmh.crystallization.utils.ResourcesUtils;
import com.mxmh.crystallization.view.SetPopWin;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.graphics.drawable.AnimationDrawable;


public class StartUpFlushActivity extends BaseActivity
        implements View.OnClickListener,
        SetPopWin.PopupWindowCallBack {

    private int DELAY_TIME = 4000;
    private int imagePosition = 0;
    private int musicPosition = 0;

    private ImageView iv_music_up;
    private ImageView iv_music_next;
    private ImageView iv_music_pause;
    private ImageView iv_set;
    private ImageView iv_imageview;

    private MediaPlayer mediaPlayer;
    private SetPopWin popWin;


    private Handler handler = new Handler() {
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ++imagePosition;

            if (imagePosition >= 12) {
                imagePosition = 1;
            }

            ImageUtils.disBigImage("drawable://"+ResourcesUtils.LOVELY_URL[imagePosition],iv_imageview);
//            iv_imageview.setImageResource(ResourcesUtils.LOVELY_URL[imagePosition]);
            handler.postDelayed(runnable, DELAY_TIME);
        }
    };


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_start_up_flush;
    }


    @Override
    protected void initView() {
        iv_imageview = (ImageView) findViewById(R.id.iv_imageview);
        iv_set = (ImageView) findViewById(R.id.iv_set);
        iv_music_next = (ImageView) findViewById(R.id.iv_music_next);
        iv_music_pause = (ImageView) findViewById(R.id.iv_music_pause);
        iv_music_up = (ImageView) findViewById(R.id.iv_music_up);
    }


    @Override
    protected void initListener() {
        super.initListener();
        iv_set.setOnClickListener(this);
        iv_music_next.setOnClickListener(this);
        iv_music_pause.setOnClickListener(this);
        iv_music_up.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        handler.postDelayed(runnable, DELAY_TIME);

        popWin = new SetPopWin(this, this);
        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer.create(this, ResourcesUtils.MUSIC_URL[musicPosition]);
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                    }
                    System.gc();
                    ++musicPosition;
                    if (musicPosition == ResourcesUtils.MUSIC_URL.length - 1) {
                        musicPosition = 0;
                    }
                    mediaPlayer.reset();
                    mediaPlayer = MediaPlayer.create(StartUpFlushActivity.this, ResourcesUtils.MUSIC_URL[musicPosition]);

                    mediaPlayer.start();
                }
            });
            mediaPlayer.start();
        }

        //TODO
//        //帧动画
//        iv_ren_move.setBackgroundResource(R.drawable.ren_move);
//        AnimationDrawable anim = (AnimationDrawable) iv_ren_move.getBackground();
//        anim.start();
//        //补间动画
//        Animation translate = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.2f,
//                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, 0);
//        translate.setDuration(4000);
//        translate.setRepeatCount(Animation.INFINITE);
//        iv_ren_move.startAnimation(translate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_set:

                if (popWin.isShowing()) {
                    popWin.dismiss();
                } else {
                    popWin.showAsDropDown(iv_set);
                }

                break;
            case R.id.iv_music_up:

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                System.gc();
                --musicPosition;
                if (musicPosition == -1) {
                    musicPosition = ResourcesUtils.MUSIC_URL.length - 1;
                }
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this, ResourcesUtils.MUSIC_URL[musicPosition]);

                mediaPlayer.start();
                iv_music_pause.setImageResource(R.drawable.ic_action_play_pause);

                break;
            case R.id.iv_music_pause:

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        iv_music_pause.setImageResource(R.drawable.ic_action_play_play);
                    } else {
                        mediaPlayer.start();
                        iv_music_pause.setImageResource(R.drawable.ic_action_play_pause);
                    }
                }

                break;
            case R.id.iv_music_next:

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                System.gc();
                ++musicPosition;
                if (musicPosition == ResourcesUtils.MUSIC_URL.length) {
                    musicPosition = 0;
                }
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this, ResourcesUtils.MUSIC_URL[musicPosition]);

                mediaPlayer.start();
                iv_music_pause.setImageResource(R.drawable.ic_action_play_pause);

                break;
        }
    }


    @Override
    public void goToLookPhotos() {
        if (popWin.isShowing()) {
            popWin.dismiss();
        }
        Intent intent = new Intent(StartUpFlushActivity.this, PhotosActivity.class);
        startActivity(intent);
        //向下滑动跳转
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        //向右滑动跳转
//        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        //透明慢慢进入
//        overridePendingTransition(R.anim.fade,R.anim.hold);
        //旋转动画，很帅
//        overridePendingTransition(R.anim.hyperspace_in,R.anim.hyperspace_out);
    }

    @Override
    public void goToLookInterestingPhotos() {
        if (popWin.isShowing()) {
            popWin.dismiss();
        }
        Intent intent = new Intent(StartUpFlushActivity.this, InterestingActivity.class);
        startActivity(intent);
        //向右滑动跳转
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.reset();
    }
}
