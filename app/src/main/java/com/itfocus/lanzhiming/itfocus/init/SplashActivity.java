package com.itfocus.lanzhiming.itfocus.init;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.itfocus.lanzhiming.itfocus.R;
import com.itfocus.lanzhiming.itfocus.activity.BaseActivity;
import com.itfocus.lanzhiming.itfocus.activity.MainActivity;


public class SplashActivity extends BaseActivity {

    private static final String FIRST_IN = "first_pref";
    private static final int GO_MAINACTIVITY = 1000;
    private static final int GO_WELCOMEACTIVITY = 1001;

    boolean isFirstIn = false;

    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_MAINACTIVITY:
                    goMainActivity();
                    break;
                case GO_WELCOMEACTIVITY:
                    goWelcomeActivity();
                    break;
            }
            super.handleMessage(msg);
        }
    };

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
//                判断状态，是否是第一次使用，若为第一次使用，将出现引导界面。
                init();
            }
        });

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

    private void init() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(
                FIRST_IN, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            mHandler.sendEmptyMessage(GO_MAINACTIVITY);
        } else {
            mHandler.sendEmptyMessage(GO_WELCOMEACTIVITY);
        }

    }

    private void goMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

    private void goWelcomeActivity() {
        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }
}
