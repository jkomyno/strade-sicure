package it.concorso.sanstino.stradesicure.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import it.concorso.sanstino.stradesicure.helper.PreferencesHelper;
import it.concorso.sanstino.stradesicure.persistence.StradeSicureDatabaseHelper;

/**
 * Created by Root on 27/04/2016.
 */
public class DeleteDataIncomingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DeleteDataOutGoingReceiver.customIntent)) {
            deleteData(context);
        }
    }

    private void deleteData(Context context){
        PreferencesHelper.signOut(context);
        StradeSicureDatabaseHelper.reset(context);
    }
}