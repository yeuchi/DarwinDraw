package com.ctyeung.darwindraw;

import android.graphics.Point;

public class MyPoint
{
    public float x;
    public float y;

    public MyPoint(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public MyPoint()
    {
        x = 0;
        y = 0;
    }

    protected float slopeFromPoint(Point p)
    {
        float slope = (float)(p.y-y)/(float)(p.x-x);
        return slope;
    }
}
