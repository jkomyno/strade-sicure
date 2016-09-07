package it.concorso.sanstino.stradesicure.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Easy storage and retrieval of preferences.
 */
public class PreferencesHelper {

    private static final String PLAYER_PREFERENCES = "playerPreferences";
    private static final String PREFERENCE_NAME = PLAYER_PREFERENCES + ".name";
    private static final String PLAYER_STATIC_NAME = "user";

    private PreferencesHelper() {
        //no instance
    }

    /**
     * @param context The Context which to obtain the SharedPreferences from.
     */
    public static void writeToPreferences(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCE_NAME, PLAYER_STATIC_NAME);
        editor.apply();
    }

    /**
     * Signs out a player by removing all it's data.
     *
     * @param context The context which to obtain the SharedPreferences from.
     */
    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCE_NAME);
        editor.apply();
    }

    /**
     * Checks whether a player is currently signed in.
     *
     * @param context The context to check this in.
     * @return <code>true</code> if login data exists, else <code>false</code>.
     */
    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCE_NAME);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PLAYER_PREFERENCES, Context.MODE_PRIVATE);
    }
}
