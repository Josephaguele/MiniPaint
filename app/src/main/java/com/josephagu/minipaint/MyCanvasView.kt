package com.josephagu.minipaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.View
import androidx.core.content.res.ResourcesCompat

// at the top file level, define a constant for the stroke width
private const val STROKE_WIDTH = 12f // has to be float

class MyCanvasView (context: Context) : View(context) {

    //At the class level of MyCanvasView, define a variable drawColor for holding the color to draw
    // with and initialize it with the colorPaint resource you defined earlier.
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    //At the class level, below, add a variable paint for a Paint object and initialize it as follows.
    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }

/*  add a variable path and initialize it with a Path object to store the path that is being drawn
    when following the user's touch on the screen. Import android.graphics.Path for the Path.
*/
    private var path = Path()

    /*At the class level, we define member variables for a canvas and a bitmap.
    These are the bitmap and canvas for caching what has been drawn before.*/
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    /*Define a class level variable backgroundColor, for the background color of the canvas and
    initialize it to the colorBackground you defined earlier.*/
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)

    /*In MyCanvasView, override the onSizeChanged() method. This callback method is called by the
    Android system with the changed screen dimensions, that is, with a new width and height
     (to change to) and the old width and height (to change from).*/
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        //Inside onSizeChanged(), create an instance of Bitmap with the new width and height,
        // which are the screen size, and assign it to extraBitmap. The third argument is the
        // bitmap color configuration. ARGB_8888 stores each color in 4 bytes and is recommended.
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        //Create a Canvas instance from extraBitmap and assign it to extraCanvas.
        extraCanvas = Canvas(extraBitmap)

        //Specify the background color in which to fill extraCanvas.
        extraCanvas.drawColor(backgroundColor)

        /*Looking at onSizeChanged(), a new bitmap and canvas are created every time the function
        executes. You need a new bitmap, because the size has changed. However, this is a memory
        leak, leaving the old bitmaps around. To fix this, recycle extraBitmap before creating the
        next one.*/
        //if (::extraBitmap.isInitialized) extraBitmap.recycle()
    }

/*
    The drawBitmap() Canvas method comes in several versions. In this code, you provide the bitmap,
     the x and y coordinates (in pixels) of the top left corner, and null for the Paint, as you'll set that later.
 */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }
    //Note: The 2D coordinate system used for drawing on a Canvas is in pixels, and the origin (0,0) is at the top left corner of the Canvas.


}