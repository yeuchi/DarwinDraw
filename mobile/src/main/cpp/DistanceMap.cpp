//
// Created by yeuchi on 12/10/18.
//
// Module:      DistanceMap.cpp
//
// Reference:    http://www.inf.u-szeged.hu/~palagyi/skel/skel.html
//
// Description: Demonstrate integration of a C++ class in Android NDK.
//              Demonstrate skeletonization by evaluating distance map of the blob's outer bounds.
//

#include <jni.h>
#include <math.h>
#include <android/log.h>
#include <android/bitmap.h>
#include "Common.h"
#include "DistanceMap.h"

//////////////////////////////////////////////////////////////////
// Construct / Destruct

DistanceMap::DistanceMap() {}

DistanceMap::~DistanceMap() {}

//////////////////////////////////////////////////////////////////
// Public

/*
 * apply distance map on to blob
 */
bool DistanceMap::Map(AndroidBitmapInfo infoSource,
                      void* pixelsSource,
                      jfloat *pointsX,
                      jfloat *pointsY,
                      int count)
{
    // would a quad tree be faster?
    // need data structure here for performance.

    // calculate the rectangular bound of the blob within the image
    if (!CalculateRectBound(count, pointsX, pointsY))
        return false;

    // calculate the multiplier to scale brightness
    float scaler = CalculateScaler();

    // evaluate pixels in rectangle
    for (int y=minY; y<maxY; y++)
    {
        void* currentPixelsSource = (char *)pixelsSource + (infoSource.stride * (y));
        rgba * line = (rgba *) currentPixelsSource;

        for(int x=minX; x<maxX; x++)
        {
            // edit pixels that are in the black fill blob
            if(line[x].red ==0 && line[x].green==0 && line[x].blue==0)
            {
                // calculate distance
                int min = (int)(CalculateMin(x, y, count, pointsX, pointsY) * scaler);
                line[x].red = min;
                line[x].green = min;
                line[x].blue = min;
            }
        }
    }

    return true;
}

//////////////////////////////////////////////////////////////////
// Protect / Private

/*
 * Find the nearest rect bounds around the blob
 */
bool DistanceMap::CalculateRectBound(int count,
                                     jfloat* pointsX, jfloat* pointsY)
{
    float minX = 10000;
    float maxX = 0;

    for (int x = 0; x < count; x ++)
    {
        if(pointsX[x] < minX)
            minX = pointsX[x];

        if(pointsX[x] > maxX)
            maxX = pointsX[x];
    }

    float minY = 10000;
    float maxY = 0;

    for (int y = 0; y < count; y ++)
    {
        if(pointsY[y] < minY)
            minY = pointsY[y];

        if(pointsY[y] > maxY)
            maxY = pointsY[y];
    }

    return (minX < 10000 || maxX > 0 || minY < 10000 || maxY > 0)? true:false;
}

/*
 * Find the closest blob point
 * return calculated distance
 */
float DistanceMap::CalculateMin(int x, int y,
                                int count,
                                jfloat* pointsX, jfloat* pointsY)
{
    float min = 1000;
    for(int i=0; i<count; i++)
    {
        float deltaY = pointsY[i] - y;
        float deltaX = pointsX[i] - x;
        float dis = sqrtf(deltaY*deltaY - deltaX*deltaX);

        if(dis < min)
            min = dis;
    }
    return min;
}

/*
 * Assume a relative square rect,
 * max distance is 1/2 of width or height.
 */
float DistanceMap::CalculateScaler()
{
    float width = (maxX - minX) / 2.0;
    float height = (maxY - minY) / 2.0;
    float min = (width<height)?width:height;
    return 255.0 / min;
}
