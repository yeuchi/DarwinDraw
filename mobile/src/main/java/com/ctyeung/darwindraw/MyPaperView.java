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
import java.util.List;

public class MyPaperView extends View
{
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    private Path path;
    protected List<MyPoint> points;

    public MyPaperView(Context context,
                          AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
        path = new Path();
        points = new ArrayList<MyPoint>();
        //path.setFillType(Path.FillType.WINDING);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawPath(path, drawPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        MyPoint point = new MyPoint(event.getX(), event.getY());
        points.add(point);

        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Starts a new line in the path
                path.moveTo(point.x, point.y);
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(point.x, point.y);
                break;

            case MotionEvent.ACTION_UP:
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
        drawPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

    }
}
