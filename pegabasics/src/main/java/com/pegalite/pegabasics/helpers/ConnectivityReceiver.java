package com.pegalite.pegabasics.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/**
 * Created by Sahil The Geek
 * Date : 13-05-2023.
 *
 * <p>This {@link ConnectivityReceiver} checks internet connectivity in real-time.</p>
 */
public class ConnectivityReceiver extends BroadcastReceiver {

    private final Listener listener;

    public ConnectivityReceiver(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = isInternetAvailable(context);
        if (listener != null) {
            listener.onConnectivityChanged(isConnected);
        }
    }

    /**
     * Checks internet connectivity using NetworkCapabilities (API 24+).
     *
     * @param context Application context
     * @return true if the device has internet access, false otherwise
     */
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        Network network = connectivityManager.getActiveNetwork();
        if (network == null) return false;

        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
    }

    public interface Listener {
        void onConnectivityChanged(boolean isConnected);
    }
}
