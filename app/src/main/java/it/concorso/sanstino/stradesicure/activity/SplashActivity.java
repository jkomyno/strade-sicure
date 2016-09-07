package it.concorso.sanstino.stradesicure.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.concorso.android.stradesicure.R;

/**
 * Created by Root on 22/04/2016.
 */
public class SplashActivity extends AppCompatActivity {
    boolean soft;

    public static boolean displayStaticSplash;
    public static boolean displayDynamicSplash;
    public static boolean startTutorial;
    public static int splashDuration;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        Log.d("POLLo", "splash");

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        startTutorial = settings.getBoolean("switch_tutorial", true);
        splashDuration = Integer.parseInt(settings.getString("list_splash", "4"));
        displayDynamicSplash = settings.getBoolean("switch_splash", true);
        displayStaticSplash = !displayDynamicSplash;

        if (displayDynamicSplash) {

            soft = ViewConfiguration.get(this).hasPermanentMenuKey();

            setContentView(R.layout.activity_splash);
            startHeavyProcessing();
        } else if (displayStaticSplash){
            setContentView(R.layout.activity_splash_no_animations);
            startMainActivity();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void startHeavyProcessing() {
        new LongOperation().execute("");
    }

    private void startMainActivity() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        if (startTutorial) {
            i.setAction(MainActivity.ACTION_FORWARD_INTRO);
        }
        startActivity(i);
        finish();
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            //some heavy processing resulting in a Data String
            for (int i = 0; i < 2; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "whatever result you have";
        }

        @Override
        protected void onPostExecute(String result) {
            startMainActivity();
        }

        @Override
        protected void onPreExecute() {
            StartAnimations();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        private void StartAnimations() {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
            anim.reset();
            LinearLayout l = (LinearLayout) findViewById(R.id.splash_layout);
            l.clearAnimation();
            l.startAnimation(anim);

            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_y_bottom_up);
            anim.reset();

            ImageView iv = (ImageView) findViewById(R.id.splash);
            Drawable drawable;

            // determine if there's soft keyboard to adjust splash image's size
            if (soft) {
                drawable = ContextCompat.getDrawable(context, R.drawable.splash_initial_fg_no_soft);
            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.splash_initial_fg_soft);
            }
            iv.setImageDrawable(drawable);

            iv.clearAnimation();
            iv.startAnimation(anim);
        }
    }
}