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
    private final int strokeColor = Color.BLACK;
    private final int fillColor = Color.GREEN;
    // defines paint and canvas
    private Paint drawPaint;
    private Path path;
    protected List<MyPoint> points;
    private PaperEvent mListener;

    public int width;
    public int height;

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

    public void setListener(PaperEvent listener)
    {
        mListener = listener;
    }

    public List<MyPoint> getPoints()
    {
        return points;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // stroke style
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setColor(strokeColor);
        canvas.drawPath(path, drawPaint);

        // fill style
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setColor(fillColor);
        canvas.drawPath(path, drawPaint);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        height= MeasureSpec.getSize(heightMeasureSpec);
        width= MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
                mListener.onActionUp();
                break;

            default:
                return false;
        }

        postInvalidate(); // Indicate view should be redrawn
        return true;
    }

    // Setup paint with color and stroke styles
    private void setupPaint() {
        // stroke style
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setColor(strokeColor);

        // fill style
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setColor(fillColor);
    }
}
