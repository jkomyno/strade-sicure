package it.concorso.sanstino.stradesicure.activity;

import android.os.Bundle;

import com.concorso.android.stradesicure.R;

import it.concorso.sanstino.stradesicure.tutorial.app.IntroActivity;
import it.concorso.sanstino.stradesicure.tutorial.slide.SimpleSlide;

/**
 * Created by Root on 18/04/2016.
 */
public class MaterialIntroActivity extends IntroActivity {
    private boolean fullScreen = false;
    private boolean skipEnabled = true;
    private boolean finishEnabled = true;
    private boolean scrollable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(fullScreen);
        super.onCreate(savedInstanceState);
        setSkipEnabled(skipEnabled);
        setFinishEnabled(finishEnabled);

        // bike
        addSlide(new SimpleSlide.Builder()
                .title(R.string.intro_slide_bike_title)
                .description(R.string.intro_slide_bike_description)
                .image(R.drawable.art_bike)
                .background(R.color.intro_slide_bike)
                .backgroundDark(R.color.intro_dark_slide_bike)
                .scrollable(scrollable)
                .build());

        // game
        addSlide(new SimpleSlide.Builder()
                .title(R.string.intro_slide_game_title)
                .description(R.string.intro_slide_game_description)
                .image(R.drawable.art_game)
                .background(R.color.intro_slide_game)
                .backgroundDark(R.color.intro_dark_slide_game)
                .scrollable(scrollable)
                .build());

        // quiz
        addSlide(new SimpleSlide.Builder()
                .title(R.string.intro_slide_quiz_title)
                .description(R.string.intro_slide_quiz_description)
                .image(R.drawable.art_quiz)
                .background(R.color.intro_slide_quiz)
                .backgroundDark(R.color.intro_dark_slide_quiz)
                .scrollable(scrollable)
                .build());

        // nointernet
        addSlide(new SimpleSlide.Builder()
                .title(R.string.intro_slide_nointernet_title)
                .description(R.string.intro_slide_nointernet_description)
                .image(R.drawable.art_nointernet)
                .background(R.color.intro_slide_nointernet)
                .backgroundDark(R.color.intro_dark_slide_nointernet)
                .scrollable(scrollable)
                .build());

        // sanstino
        addSlide(new SimpleSlide.Builder()
                .title(R.string.intro_slide_sanstino_title)
                .description(R.string.intro_slide_sanstino_description)
                .image(R.drawable.art_sanstino)
                .background(R.color.intro_slide_sanstino)
                .backgroundDark(R.color.intro_dark_slide_sanstino)
                .scrollable(scrollable)
                .build());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
