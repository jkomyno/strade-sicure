package it.concorso.sanstino.stradesicure.tutorial.util;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.concorso.android.stradesicure.R;

/**
 * Utility methods for working with animations.
 */
public class AnimUtils {

    private AnimUtils() {
    }

    private static Interpolator fastOutSlowIn;

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fastOutSlowIn = AnimationUtils.loadInterpolator(context,
                        android.R.interpolator.fast_out_slow_in);
            } else {
                fastOutSlowIn = new FastOutSlowInInterpolator();
            }
        }
        return fastOutSlowIn;
    }

    public static void applyShakeAnimation(Context context, View view) {
        Animation shake;
        shake = AnimationUtils.loadAnimation(context, R.anim.shake);
        view.startAnimation(shake);
    }

}