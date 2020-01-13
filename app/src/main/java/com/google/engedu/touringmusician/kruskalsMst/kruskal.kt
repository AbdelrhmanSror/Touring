package com.google.engedu.touringmusician.kruskalsMst

import android.graphics.Point
import com.google.engedu.touringmusician.Edge
import java.util.*
import kotlin.collections.LinkedHashMap


object EdgeComparator : Comparator<Edge> {
    override fun compare(o1: Edge, o2: Edge): Int {
        return when {
            o1.weight < o2.weight -> -1
            o1.weight > o2.weight -> 1
            else -> 0
        }
    }
}

object Kruskal {

    fun findMST(tourLinkedList: HashMap<Point, Edge>): LinkedHashMap<Point, Edge> {
        //priority queue to sort all the edges in ascending order using its weight
        val edgesQueue = arrayListOf<Edge>()
        //hash set to store minimum spanning tree
        val constructedGraph = LinkedHashMap<Point, Edge>()
        val cycleDetection = CycleDetection()

        tourLinkedList.forEach {
            it.let { edgesQueue.add(it.value) }
        }
        /**
         * priority queue iterator  does not guarantee to traverse the elements of the priority queue in any particular order
         * so i have to make a work around by calling poll on priority queue because for sure it will return the least item in order
         */
        edgesQueue.sortedWith(EdgeComparator).forEach {
            /**
             * if cycle not exist then we add it to [constructedGraph]
             */

            if (!cycleDetection.isCycleExist(it.point to it.next)) {
                //if there is no cycle we add the point as it is
                constructedGraph[it.point] = it
            } else {
                //if there is cycle we change the next of the current point to point to it self instead
                //so the line that will cause cycle will not exist
                val edge = Edge(it.point)
                edge.prev = it.point
                constructedGraph[it.point] = edge

            }

        }
        return constructedGraph
    }


}



