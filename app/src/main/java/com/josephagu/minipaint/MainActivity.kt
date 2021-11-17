package com.josephagu.minipaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Create an instance of MyCanvasView.
        val myCanvasView = MyCanvasView(this)

     // request the full screen for the layout of myCanvasView by setting the SYSTEM_UI_FLAG_FULLSCREEN
        // flag on myCanvasView. In this way, the view completely fills the screen.
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        // add a content description
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        setContentView(myCanvasView)
    }
}