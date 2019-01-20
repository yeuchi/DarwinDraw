//
// Created by yeuchi on 12/10/18.
//
// Module:      DistanceMap.cpp
//
// Description: Demonstrate integration of a C++ class in Android NDK.
//              Demonstrate erosion by thinning algorithm
//

#include <jni.h>
#include <android/log.h>
#include <android/bitmap.h>
#include "Common.h"
#include "Erosion.h"

//////////////////////////////////////////////////////////////////
// Construct / Destruct

Erosion::Erosion() {}

Erosion::~Erosion() {}

//////////////////////////////////////////////////////////////////
// Public

bool Erosion::Thinning()
{
    return false;
}

//////////////////////////////////////////////////////////////////
// Protect / Private