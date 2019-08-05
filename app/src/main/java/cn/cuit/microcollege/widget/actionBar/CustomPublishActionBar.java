package cn.cuit.microcollege.widget.actionBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public class CustomPublishActionBar extends LinearLayout {
    @BindView(R.id.custom_actionbar_back_img)
    ImageView mBackImg;
    @BindView(R.id.custom_actionbar_title)
    TextView mTitleTv;
    @BindView(R.id.custom_publish_btn)
    TextView mPublishTv;
    private setBarListener listener;

    public void setBarListener(setBarListener listener) {
        this.listener = listener;
    }

    public CustomPublishActionBar(Context context) {
        this(context, null);
    }

    public CustomPublishActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_publish_actionbar, this, true);
        ButterKnife.bind(this);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        int attributeCount = attrs.getAttributeCount();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomDefaultActionBar);

        for (int i = 0; i < attributeCount; i++) {
            int index = typedArray.getIndex(i);

            switch (index) {
                case R.styleable.CustomDefaultActionBar_cs_text:
                    CharSequence titleContent = typedArray.getText(index);
                    if (titleContent != null) {
                        mTitleTv.setText(titleContent.toString());
                    }
                    break;
                case R.styleable.CustomDefaultActionBar_cs_btn_text://按钮的文字
                    CharSequence btnText = typedArray.getText(index);
                    if (btnText != null) {
                        mPublishTv.setText(btnText.toString());
                    }
                    break;

                case R.styleable.CustomDefaultActionBar_cs_btn_text_enable://按钮可用
                    boolean text_btn_enable = typedArray.getBoolean(index, false);
                    mPublishTv.setEnabled(text_btn_enable);
                    break;
            }
        }
        typedArray.recycle();
    }

    @OnClick({R.id.custom_actionbar_back_img, R.id.custom_actionbar_title, R.id.custom_publish_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_actionbar_back_img:
                if (listener != null) {
                    listener.onBack();
                }
                break;
            case R.id.custom_actionbar_title:
                break;

            case R.id.custom_publish_btn:
                if (listener != null) {
                    listener.onPublish();
                }
                break;
        }
    }

    public void setTitle(String content) {
        if (!TextUtils.isEmpty(content)) {
            mTitleTv.setText(content);
        }
    }

    public void setPublishBtnEnable(boolean enable) {
        mPublishTv.setEnabled(enable);
    }

    public String getTitle() {
        return mTitleTv.getText().toString();
    }

    public interface setBarListener {
        void onBack();

        void onPublish();
    }
}
