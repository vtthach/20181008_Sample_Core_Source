package com.sf0404.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class BitmapUtils {
    public static final int MAX_SIZE_IMAGE_UPLOAD = 800;
    public static final int AVATAR_SIZE = 200;
    private static final int VERTICAL = 0;
    private static final int HORIZONTAL = 1;

    /**
     * @param bitmapSource
     * @param context
     * @return
     */
    public static Bitmap copyBitmap(Bitmap bitmapSource, Context context) {
        int[] screenSize = UiUtil.getScreenSize(context);
        int maxSide = Math.max(bitmapSource.getWidth(), bitmapSource.getHeight());
        int maxAllowSize = Math.max(screenSize[0], screenSize[1]);
        if (maxAllowSize < maxSide) {
            return createScaleBitmap(bitmapSource, screenSize[0], screenSize[1], true);
        } else {
            return Bitmap.createBitmap(bitmapSource);
        }
    }

    public static Bitmap createScaleBitmap(Bitmap bitmapSource, int desireWidth, int desireHeight, boolean isKeepRatio) {
        if (isKeepRatio) {
            float ratio;
            if (bitmapSource.getWidth() > bitmapSource.getHeight()) {
                ratio = (float) desireWidth / (float) bitmapSource.getWidth();
            } else {
                ratio = (float) desireHeight / (float) bitmapSource.getHeight();
            }
            return Bitmap.createScaledBitmap(bitmapSource, (int) (bitmapSource.getWidth() * ratio), (int) (bitmapSource.getHeight() * ratio), false);
        } else {
            return Bitmap.createScaledBitmap(bitmapSource, desireWidth, desireHeight, false);
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    || (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromFile(String file, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();

        if (reqHeight > 0 && reqWidth > 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file, options);
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        }

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return getBitmapWithExif(BitmapFactory.decodeFile(file, options), file);
    }


    static public void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {
        final int frameSize = width * height;

        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0) y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0) r = 0;
                else if (r > 262143) r = 262143;
                if (g < 0) g = 0;
                else if (g > 262143) g = 262143;
                if (b < 0) b = 0;
                else if (b > 262143) b = 262143;

                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
            }
        }
    }

    static public void decodeYUV420SPGrayscale(int[] rgb, byte[] yuv420sp, int width, int height) {
        final int frameSize = width * height;

        for (int pix = 0; pix < frameSize; pix++) {
            int pixVal = (0xff & ((int) yuv420sp[pix])) - 16;
            if (pixVal < 0) pixVal = 0;
            if (pixVal > 255) pixVal = 255;
            rgb[pix] = 0xff000000 | (pixVal << 16) | (pixVal << 8) | pixVal;
        } // pix
    }


    public static Bitmap convertBytesToBitmap(byte[] bytes) throws Exception {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap convertBytesToBitmap(byte[] bytes, int reqWidth, int reqHeight) throws Exception {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if (reqWidth > 0 && reqHeight > 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        }
        // Calculate inSampleSize
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    public static byte[] covertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] getCompressBitmapData(String filePath, int width, int height, int quality, boolean isEncrypt) {
        Bitmap scaledBitmap = decodeBitmapFromFile(filePath, width, height, isEncrypt);
        return BitmapUtils.compressBitmap(scaledBitmap, quality);
    }

    public static Bitmap decodeBitmapFromFile(String filePath, int width, int height, boolean isEncrypt) {
        if (isEncrypt) {
            return decodeSampleByteArray(filePath, width, height);
        } else {
            return decodeSampledBitmapFromFile(filePath, width, height);
        }
    }

    private static Bitmap decodeSampleByteArray(String filePath, int reqWidth, int reqHeight) {
        byte[] bytes = getDecryptedByte(filePath);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if (reqWidth > 0 && reqHeight > 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        }
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return getBitmapWithExif(BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options), filePath);
    }

    private static byte[] getDecryptedByte(String filePath) {
        // TODO add crypto lib here
        // CryptoUtils.decryptFile(filePath);
        return new byte[0];
    }

    public static Bitmap getBitmapWithExif(@NonNull Bitmap bitmap, String filePath) {
        ExifInterface exifReader;
        try {
            exifReader = new ExifInterface(filePath);
        } catch (IOException e) {
            Timber.e(e, "get bitmap with exif error: " + e.getMessage());
            return bitmap;
        }
        int orientation = exifReader.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        Bitmap rs = null;
        if (orientation == ExifInterface.ORIENTATION_NORMAL) {
            // Do nothing. The original image is fine.
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            rs = rotateBitmap(bitmap, 90);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            rs = rotateBitmap(bitmap, 180);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            rs = rotateBitmap(bitmap, 270);
        }
        if (rs != null) {
            bitmap.recycle();
            return rs;
        } else {
            return bitmap;
        }
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static byte[] compressBitmap(Bitmap scaledBitmap, int quality) {
        ByteArrayOutputStream out;
        try {
            out = new ByteArrayOutputStream();
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            return out.toByteArray();
        } catch (Exception e) {
            Timber.e("compressBitmap exception: " + e.getMessage());
        }
        return new byte[0];
    }

    /**
     * Creates a new bitmap by flipping the specified bitmap
     * vertically or horizontally.
     *
     * @param src  Bitmap to flipBitmap
     * @param type Flip direction ({@link #HORIZONTAL} or {@link #VERTICAL})
     * @return New bitmap created by flipping the given one
     * vertically or horizontally as specified by
     * the <code>type</code> parameter or
     * the original bitmap if an unknown type
     * is specified.
     **/
    public static Bitmap flipBitmap(Bitmap src, int type) {
        Matrix matrix = new Matrix();

        if (type == VERTICAL) {
            matrix.preScale(1.0f, -1.0f);
        } else if (type == HORIZONTAL) {
            matrix.preScale(-1.0f, 1.0f);
        } else {
            return src;
        }

        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    public static Observable<String> getCacheImageObservable(Intent data, Context context) {
        return Observable.defer(() -> {
            String fileSaved = PhotoUtils.createImageFile(context).getAbsolutePath();
            Uri uri = data.getData();
            try {
                InputStream input = context.getContentResolver().openInputStream(uri);
                if (input != null) {
                    FileUtils.writeInputStreamToFile(input, fileSaved);
                    input.close();
                }
            } catch (Exception e) {
                Timber.e(e, "Copy image from pick photo action error: " + e.getMessage());
                throw new RuntimeException("Create image error: " + e.getMessage()); // NOSONAR
            }
            return Observable.just(fileSaved);
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    //I added this method because people keep asking how
    //to calculate the dimensions of the bitmap...see comments below
    public static int getSquareCropDimensionForBitmap(Bitmap bitmap) {
        //use the smallest dimension of the image to crop to
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }

    public static String scaleCenterCrop(String photoFilePath, Context context, boolean isAvatarCrop) throws IOException {
        Bitmap originalBitmap = decodeBitmapFromFile(photoFilePath, MAX_SIZE_IMAGE_UPLOAD, MAX_SIZE_IMAGE_UPLOAD, false);
        int dimension = getSquareCropDimensionForBitmap(originalBitmap);
        // Center crop
        Bitmap finalBitmap = originalBitmap;
        if (isAvatarCrop) {
            finalBitmap = ThumbnailUtils.extractThumbnail(originalBitmap, dimension, dimension);
            if (finalBitmap != originalBitmap) {
                originalBitmap.recycle();
            }
            // Scale center crop
            Bitmap originalScale = scaleBitmapAndKeepRatio(finalBitmap, AVATAR_SIZE, AVATAR_SIZE);
            if (originalScale != finalBitmap) {
                finalBitmap.recycle();
            }
            finalBitmap = originalScale;
        }
        // Write to file
        File newFile = PhotoUtils.createImageFile(context);
        FileUtils.writeBitmapToFile(finalBitmap, newFile.getAbsolutePath());
        // Release
        finalBitmap.recycle();
        return newFile.getAbsolutePath();
    }

    public static Observable<String> getCropObservable(String photoFilePath, Context context, boolean isAvatarCrop) {
        return Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just(BitmapUtils.scaleCenterCrop(photoFilePath, context, isAvatarCrop));
            }
        });
    }

    public static Bitmap scaleBitmapAndKeepRatio(Bitmap targetBitmap, int reqHeightInPixels, int reqWidthInPixels) {
        RectF targetRect = new RectF(0, 0, targetBitmap.getWidth(), targetBitmap.getHeight());
        RectF scaleRect = new RectF(0, 0, reqWidthInPixels, reqHeightInPixels);
        if (getMaxSide(targetRect) < getMaxSide(scaleRect)) {
            return targetBitmap;
        } else {
            Matrix m = new Matrix();
            m.setRectToRect(targetRect, scaleRect, Matrix.ScaleToFit.CENTER);
            return Bitmap.createBitmap(targetBitmap, 0, 0, targetBitmap.getWidth(), targetBitmap.getHeight(), m, true);
        }
    }

    public static double getMaxSide(RectF targetRect) {
        return Math.max(targetRect.width(), targetRect.height());
    }
}
