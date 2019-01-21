//
// Created by yeuchi on 12/10/18.
//
// Module:      DistanceMap.h
//
// Description: Demonstrate integration of a C++ class in Android NDK.
//              Demonstrate skeletonization by evaluating distance map of the blob's outer bounds.
//

#ifndef DARWINDRAW_DISTANCEMAP_H
#define DARWINDRAW_DISTANCEMAP_H


class DistanceMap {
public:
    DistanceMap();
    ~DistanceMap();

public:
    bool Map(AndroidBitmapInfo infoSource,
             void* pixelsSource,
             float* pointsX,
             float* pointsY,
             int count);

protected:
    bool CalculateRectBound(int count, float* pointsX, float* pointsY);

    float CalculateMin(int x, int y,
                        int count, float* pointsX, float* pointsY);

    float CalculateScaler();

protected:
    int minX, maxX;
    int minY, maxY;
};


#endif //DARWINDRAW_DISTANCEMAP_H
