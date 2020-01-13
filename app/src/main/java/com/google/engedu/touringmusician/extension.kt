package com.google.engedu.touringmusician

import android.graphics.Point
import kotlin.math.pow
import kotlin.math.sqrt

fun Point.distanceBetween(to: Point): Float {
    return sqrt((this.y - to.y.toDouble()).pow(2.0) + (this.x - to.x.toDouble()).pow(2.0)).toFloat()
}

fun HashMap<Point, Edge>.closestPoint(p: Point): Point {
    val currentClosestPoint = ClosestPoint()
    this.forEach {
        val newDistance = p.distanceBetween(it.key)
        if (currentClosestPoint.distance > newDistance) {
            currentClosestPoint.distance = newDistance
            currentClosestPoint.point = it.key
        }
    }
    return currentClosestPoint.point!!
}

/**
 * adding point in specific location
 */
fun HashMap<Point, Edge>.addNode(p: Point, prev: Point, next: Point) {
    val node = Edge(p)
    node.next = next
    node.prev = prev
    this[p] = node
    this[prev]!!.next = p

}

/**
 * calculate the total distance of the graph
 */
fun HashMap<Point, Edge>.totalDistance(): Float {
    var total = 0f
    forEach {
        total += it.value.weight
    }
    return total
}
