package com.innovation.rain.feature.collection.scaniddoc.scan.common.cameracontroller

import android.media.Image
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Saves a JPEG [Image] into the specified [File].
 */
class ImageSaver(private val image: Image,
                 private val file: File)
    : Runnable {

    override fun run() {

        val buffer = image.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        var output: FileOutputStream? = null
        try {
            output = FileOutputStream(file).apply {
                write(bytes)
            }
        } catch (e: IOException) {
            Timber.e(e.toString())
        } finally {
            image.close()
            output?.let {
                try {
                    it.close()
                } catch (e: IOException) {
                    Timber.e(e.toString())
                }
            }
        }
    }
}
