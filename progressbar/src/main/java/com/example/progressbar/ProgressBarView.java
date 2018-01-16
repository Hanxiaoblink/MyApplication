package com.example.progressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressBarView extends View {
  private Paint progressPaint;
  private Paint backgroundPaint;
  private float strokeWidth = 10f;
  private int strokeColor = Color.RED;
  private int solidColor = Color.BLUE;
  private int progress = 0;

  public ProgressBarView(Context context) {
    super(context);
  }

  public ProgressBarView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
    setWillNotDraw(false);
    if (attrs != null) {
      TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressBarView);
      strokeWidth = typedArray.getDimension(R.styleable.ProgressBarView_stroke_width, 0f);
      strokeColor = typedArray.getColor(R.styleable.ProgressBarView_stroke_color, Color.RED);
      solidColor = typedArray.getColor(R.styleable.ProgressBarView_solid_color, Color.BLUE);
      typedArray.recycle();
    }
    progressPaint = new Paint();
    progressPaint.setColor(solidColor);
    progressPaint.setStrokeWidth(strokeWidth);
    progressPaint.setStyle(Paint.Style.STROKE);
    progressPaint.setAntiAlias(true);

    backgroundPaint = new Paint();
    backgroundPaint.setStyle(Paint.Style.STROKE);
    backgroundPaint.setColor(strokeColor);
    backgroundPaint.setStrokeWidth(strokeWidth);
    backgroundPaint.setAntiAlias(true);

  }

  @Override
  @SuppressLint("DrawAllocation")
  protected void onDraw(Canvas canvas) {
    //canvas.drawColor(Color.WHITE);
    super.onDraw(canvas);
    int sc = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
    progressPaint.setColor(Color.WHITE);
    RectF rectF = new RectF(strokeWidth / 2, strokeWidth / 2, getWidth() - strokeWidth / 2, getHeight() - strokeWidth / 2);
    canvas.drawRoundRect(rectF, (getHeight() - strokeWidth) / 2, (getHeight() - strokeWidth) / 2, progressPaint);
    progressPaint.setXfermode(new PorterDuffXfermode((PorterDuff.Mode.SRC_IN)));
    int progressMaxWidth = (int) (getWidth() - 2 * strokeWidth);
    RectF solidRectF = new RectF(strokeWidth, strokeWidth, strokeWidth+progress / 100f * progressMaxWidth, getHeight() - strokeWidth);
    progressPaint.setStyle(Paint.Style.FILL);
    progressPaint.setColor(solidColor);
    canvas.drawRect(solidRectF, progressPaint);
    progressPaint.setXfermode(null);
    canvas.restoreToCount(sc);
    canvas.drawRoundRect(rectF, (getHeight() - strokeWidth) / 2, (getHeight() - strokeWidth) / 2, backgroundPaint);
  }

  public void setProgress(int progress) {
    if (progress < 0) {
      this.progress = 0;
    } else if (progress > 100) {
      this.progress = 100;
    } else {
      this.progress = progress;
    }
    invalidate();
  }
}

