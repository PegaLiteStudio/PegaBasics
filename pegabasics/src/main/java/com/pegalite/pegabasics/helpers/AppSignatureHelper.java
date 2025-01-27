package com.pegalite.pegabasics.helpers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * AppSignatureHelper is a utility class to retrieve and log the app's signature in
 * Base64-encoded SHA-256 format. It handles compatibility for both older and newer versions of Android.
 * <p>
 * Created by: Sahil Hossain (PegaLiteStudio)

 * Date: 2025-01-27
 * Version: 1.1.0
 */
public class AppSignatureHelper {

    private static final String TAG = "AppSignatureHelper";

    /**
     * Retrieves and logs the app's signature in Base64-encoded SHA-256 format.
     *
     * @param context The application context.
     */
    public static String getAppSignature(Context context) {
        String appSignature = "---pega---"; // Default Signature.
        try {
            // Retrieve the package name of the current app
            String packageName = context.getPackageName();

            // Obtain the PackageInfo containing the signatures
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES // Note: Deprecated in API 28
            );

            // Iterate over the signatures
            for (Signature signature : packageInfo.signatures) {
                // Compute the SHA-256 hash of the signature
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(signature.toByteArray());

                // Encode the hash in Base64
                appSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT);

                // Log the app signature
                Log.d(TAG, "App Signature (Base64 SHA-256): " + appSignature);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package not found", e);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "SHA-256 algorithm not found", e);
        }

        return appSignature;
    }
}
