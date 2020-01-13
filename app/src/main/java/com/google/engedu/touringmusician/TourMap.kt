package com.google.engedu.touringmusician

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

class TourMap(context: Context?) : View(context) {
    private val mapImage: Bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.map)
    private val tour = TourLinkedList()
    private val pointPaint = Paint()
    private var insertMode = "Add"
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mapImage, 0f, 0f, null)
        pointPaint.strokeWidth = 10f
        for (p in tour.beginEdges) {
            pointPaint.color = Color.GREEN
            canvas.drawCircle(p.key.x.toFloat(), p.key.y.toFloat(), 20f, pointPaint)
            //draw line between the current point and the next point to it
            canvas.drawLine(p.key.x.toFloat(), p.key.y.toFloat(), p.value.next.x.toFloat(), p.value.next.y.toFloat(), pointPaint)
        }
        for (p in tour.smallestEdges) {
            pointPaint.color = Color.RED

            canvas.drawCircle(p.key.x.toFloat(), p.key.y.toFloat(), 20f, pointPaint)
            //draw line between the current point and the next point to it
            canvas.drawLine(p.key.x.toFloat(), p.key.y.toFloat(), p.value.next.x.toFloat(), p.value.next.y.toFloat(), pointPaint)
        }
        for (p in tour.nearestEdges) {
            pointPaint.color = Color.BLUE

            canvas.drawCircle(p.key.x.toFloat(), p.key.y.toFloat(), 20f, pointPaint)
            //draw line between the current point and the next point to it
            canvas.drawLine(p.key.x.toFloat(), p.key.y.toFloat(), p.value.next.x.toFloat(), p.value.next.y.toFloat(), pointPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val p = Point(event.x.toInt(), event.y.toInt())
                when (insertMode) {
                    "Closest" -> tour.insertNearest(p)
                    "Smallest" -> tour.insertSmallest(p)
                    else -> tour.insertBeginning(p)
                }
                val message = (context as Activity).findViewById<View>(R.id.game_status) as TextView
                message.text = String.format("Tour length Begin is now %.2f \n Tour length Near is %.2f \n Tour length Small is %.2f ", tour.totalDistance().first.first, tour.totalDistance().first.second, tour.totalDistance().second)
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun reset() {
        tour.reset()
        invalidate()
    }

    fun setInsertMode(mode: String) {
        insertMode = mode
    }

}