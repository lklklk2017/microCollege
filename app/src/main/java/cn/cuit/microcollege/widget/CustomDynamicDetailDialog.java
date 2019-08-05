package cn.cuit.microcollege.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseDialog;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/11
 * @Descirption:
 */
public class CustomDynamicDetailDialog extends BaseDialog {

    @BindView(R.id.dialog_sel1_tv)
    TextView dialogSel1Tv;
    @BindView(R.id.dialog_sel2_tv)
    TextView dialogSel2Tv;
    @BindView(R.id.dialog_sel3_tv)
    TextView dialogSel3Tv;
    @BindView(R.id.dialog_sel4_tv)
    TextView dialogSel4Tv;

    public CustomDynamicDetailDialog(Context context, Boolean canTouchCancel, int layoutResId) {
        super(context, canTouchCancel, layoutResId);
    }

    public CustomDynamicDetailDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CustomDynamicDetailDialog(Context context) {
        this(context, true, R.layout.dialog_dynamic_detail_select);
        ButterKnife.bind(this);
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected void initListener() {

    }

    private OnSeclectClick listener;

    public OnSeclectClick getListener() {
        return listener;
    }

    public void setListener(OnSeclectClick listener) {
        this.listener = listener;
    }

    @OnClick({R.id.dialog_sel1_tv, R.id.dialog_sel2_tv, R.id.dialog_sel3_tv, R.id.dialog_sel4_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_sel1_tv:
                if (listener != null) {
                    listener.onItemOne();
                    dismiss();
                }
                break;
            case R.id.dialog_sel2_tv:
                if (listener != null) {
                    listener.onItemTwo();
                    dismiss();
                }
                break;
            case R.id.dialog_sel3_tv:
                if (listener != null) {
                    listener.onItemThree();
                    dismiss();
                }
                break;
            case R.id.dialog_sel4_tv:
                if (listener != null) {
                    listener.onItemDelete();
                    dismiss();
                }
                break;
        }
    }

    public interface OnSeclectClick {
        void onItemOne();

        void onItemTwo();

        void onItemThree();

        void onItemDelete();
    }
}
