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
public class CustomDefaultActionBar extends LinearLayout {
    @BindView(R.id.custom_actionbar_back_img)
    ImageView mBackImg;
//    @BindView(R.id.custom_actionbar_icon)
//    ImageView mIconImg;
    @BindView(R.id.custom_actionbar_title)
    TextView mTitleTv;
    private setBarListener listener;

    public void setBarListener(setBarListener listener) {
        this.listener = listener;
    }

    public CustomDefaultActionBar(Context context) {
        this(context, null);
    }

    public CustomDefaultActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_default_actionbar, this, true);
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
            }
        }
        typedArray.recycle();
    }

    @OnClick({R.id.custom_actionbar_back_img, R.id.custom_actionbar_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_actionbar_back_img:
                if (listener != null) {
                    listener.onBack();
                }
                break;
            case R.id.custom_actionbar_title:
                break;
        }
    }

    public void setTitle(String content) {
        if (!TextUtils.isEmpty(content)) {
            mTitleTv.setText(content);
        }
    }

    public String getTitle() {
        return mTitleTv.getText().toString();
    }

    public interface setBarListener {
        void onBack();
    }
}
