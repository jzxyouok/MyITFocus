package com.itfocus.lanzhiming.itfocus.init;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.itfocus.lanzhiming.itfocus.R;
import com.itfocus.lanzhiming.itfocus.activity.BaseActivity;
import com.itfocus.lanzhiming.itfocus.activity.MainActivity;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);
        ImageView iv = (ImageView) findViewById(R.id.iv_flash);//获得闪屏页log图片
        Animation anim = getAnima();//获取闪屏也的渐变动画
        iv.startAnimation(anim);//开始闪屏页的动画
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }

            /**
             * 动画结束调用
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                startHomeActivity();//开始跳转主页面

            }
        });

    }

    /**
     * 跳转主页面
     */
    protected void startHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    /**
     * 获取渐变动画
     *
     * @return
     */
    private Animation getAnima() {
        AlphaAnimation anim = new AlphaAnimation(0.8f, 1);
        anim.setDuration(2000);
        anim.setFillAfter(true);
        return anim;
    }
}
