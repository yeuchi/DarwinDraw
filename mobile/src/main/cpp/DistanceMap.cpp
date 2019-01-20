//
// Created by yeuchi on 12/10/18.
//
// Module:      DistanceMap.cpp
//
// Description: Demonstrate integration of a C++ class in Android NDK.
//              Demonstrate skeletonization by evaluating distance map of the blob's outer bounds.
//

#include <jni.h>
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

bool DistanceMap::Map()
{
    return false;
}

//////////////////////////////////////////////////////////////////
// Protect / Private