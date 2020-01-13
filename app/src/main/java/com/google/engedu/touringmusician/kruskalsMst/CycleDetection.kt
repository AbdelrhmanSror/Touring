package com.google.engedu.touringmusician.kruskalsMst

import android.graphics.Point
import com.google.engedu.touringmusician.UnionNode
import java.util.*
import kotlin.collections.HashMap


/**
 * class for detecting if there is a cycle in graph or not using union rank and path compression
 * complexity in worst case is log(n) which is much more better than naive solution which take liner time in worst case
 */
class CycleDetection {
    private val nodeMap = HashMap<Point, UnionNode>()
    //pair with src and destination
    private val edges = LinkedList<Pair<Point, Point>>()

    fun isCycleExist(vararg pair: Pair<Point, Point>): Boolean {
        pair.forEach {
            createIfNotExist(it.first, it.second)
        }
        edges.addAll(pair)
        pair.forEach {
            val src = findRoot(it.first)
            val des = findRoot(it.second)
            if (src == des) {
                return true
            }
            union(src, des)

        }

        return false

    }

    private fun findRoot(node: Point): Point {
        //here this if statement is as if i said [if(nodeMap[node].parent!=node)]
        if (nodeMap[node]!!.parent == node) {
            return node
        }
        return findRoot(nodeMap[node]!!.parent)

    }


    private fun union(src: Point, des: Point) {
        when {
            nodeMap[src]!!.rank > nodeMap[des]!!.rank -> {
                nodeMap[des]?.parent = src

            }
            nodeMap[src]!!.rank < nodeMap[des]!!.rank -> {
                nodeMap[src]?.parent = des


            }
            else -> {
                nodeMap[src]!!.parent = des
                nodeMap[des]!!.rank++


            }
        }

    }

    private fun createIfNotExist(src: Point, des: Point) {
        if (!nodeMap.containsKey(src))
            nodeMap[src] = UnionNode(src, 0)
        if (!nodeMap.containsKey(des))
            nodeMap[des] = UnionNode(des, 0)


    }

}