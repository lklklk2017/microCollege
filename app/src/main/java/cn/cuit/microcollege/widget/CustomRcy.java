package cn.cuit.microcollege.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/5
 * @Descirption:
 */
public class CustomRcy extends RecyclerView {
    public CustomRcy(Context context) {
        super(context);
    }

    public CustomRcy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        super.onTouchEvent(e);

        boolean result = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogU.i("cutom-rcy-DOWN", this.getClass().getName());

                break;
            case MotionEvent.ACTION_MOVE:
                LogU.i("cutom-rcy-MOVE", this.getClass().getName());
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                LogU.i("cutom-rcy-UP", this.getClass().getName());
                break;
        }

        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogU.i("cutom-rcy- dispatchTouchEvent", this.getClass().getName());

        return super.dispatchTouchEvent(ev);
    }
}
