package cn.cuit.microcollege.widget;

import android.content.Context;
import android.widget.ImageView;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseDialog;


/**
 * Created by Administrator on 2018/4/6.
 */

public class CustomShowDialog extends BaseDialog {

    private ImageView mImg;

    public CustomShowDialog(Context context, Boolean canTouchCancel, int layoutResId) {
        super(context, canTouchCancel, layoutResId);

    }

    public CustomShowDialog(Context context) {
        super(context, false, R.layout.dialog_during);

    }

    public CustomShowDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void initWindow() {
    }

    @Override
    protected void initListener() {

    }
}
