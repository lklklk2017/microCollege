package cn.cuit.microcollege.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class CustomDetailViewPager extends ViewPager {

    private int current;
    private int height = 0;
    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
    private boolean scrollble = true;

    public CustomDetailViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomDetailViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mChildrenViews.size() > current) {
            View child = mChildrenViews.get(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 重新设置高度
     * @param current
     */
    public void resetHeight(int current) {

        this.current = current;
        if (mChildrenViews.size() > current) {
            ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }

    /**
     * 保存key 和 key对应的view
     * @param view
     * @param position
     */
    public void setObjectForPosition(View view, int position)
    {
        mChildrenViews.put(position, view);
    }

}
