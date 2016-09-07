package it.concorso.sanstino.stradesicure.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Root on 27/04/2016.
 */
public class DeleteDataOutGoingReceiver extends BroadcastReceiver {

    public static final String customIntent = "it.concorso.sanstino.stradesicure.broadcast.DeleteDataOutGoingReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setAction(customIntent);
        context.sendBroadcast(i);
    }
}
