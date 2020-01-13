package com.google.engedu.touringmusician

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

class MainActivity : AppCompatActivity() {
    private var map: TourMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<View>(R.id.top_layout) as LinearLayout
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 30.0f)
        map = TourMap(this)
        map!!.layoutParams = params
        layout.addView(map, 0)
        setUpPopUpMenu()

    }

    fun onReset(v: View?) {
        map!!.reset()
        val message = findViewById<View>(R.id.game_status) as TextView
        message.text = getString(R.string.startMessage)
    }
    private fun setUpPopUpMenu(){
        val modeButton = findViewById<View>(R.id.mode_selector) as Button
        modeButton.setOnClickListener {
            val popup = PopupMenu(this@MainActivity, modeButton)
            popup.menuInflater
                    .inflate(R.menu.modepopup, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                map!!.setInsertMode(item.title.toString())
                true
            }
            popup.show()
        }
    }
}