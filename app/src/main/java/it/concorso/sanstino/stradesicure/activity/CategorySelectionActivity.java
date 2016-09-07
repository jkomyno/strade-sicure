package it.concorso.sanstino.stradesicure.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.concorso.android.stradesicure.R;

import it.concorso.sanstino.stradesicure.fragment.CategorySelectionFragment;
import it.concorso.sanstino.stradesicure.helper.ApiLevelHelper;
import it.concorso.sanstino.stradesicure.helper.PreferencesHelper;
import it.concorso.sanstino.stradesicure.persistence.StradeSicureDatabaseHelper;

public class CategorySelectionActivity extends AppCompatActivity {

    public static void start(Activity activity, ActivityOptionsCompat options) {
        Intent starter = getStartIntent(activity);
        ActivityCompat.startActivity(activity, starter, options.toBundle());
    }

    public static void start(Context context) {
        Intent starter = getStartIntent(context);
        context.startActivity(starter);
    }

    @NonNull
    static Intent getStartIntent(Context context) {
        Intent starter = new Intent(context, CategorySelectionActivity.class);
        return starter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_selection);
        // if there aren't record saved
        if (!PreferencesHelper.isSignedIn(this)) {
                PreferencesHelper.writeToPreferences(this);
        }
        setUpToolbar();
        if (savedInstanceState == null) {
            attachCategoryGridFragment();
        } else {
            setProgressBarVisibility(View.GONE);
        }
        supportPostponeEnterTransition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView scoreView = (TextView) findViewById(R.id.score);
        final int score = StradeSicureDatabaseHelper.getScore(this);
        scoreView.setText(getString(R.string.x_points, score));
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_player);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.category_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out: {
                signOut();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NewApi")
    private void signOut() {
        PreferencesHelper.signOut(this);
        StradeSicureDatabaseHelper.reset(this);
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            getWindow().setExitTransition(TransitionInflater.from(this)
                    .inflateTransition(R.transition.category_enter));
        }
        CategorySelectionActivity.start(this);
        ActivityCompat.finishAfterTransition(this);
    }

    private void attachCategoryGridFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentById(R.id.category_container);
        if (!(fragment instanceof CategorySelectionFragment)) {
            fragment = CategorySelectionFragment.newInstance();
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.category_container, fragment)
                .commit();
        setProgressBarVisibility(View.GONE);
    }

    private void setProgressBarVisibility(int visibility) {
        findViewById(R.id.progress).setVisibility(visibility);
    }
}

