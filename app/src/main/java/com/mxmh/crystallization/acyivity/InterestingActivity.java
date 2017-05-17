package com.mxmh.crystallization.acyivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.mxmh.crystallization.R;
import com.mxmh.crystallization.utils.ImageUtils;
import com.mxmh.crystallization.utils.ResourcesUtils;

/**
 * Created by gumenghao on 17/4/26.
 */

public class InterestingActivity extends BaseActivity implements View.OnClickListener {

    private AnimationSet animationSet;
    private ImageView iv_back_photos;
    private ImageView iv_photos;
    private Button bt_next_phone;
    private Button bt_up_phone;

    private int imagePosition = 0;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_photos_interestion;
    }

    @Override
    protected void initView() {

        iv_back_photos = (ImageView) findViewById(R.id.iv_back_photos);
        iv_photos = (ImageView) findViewById(R.id.iv_photos);
        bt_next_phone = (Button) findViewById(R.id.bt_next_phone);
        bt_up_phone = (Button) findViewById(R.id.bt_up_phone);
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_back_photos.setOnClickListener(this);
        bt_next_phone.setOnClickListener(this);
        bt_up_phone.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        initAnimation();

        iv_photos.startAnimation(animationSet);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_photos:
                InterestingActivity.this.finish();
                break;
            case R.id.bt_up_phone:
                --imagePosition;
                if (imagePosition == -1) {
                    imagePosition = ResourcesUtils.GAOXIAO_URL.length - 1;
                }

                ImageUtils.disBigImage("drawable://" + ResourcesUtils.GAOXIAO_URL[imagePosition], iv_photos);

                break;
            case R.id.bt_next_phone:
                ++imagePosition;
                if (imagePosition == ResourcesUtils.GAOXIAO_URL.length) {
                    imagePosition = 0;
                }

                ImageUtils.disBigImage("drawable://" + ResourcesUtils.GAOXIAO_URL[imagePosition], iv_photos);

                break;
        }
    }

    private void initAnimation() {//setRepeatCount(Animation.INFINITE); 无限循环
        //1.AnimationSet
        animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());
        //透明度
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.3f);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
//        alphaAnimation.setRepeatCount(Animation.INFINITE);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
        //旋转
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
        //缩放（以某个点为中心缩放）
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.4f, 1, 0.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        //添加动画
        animationSet.setFillAfter(true);
//        animationSet.addAnimation(alphaAnimation);
//        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(300);
        animationSet.setStartOffset(0);

        // 2.XML文件
//        Animation animation = AnimationUtils.loadAnimation(this,
//                R.anim.hyperspace_out);
//        animation.setRepeatCount(Animation.INFINITE);//循环显示
//        iv_photos.startAnimation(animation);
    }

}
