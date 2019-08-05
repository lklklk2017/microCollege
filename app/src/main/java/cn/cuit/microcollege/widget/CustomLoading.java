package cn.cuit.microcollege.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.utils.TransFormUtil;


/**
 * Created by Administrator on 2018/4/22.
 */

public class CustomLoading extends View {

    private int mCircleColor;
    private int mCircleWidth;
    private Paint mPaint;
    private int mProgress = -90;
    private int mDefWidth = 100;
    private int mDefHeight = 100;
    private int radius;
    private int min;
    private int mArcColor;
    private RectF mRect;
    private int radius_max;
    private boolean isReduce = true;
    private int mProgress_Second = 180;
    private int mArcColor_Second;

    public CustomLoading(Context context) {
        this(context, null);
    }

    public CustomLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomLoading);
        int indexCount = a.getIndexCount();

        for (int i = 0; i < indexCount; i++) {
            int attr = a.getIndex(i);//获取单个属性值对应的索引下标
            switch (attr) {
                case R.styleable.CustomLoading_circleColor:
                    mCircleColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomLoading_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomLoading_arc_color:
                    mArcColor = a.getColor(attr, Color.RED);
                    mArcColor_Second = a.getColor(attr, Color.RED);
                    break;
            }
        }

        a.recycle();

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {

                    mProgress++;

                    if (mProgress == 270) {
                        mProgress = -90;
                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }).start();

        /**
         * 第二个圈
         */
        new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {

                    mProgress_Second--;

                    if (mProgress_Second == -180) {
                        mProgress_Second = 180;
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();

                }
            }
        }).start();

        /**
         * 缩圈速度
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isReduce) {
                        radius--;
                        if (radius <= radius_max*0.7) {
                            isReduce = false;
                            mArcColor_Second = getColor();
                        }
                    } else {
                        radius++;
                        if (radius >= radius_max) {
                            isReduce = true;
                        }
                    }
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }).start();

        //控制外圈颜色
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mArcColor = getColor();
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 产生随机颜色
     * @return
     */
    private int getColor() {
        int red = 0;
        int green = 0;
        int blue = 0;
        int alpha = 0;
        Random random = new Random(System.currentTimeMillis());
        red = random.nextInt(256);
        green = random.nextInt(256);
        blue = random.nextInt(256);
        alpha = random.nextInt(256);
        return Color.argb(255, red, green, blue);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int w_size = MeasureSpec.getSize(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int h_size = MeasureSpec.getSize(heightMeasureSpec);


        if (w_mode == MeasureSpec.AT_MOST && h_mode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefWidth, mDefHeight);
        } else if (w_mode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefWidth, h_size);
        } else if (h_mode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(w_size, mDefHeight);
        }
        min = Math.min(getWidth(), getHeight());
        radius_max = (int) (min / 2 - TransFormUtil.dp2px(getContext(), 10));
        radius = radius_max;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画外圈
        mPaint.setColor(mArcColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth * 1f);
        int radius_rec = (int) (radius - TransFormUtil.dp2px(getContext(), 10));
        mRect = new RectF(getWidth() / 2 - radius_rec, getHeight() / 2 - radius_rec, getWidth() / 2 + radius_rec, getHeight() / 2 + radius_rec);
        canvas.drawArc(mRect, mProgress, 80, false, mPaint);
        canvas.drawArc(mRect, mProgress + 180, 80, false, mPaint);

        //画第一圈里圈
        mPaint.setColor(mArcColor_Second);
        mPaint.setStrokeWidth(mCircleWidth * 3);
        int radius_rec_second = (int) (radius - TransFormUtil.dp2px(getContext(), 30));
        mRect = new RectF(getWidth() / 2 - radius_rec_second, getHeight() / 2 - radius_rec_second, getWidth() / 2 + radius_rec_second, getHeight() / 2 + radius_rec_second);
        canvas.drawArc(mRect, mProgress_Second, 50, false, mPaint);
        canvas.drawArc(mRect, mProgress_Second + 180, 50, false, mPaint);

        //画第二圈里圈
        mPaint.setColor(mArcColor_Second);
        mPaint.setStrokeWidth(mCircleWidth * 0.5f);
        int radius_rec_third = (int) (radius - TransFormUtil.dp2px(getContext(), 50));
        mRect = new RectF(getWidth() / 2 - radius_rec_third, getHeight() / 2 - radius_rec_third, getWidth() / 2 + radius_rec_third, getHeight() / 2 + radius_rec_third);
        canvas.drawArc(mRect, mProgress_Second, 50, false, mPaint);
        canvas.drawArc(mRect, mProgress_Second + 180, 50, false, mPaint);
    }
}
