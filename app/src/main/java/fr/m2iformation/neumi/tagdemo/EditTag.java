package fr.m2iformation.neumi.tagdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class EditTag extends AppCompatImageView implements View.OnTouchListener {

    private Color tagColor;
    private Integer tagWeight;
    private Bitmap fingerTag;
    private Color bkgColor;
    Paint paint;
    Path path;

    public EditTag(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }


    public EditTag(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();

    }

    public Color getTagColor() {
        return tagColor;
    }

    public void setTagColor(Color tagColor) {
        tagColor = tagColor;
    }

    public int getTagWeight() {
        return tagWeight;
    }

    public void setTagWeight(Integer tagWeight) {
        tagWeight = tagWeight;
    }

    public Bitmap getFingerTag() {
        return fingerTag;
    }

    public void setFingerTag(Bitmap tag) {
        fingerTag = fingerTag;
    }

    public Color getBkgColor() {
        return bkgColor;
    }

    public void setBkgColor(Color bkgColor) {
        this.bkgColor = bkgColor;
    }

    public void clear() {
        path.reset();
        invalidate();
    }

    public void saveJpg(String fichier) {
    }

    public void savePng(String fichier) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            path.moveTo(event.getX(), event.getY());
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.lineTo(event.getX(), event.getY());
        }
        invalidate();
        return true;
    }
}
