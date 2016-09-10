package it.concorso.sanstino.stradesicure.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.concorso.android.stradesicure.R;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.ss.stradesicure.UnityPlayerActivity;
import com.ss.stradesicure.UnityPlayerNativeActivity;

import java.util.ArrayList;
import java.util.List;

import it.concorso.sanstino.stradesicure.adapter.MyMenuAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_FORWARD_INTRO = "it.concorso.sanstino.stradesicure.activity.ACTION_FORWARD_INTRO";
    private static final String TUTORIAL_ACTION = "TUTORIAL_ACTION";
    private static String url = "http://www.sanstino.it";
    private Context context;

    LinearLayout menuContainer;
    LinearLayout goToQuiz,
            goToGame,
            goToSite,
            goToSettings,
            goToAbout;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        Intent intent = getIntent();
        String action = intent.getAction();
        savedInstanceState.putString(TUTORIAL_ACTION, action);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getApplicationContext();

        Intent intent = getIntent();
        String action = intent.getAction();
        if (action != null && savedInstanceState == null && action.equals(ACTION_FORWARD_INTRO)) {
            Log.d("POLLO", action);
            Intent introIntent = new Intent(MainActivity.this, MaterialIntroActivity.class);
            startActivity(introIntent);
        }

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyMenuAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                switch(pos) {
                    case 0:
                        goToCategorySelectionActivity();
                        break;
                    case 1:
                        //goToGameActivity();
                        goToGamePlayStore();
                        break;
                    case 2:
                        goToTutorial();
                        break;
                    case 3:
                        goToSiteActivity();
                        break;
                    case 4:
                        goToSettingsActivity();
                        break;
                    case 5:
                        goToAboutActivity();
                        break;
                }
            }
        });
    }

    private void goToCategorySelectionActivity() {
        Intent i = new Intent(MainActivity.this, CategorySelectionActivity.class);
        startActivity(i);
    }

    private void goToGamePlayStore() {
        Uri webpage = Uri.parse("https://play.google.com/store/apps/details?id=com.ss.stradesicuregame&hl=it");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    private void goToGameActivity() {
        Intent i = new Intent(MainActivity.this, UnityPlayerActivity.class);
        startActivity(i);
        // finish();
    }

    private void goToTutorial() {
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        i.setAction(MainActivity.ACTION_FORWARD_INTRO);
        startActivity(i);
    }

    private void goToSiteActivity() {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    private void goToSettingsActivity() {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(i);
    }

    private void goToAboutActivity() {
        new LibsBuilder()
                // pass the fields of your application to the lib so it can find all external lib information
                .withFields(R.string.class.getFields())
                .withActivityTitle("Open Source")
                .withAboutAppName("Strade Sicure")
                // provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                // start the activity
                .start(this);
    }

    // handle double tap to close
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    private Toast toastExit;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
            // dismiss Toast to avoid it being visible after the app is terminated
            // wtf it throws error
            // toastExit.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        toastExit = Toast.makeText(this, "Premi INDIETRO di nuovo per uscire dall'app", Toast.LENGTH_SHORT);
        toastExit.show();
        mHandler.postDelayed(mRunnable, 2000);
    }
}
