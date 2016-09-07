package it.concorso.sanstino.stradesicure.tutorial.app;

import android.support.v4.app.Fragment;

/**
 * Created by Root on 18/04/2016.
 */
public class SlideFragment extends Fragment {

    public boolean canGoForward() {
        return true;
    }

    public boolean canGoBackward() {
        return true;
    }

    public void updateNavigation() {
        if (getActivity() instanceof IntroActivity) {
            ((IntroActivity) getActivity()).lockSwipeIfNeeded();
        }
    }

    protected void nextSlide() {
        if (getActivity() instanceof IntroActivity) {
            ((IntroActivity) getActivity()).nextSlide();
        }
    }

    protected void previousSlide() {
        if (getActivity() instanceof IntroActivity) {
            ((IntroActivity) getActivity()).previousSlide();
        }
    }

}
