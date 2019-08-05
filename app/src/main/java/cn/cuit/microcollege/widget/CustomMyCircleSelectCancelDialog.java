package cn.cuit.microcollege.widget;

import android.content.Context;
import android.view.View;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseDialog;


/**
 * Created by Administrator on 2018/4/6.
 */

public class CustomMyCircleSelectCancelDialog extends BaseDialog implements View.OnClickListener {

    private ContentClickListener listener;

    public CustomMyCircleSelectCancelDialog(Context context) {
        this(context, true, R.layout.dialog_circle_select);
    }

    public CustomMyCircleSelectCancelDialog(Context context, Boolean canTouchCancel, int layoutResId) {
        this(context, canTouchCancel, layoutResId, null);
    }

    public CustomMyCircleSelectCancelDialog(Context context, Boolean canTouchCancel, int layoutResId, ContentClickListener listener) {
        super(context, canTouchCancel, layoutResId);
        this.listener = listener;
    }

    public CustomMyCircleSelectCancelDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public void seContentClickListener(ContentClickListener listener) {
        this.listener = listener;
    }


    @Override
    protected void initWindow() {
        View view = getWindow().getDecorView();
        view.findViewById(R.id.dialog_sel1_tv).setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_sel1_tv:
                if (listener != null) {
                    listener.onDialogItemOne();
                    dismiss();
                }
                break;
        }
    }

    public interface ContentClickListener {
        void onDialogItemOne();
    }
}
