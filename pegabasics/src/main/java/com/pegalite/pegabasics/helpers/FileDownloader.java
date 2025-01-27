package com.pegalite.pegabasics.helpers;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {

    public static void downloadFile(Context context, String fileUrl, String fileName, DownloadCallback callback) {
        new Thread(() -> {
            InputStream input = null;
            BufferedOutputStream output = null;
            HttpURLConnection connection = null;

            try {
                // Create URL and open connection
                URL url = new URL(fileUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10_000);
                connection.setReadTimeout(15_000);
                connection.connect();

                // Check if response is OK
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new Exception("Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage());
                }

                // Get file length
                int fileLength = connection.getContentLength();

                // Get cache directory
                File cacheDir = context.getCacheDir();
                File finalFile = new File(cacheDir, fileName);  // Final destination
                File tempFile = new File(cacheDir, fileName + ".tmp");  // Temporary file

                // Open streams
                input = new BufferedInputStream(connection.getInputStream(), 8192);
                output = new BufferedOutputStream(new FileOutputStream(tempFile), 8192);

                byte[] data = new byte[8192];
                long total = 0;
                int count;

                // Read and write file
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);

                    // Update progress
                    if (fileLength > 0) {
                        int progress = (int) (total * 100 / fileLength);
                        callback.onProgressUpdate(progress);
                    }
                }

                // Flush output
                output.flush();

                // **Ensure full download before renaming**
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

    public interface DownloadCallback {
        void onProgressUpdate(int progress);

        void onDownloadComplete(File file);

        void onDownloadFailed(Exception e);
    }
}

