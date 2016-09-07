package it.concorso.sanstino.stradesicure.tutorial.app;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Root on 18/04/2016.
 */
public interface OnNavigationBlockedListener {
    @IntDef({DIRECTION_FORWARD, DIRECTION_BACKWARD})
    @Retention(RetentionPolicy.SOURCE)
    @interface Direction {
    }

    int DIRECTION_FORWARD = 1;

    int DIRECTION_BACKWARD = -1;

    void onNavigationBlocked(int position, @Direction int direction);
}