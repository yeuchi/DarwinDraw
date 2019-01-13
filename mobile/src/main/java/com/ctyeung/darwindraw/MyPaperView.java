/*
 * Code from article:
 *
 * https://guides.codepath.com/android/Basic-Painting-with-Views
 */
package com.ctyeung.darwindraw;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyPaperView extends View
{
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    private Path path = new Path();

    public MyPaperView(Context context,
                          AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawPath(path, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Starts a new line in the path
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                // Draws line between last point and this point
                path.lineTo(pointX, pointY);
                break;

            default:
                return false;
        }

        postInvalidate(); // Indicate view should be redrawn
        return true;
    }

    // Setup paint with color and stroke styles
    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        drawPaint.setStyle(Paint.Style.STROKE);

    }
}
