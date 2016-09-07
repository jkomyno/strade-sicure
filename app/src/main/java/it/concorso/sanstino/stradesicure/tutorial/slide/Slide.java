package it.concorso.sanstino.stradesicure.tutorial.slide;

/**
 * Created by Root on 18/04/2016.
 */
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;

public abstract class Slide {

    public abstract Fragment getFragment();

    @ColorRes
    public abstract int getBackground();
    @ColorRes
    public int getBackgroundDark(){
        return android.R.color.black;
    }

    public boolean canGoForward() {
        return true;
    }

    public boolean canGoBackward() {
        return true;
    }
}
