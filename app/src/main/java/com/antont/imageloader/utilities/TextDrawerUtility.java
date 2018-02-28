package com.antont.imageloader.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class TextDrawerUtility {

    public void drawTextOverImage(ImageView image, String text) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        Bitmap newImage = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_4444);

        Canvas c = new Canvas(newImage);
        c.drawBitmap(((BitmapDrawable) image.getDrawable()).getBitmap(), 0, 0, null);

        drawFilledTriangle(imageWidth, imageHeight, Color.WHITE, c);
        drawTextDiagonally(text, imageWidth, imageHeight, Color.BLACK, c);

        image.setImageBitmap(newImage);
    }

    private void drawTextDiagonally(String text, int imageWidth, int imageHeight, int color, Canvas c) {
        Paint paint;

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        paint.setTextSize(getOptimalFontSize(text, imageWidth));

        int textWith = (int) paint.measureText(text);

        int textOffset = ((int) Math.sqrt(Math.pow((imageHeight / 4), 2) + Math.pow((imageHeight / 4), 2)) - textWith) / 2;

        Path textPath = new Path();
        textPath.moveTo(3 * imageWidth / 4, imageHeight);
        textPath.lineTo(imageWidth, 3 * imageHeight / 4);
        textPath.close();

        c.drawTextOnPath(text, textPath, textOffset, 0, paint);
    }

    private int getOptimalFontSize(String text, int imageWidth) {
        int size = 0;
        int length = (int) Math.sqrt(Math.pow((imageWidth / 4), 2) + Math.pow((imageWidth / 4), 2));
        Paint paint = new Paint();

        while ((paint.measureText(text) + imageWidth * 0.05) < length) {
            paint.setTextSize(size);
            size++;
        }
        return size;
    }

    private void drawFilledTriangle(int imageWidth, int imageHeight, int color, Canvas c) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(imageWidth, imageHeight / 2);
        path.lineTo(imageWidth, imageHeight);
        path.lineTo(imageWidth / 2, imageHeight);
        path.lineTo(imageWidth, imageHeight / 2);
        path.close();
        c.drawPath(path, paint);
    }

}
