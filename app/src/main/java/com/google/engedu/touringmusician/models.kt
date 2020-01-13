package com.google.engedu.touringmusician

import android.graphics.Point


data class Edge(var point: Point, var weight: Float = 0f) {
    var prev: Point = point
    var next: Point= point
        set(value) {
            weight = point.distanceBetween(value)
            field = value
        }


}


data class UnionNode(var parent: Point, var rank: Int = 0)


data class ClosestPoint(var distance: Float = Float.MAX_VALUE, var point: Point? = null)


