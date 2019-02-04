package com.ctyeung.darwindraw.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

import com.ctyeung.darwindraw.viewModels.BoundRect;

public class BitmapUtil
{
    /*
     * draw view onto bitmap
     *
     * Reference: code from this article
     * https://stackoverflow.com/questions/2801116/converting-a-view-to-bitmap-without-displaying-it-in-android
     */
    static public Bitmap renderView(View view,
                                    BoundRect rect)
    {
        view.measure(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Bitmap bitmap = Bitmap.createBitmap( (int)rect.getWidth(), (int)rect.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.layout((int)rect.minX, (int)rect.minY, (int)rect.maxX, (int)rect.maxY);
        view.draw(c);
        return bitmap;
    }
}
