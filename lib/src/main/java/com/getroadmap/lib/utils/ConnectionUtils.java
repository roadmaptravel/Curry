package com.getroadmap.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jan on 22/08/2017.
 */

public class ConnectionUtils {
    public static boolean isNetworkConnected(Context context) {

        // get Connectivity Manager to get network status
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true; //we have a connection
        } else {
            return false; // no connection!
        }
    }
}
