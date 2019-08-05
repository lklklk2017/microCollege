package cn.cuit.microcollege.widget.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public class CustomRecycleView extends RecyclerView {
    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }
}
