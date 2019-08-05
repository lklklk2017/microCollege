package cn.cuit.microcollege.design;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.support.design.widget.Snackbar.*;

import cn.cuit.microcollege.utils.LogU;


/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption:
 */
public class FloatActionBarBehavior extends CoordinatorLayout.Behavior {
    public FloatActionBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        LogU.i("执行了behavior，translationY：" + translationY, this.getClass().getName());
        return true;
    }
}
