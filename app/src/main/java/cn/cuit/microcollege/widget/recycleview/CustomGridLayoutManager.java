package cn.cuit.microcollege.widget.recycleview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption:
 */
public class CustomGridLayoutManager extends GridLayoutManager {

    private boolean isScrollEnabled = true;


    public CustomGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollView(boolean enable) {
        this.isScrollEnabled = enable;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
