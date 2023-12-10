package com.example.puiyeeng_coleanam_mapd711_project

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class FashionGuideService : IntentService("FashionGuideService") {
    private val thread = Thread()
    private val handler = Handler(Looper.getMainLooper()) // Handler for UI thread

    override fun onHandleIntent(intent: Intent?) {
        thread.start()
        try {
            // Actual file download logic
            val fileUrl = URL("https://catalogimages.wiley.com/images/db/pdf/9780470539262.excerpt.pdf")
            val result = downloadFile(fileUrl)

            // Display a Toast on the UI thread
            handler.post {
                showToast("Downloaded $result bytes")
            }

        } catch (e: MalformedURLException) {
            // Log any URL-related errors
            e.printStackTrace()

            // Display a Toast for the error
            handler.post {
                showToast("Error: Malformed URL")
            }
        }
    }
    private fun downloadFile(url: URL): Int {
        try {
            // Open a connection to the URL
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

            // Get the input stream
            val inputStream = BufferedInputStream(connection.inputStream)

            // Create a FileOutputStream to save the downloaded file
            val outputStream = FileOutputStream(applicationContext.getExternalFilesDir(null)?.absolutePath + "/fashion_guide.pdf")

            // Buffer to read data from input stream
            val buffer = ByteArray(1024)
            var bytesRead: Int

            // Read from the input stream and write to the output stream
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            // Close the streams
            inputStream.close()
            outputStream.close()

            // Disconnect the HttpURLConnection
            connection.disconnect()

            // Return the total bytes downloaded
            return connection.contentLength

        } catch (e: Exception) {
            // Log any errors during download
            e.printStackTrace()

            // Display a Toast for the error
            handler.post {
                showToast("Error: ${e.message}")
            }
        }
        // Return 0 if the download was unsuccessful
        return 0
    }

    // Helper method to show a Toast on the UI thread
    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
