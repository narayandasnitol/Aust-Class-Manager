package com.nitol.aust.cse.austclassmanager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_Activity extends AppCompatActivity {

    TextView title;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        title = (TextView) findViewById(R.id.titleName);
        image = (ImageView) findViewById(R.id.welcome_image);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/che.ttf");
        title.setTypeface(typeface);



        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_2);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        image.setAnimation(animation);
        title.setAnimation(animation2);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //title.setAnimation(animation);

                //finish();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                finish();
                startActivity(new Intent(getApplicationContext(), Signup_Activity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }


}
