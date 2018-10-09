package com.sf0404.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class PhotoUtils {

    /**
     * @param act
     * @param requestCode
     * @param fileProvider R.string.file_provider create new one
     * @return
     */
    public static String startIntentCamera(Activity act, int requestCode, int fileProvider) {
        // TODO refactor startIntentCamera
        File photoFile = null;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            if (intent.resolveActivity(act.getPackageManager()) != null) {
                photoFile = createImageFile(act);
                Uri outPutUri = FileProvider.getUriForFile(act,
                        act.getString(fileProvider),
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
                act.startActivityForResult(intent, requestCode);
            }
        } catch (IOException e) {
            Timber.e(e, "StartIntentCamera error: " + e.getMessage());
        }
        return photoFile != null ? photoFile.getAbsolutePath() : null;
    }

    public static String startIntentCamera(Fragment fragment, int requestCode, int fileProvider) {
        File photoFile = null;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
                photoFile = createImageFile(fragment.getContext());
                Uri outPutUri = FileProvider.getUriForFile(fragment.getContext(),
                        fragment.getString(fileProvider),
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
                fragment.startActivityForResult(intent, requestCode);
            }
        } catch (IOException e) {
            Timber.e(e, "StartIntentCamera error: " + e.getMessage());
        }
        return photoFile != null ? photoFile.getAbsolutePath() : null;
    }

    public static void startIntentGallery(Activity act, int requestCode, CharSequence msg) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        act.startActivityForResult(Intent.createChooser(intent, msg), requestCode);
    }

    public static void startIntentGallery(Fragment fragment, int requestCode, CharSequence msg) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        fragment.startActivityForResult(Intent.createChooser(intent, msg), requestCode);
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getCachePhotoDir(context);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

    public static File getCachePhotoDir(Context context) {
        String state = Environment.getExternalStorageState();
        File filesDir;
        // Make sure it's available
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        } else {
            // Load another directory, probably local memory
            filesDir = context.getCacheDir();
        }
        return filesDir;
    }

    public static boolean deletePhotoCache(Context context) {
        try {
            File dir = getCachePhotoDir(context);
            return dir != null && dir.isDirectory() && deleteDir(dir);
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir != null && dir.delete();
    }
}