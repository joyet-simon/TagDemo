package fr.m2iformation.neumi.tagdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileOutputStream;

public class EditTag extends AppCompatImageView implements View.OnTouchListener {

    private Integer tagColor;
    private Integer tagWeight;
    private Bitmap fingerTag;
    private Integer bkgColor;
    Paint paint;
    Path path;


    private OnOutListener onOutListener;

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
        tagColor = Color.BLACK;
        setOnTouchListener(this);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        setTagColor(attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "tagColor", tagColor));
        setTagWeight(attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "tagWeight", 5));
        setBkgColor(attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "bkgColor", Color.WHITE));

    }

    public Integer getTagColor() {
        return tagColor;
    }

    public void setTagColor(Integer color) {
        this.tagColor = color;
        paint.setColor(tagColor);
        invalidate();
    }

    public int getTagWeight() {
        return tagWeight;
    }

    public void setTagWeight(Integer tagWeight) {
        this.tagWeight = tagWeight;
        paint.setStrokeWidth(tagWeight);
        invalidate();
    }

    public Bitmap getFingerTag() {
        buildDrawingCache();
        Bitmap bitmap = getDrawingCache();
//        Bitmap bitmap = ((BitmapDrawable)getDrawable()).getBitmap();

        fingerTag = bitmap;
        return fingerTag;
    }

    public void setFingerTag(Bitmap fingertag) {
        setImageBitmap(fingerTag);
        this.fingerTag = fingertag;
    }

    public Integer getBkgColor() {
        return bkgColor;
    }

    public void setBkgColor(Integer bkgColor) {
        this.bkgColor = bkgColor;
        setBackgroundColor(bkgColor);
        invalidate();
    }

    public void setOnOutListener(OnOutListener onOutListener) {
        this.onOutListener = onOutListener;
    }

    public void clear() {
        path.reset();
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveJpg(String fichier) {
        try (FileOutputStream out = new FileOutputStream(fichier)) {
            getFingerTag().compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void savePng(String fichier) {
        try (FileOutputStream out = new FileOutputStream(fichier)) {
            getFingerTag().compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            path.moveTo(event.getX(), event.getY());
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.lineTo(event.getX(), event.getY());
        }
        if (event.getX() < 2 || event.getY() < 2 || event.getX() > getWidth() - 2 || event.getY() > getHeight() - 2) {
            if (onOutListener != null) {
                onOutListener.onOut(v);
            }
        }
        invalidate();
        return true;
    }

    public interface OnOutListener {
        void onOut(View view);
    }

}



