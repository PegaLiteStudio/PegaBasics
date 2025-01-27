package com.pegalite.pegabasics.helpers;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * FileDownloader - A utility class to download files from a given URL.
 * <p>
 * This class downloads a file to the cache directory using a background thread.
 * It ensures that incomplete downloads do not leave partial files.
 *
 * <p>
 * Features:
 * - Downloads a file from a URL.
 * - Saves it temporarily and renames it upon successful completion.
 * - Provides callbacks for progress updates, completion, and failure.
 * </p>
 * <p>
 * Created by: Sahil Hossain (PegaLiteStudio)
 * Date: 26 January 2025
 */
public class FileDownloader {

    /**
     * Downloads a file from the given URL and saves it in the cache directory.
     *
     * @param context  The application context.
     * @param fileUrl  The URL of the file to download.
     * @param fileName The name of the file to save.
     * @param callback The callback to handle progress, success, and failure.
     */
    public static void downloadFile(Context context, String fileUrl, String fileName, DownloadCallback callback) {
        new Thread(() -> {
            InputStream input = null;
            BufferedOutputStream output = null;
            HttpURLConnection connection = null;

            try {
                // Create URL and open connection
                URL url = new URL(fileUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10_000); // Timeout for connection
                connection.setReadTimeout(15_000); // Timeout for reading data
                connection.connect();

                // Check if response is OK (HTTP 200)
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new Exception("Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage());
                }

                // Get file length (for progress calculation)
                int fileLength = connection.getContentLength();

                // Get cache directory
                File cacheDir = context.getCacheDir();
                File finalFile = new File(cacheDir, fileName);  // Final destination
                File tempFile = new File(cacheDir, fileName + ".tmp");  // Temporary file

                // Open input and output streams
                input = new BufferedInputStream(connection.getInputStream(), 8192);
                output = new BufferedOutputStream(new FileOutputStream(tempFile), 8192);

                byte[] data = new byte[8192];
                long total = 0;
                int count;

                // Read data and write to the temporary file
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);

                    // Update progress
                    if (fileLength > 0) {
                        int progress = (int) (total * 100 / fileLength);
                        callback.onProgressUpdate(progress);
                    }
                }

                // Flush the output stream
                output.flush();

                // Ensure full download before renaming
                if (total == fileLength) {
                    // Rename temp file to final file
                    if (tempFile.renameTo(finalFile)) {
                        callback.onDownloadComplete(finalFile);
                    } else {
                        throw new Exception("Failed to rename file.");
                    }
                } else {
                    throw new Exception("Download incomplete.");
                }

            } catch (Exception e) {
                callback.onDownloadFailed(e);
            } finally {
                try {
                    if (input != null) input.close();
                    if (output != null) output.close();
                } catch (Exception ignored) {
                }

                if (connection != null) connection.disconnect();
            }
        }).start();
    }

    /**
     * Callback interface for handling download progress, success, and failure.
     */
    public interface DownloadCallback {
        /**
         * Called when the download progress updates.
         *
         * @param progress Percentage of the file downloaded (0-100).
         */
        void onProgressUpdate(int progress);

        /**
         * Called when the file is successfully downloaded.
         *
         * @param file The downloaded file.
         */
        void onDownloadComplete(File file);

        /**
         * Called when the download fails.
         *
         * @param e The exception that caused the failure.
         */
        void onDownloadFailed(Exception e);
    }
}


