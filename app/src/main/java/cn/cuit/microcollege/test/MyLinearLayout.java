package cn.cuit.microcollege.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
@SuppressLint("AppCompatCustomView")
public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogU.i("调用了MyLinearLayout的onTouchEvent", this.getClass().getName());

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        LogU.i("调用了MyLinearLayout的dispatchTouchEvent", this.getClass().getName());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        float x = ev.getX();
        float y = ev.getY();

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
