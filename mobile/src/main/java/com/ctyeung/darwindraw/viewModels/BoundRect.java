package com.ctyeung.darwindraw.viewModels;

import com.ctyeung.darwindraw.MyPoint;

public class BoundRect
{
    public float minX;
    public float maxX;
    public float minY;
    public float maxY;

    public BoundRect(float minX, float maxX,
                     float minY, float maxY)
    {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    };

    public void find(MyPoint p)
    {
        if (p.x < minX)
            minX = p.x;

        if (p.x > maxX)
            maxX = p.x;

        if(p.y < minY)
            minY = p.y;

        if(p.y > minY)
            maxY = p.y;
    }

    public float getWidth()
    {
        return maxX -minX;
    }

    public float getHeight()
    {
        return maxY -minY;
    }
}
