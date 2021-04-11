package com.rafsan.drawNpaint.utilities;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawNPaint extends View {
    public Bitmap  mBitmap;
    public Canvas  mCanvas;
    private Path    mPath;
    private Paint   mBitmapPaint;
    private Paint   mPaint;


    public DrawNPaint(Context c, AttributeSet attrs) {
        super(c, attrs);

        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFF000000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(5);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (DrawingFlags.INSTANCE.getBITMAP_SELECTED() !=null) {
            mBitmap = Bitmap.createScaledBitmap(DrawingFlags.INSTANCE.getBITMAP_SELECTED(),w, h, false);
        } else {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("STATUS","DRAW: "+ DrawingFlags.INSTANCE.getDRAW());
        if (DrawingFlags.INSTANCE.getDRAW()) {
            drawNow(event);
        } else {
            fillNow(event);
        }
        return true;
    }

    private void fillNow(MotionEvent event) {
        paint((int)event.getX(),(int)event.getY());
        invalidate();
    }

    private void drawNow(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
    }

    public Bitmap getBitmap()
    {
        //this.measure(100, 100);
        //this.layout(0, 0, 100, 100);
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bmp;
    }

    public void clear(){
        DrawingFlags.INSTANCE.setBITMAP_SELECTED(null);
        mBitmap.eraseColor(Color.WHITE);
        invalidate();
        System.gc();

    }

    private void paint(int x, int y) {
        Log.i("PIXELS", String.valueOf(mBitmap.getPixel(x,y)));
        int targetColor = mBitmap.getPixel(x,y);
        ColorFill floodFill = new ColorFill();
        floodFill.colorFill(mBitmap,new Point(x,y),targetColor, DrawingFlags.INSTANCE.getCOLOR_SELECTED());
        invalidate();
    }
}
