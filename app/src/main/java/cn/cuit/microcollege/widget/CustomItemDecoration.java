package cn.cuit.microcollege.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/4/5.
 * recycle 中的间隔自定义
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private int color;
    private int left = 0;
    private int right = 0;
    private int top = 5;
    private int bottom = 10;

    public CustomItemDecoration(int color) {
        this.color = color;
    }

    public CustomItemDecoration() {

    }

    public  CustomItemDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.drawColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(left, top, right, bottom);
    }

    public CustomItemDecoration setDecor(int left, int top, int right, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        return this;
    }

    public CustomItemDecoration setColor(int color) {
        this.color = color;
        return this;
    }
}
