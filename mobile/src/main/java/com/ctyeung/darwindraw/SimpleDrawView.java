/*
 * Code from article:
 *
 * https://guides.codepath.com/android/Basic-Painting-with-Views
 */
package com.ctyeung.darwindraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawView extends View {
    // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    // Store circles to draw each time the user touches down
    private List<Point> circlePoints;

    public SimpleDrawView(Context context,
                          AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint(); // same as before
        circlePoints = new ArrayList<Point>();
    }

    // Draw each circle onto the view
    @Override
    protected void onDraw(Canvas canvas) {

        for (Point p : circlePoints) {
            canvas.drawCircle(p.x, p.y, 5, drawPaint);
        }
    }

    // Append new circle each time user presses on screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
        // indicate view should be redrawn
        postInvalidate();
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

        drawPaint.setStyle(Paint.Style.FILL); // change to fill

    }
}
