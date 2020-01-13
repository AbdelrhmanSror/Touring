package com.google.engedu.touringmusician

import android.graphics.Color
import android.graphics.Point
import com.google.engedu.touringmusician.kruskalsMst.Kruskal


@Suppress("KDocUnresolvedReference")
class TourLinkedList {

    //linked hash map that store the edges in the order they have been added
    var beginEdges = LinkedHashMap<Point, Edge>()
        private set
    //linked hash map that store the edges in the order they have been added
    var nearestEdges = LinkedHashMap<Point, Edge>()
        private set
    //linked hash map that store the edges in the order they have been added
    var smallestEdges = LinkedHashMap<Point, Edge>()
        private set
    private var head: Point? = null


    /**
     * will return total distance of each graph individually
     * @return [totalDistance.first.first] is the total distances result in inserting nodes in beginning
     * @return [totalDistance.first.second] is the total distances result in inserting nodes in near
     * @return [totalDistance.second] is the total distances result in inserting nodes in minimum spanning tree
     */
    fun totalDistance(): Pair<Pair<Float, Float>, Float> {
        return (beginEdges.totalDistance()) to (nearestEdges.totalDistance()) to (smallestEdges.totalDistance())
    }


    fun reset() {
        beginEdges.clear()
        smallestEdges.clear()
        nearestEdges.clear()

    }


    /**
     * @return true  and will create head if it was null
     * @return false otherwise
     */
    private fun HashMap<Point, Edge>.createHeadIfNull(p: Point): Boolean {
        if (head == null || this.size < 1) {
            head = p
            this[p] = Edge(p)
            return true
        }
        return false
    }

    /**
     * will insert point after the closest point to it
     */

    fun insertNearest(p: Point) {
        if (!nearestEdges.createHeadIfNull(p)) {
            val closestNode = nearestEdges.closestPoint(p = p)
            nearestEdges.addNode(p, closestNode, nearestEdges[closestNode]!!.next)
        }
    }

    /**
     * will insert the point to its nearest point and will update the [edges] so it will have minimum spanning tree
     */
    fun insertSmallest(p: Point) {
        if (!smallestEdges.createHeadIfNull(p)) {
            val closestNode = smallestEdges.closestPoint(p = p)
            smallestEdges.addNode(p, closestNode, smallestEdges[closestNode]!!.next)
            //if the size is less than 2 so there won't be definitely any cycles to detect
            if (smallestEdges.size > 2) {
                smallestEdges = Kruskal.findMST(smallestEdges)
            }
        }
    }


    fun insertBeginning(p: Point) {
        //if the list was empty we create new one and make the head point at itself
        if (!beginEdges.createHeadIfNull(p)) {
            //we place the node at the beginning so make its next (the head it self)
            //and the its previous will be the previous of the head
            //also we make the next of previous of the head points to that new node
            beginEdges.addNode(p, beginEdges[head]!!.prev, head!!)
            //set the new head with current point added to beginning
            head = p

        }

    }


}
