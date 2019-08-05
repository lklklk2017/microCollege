package cn.cuit.microcollege.widget.actionBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
public class CustomDefaultCenterActionBar extends LinearLayout {
    @BindView(R.id.custom_actionbar_back_img)
    ImageView mBackImg;
    @BindView(R.id.custom_actionbar_title)
    TextView mTitleTv;
    private setBarListener listener;

    public void setBarListener(setBarListener listener) {
        this.listener = listener;
    }

    public CustomDefaultCenterActionBar(Context context) {
        this(context, null);
    }

    public CustomDefaultCenterActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_default_center_actionbar, this, true);
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

                case R.styleable.CustomDefaultActionBar_cs_img:
                    Drawable drawable = typedArray.getDrawable(index);
                    if (drawable != null) {
                        mBackImg.setImageDrawable(drawable);
                    }
            }
        }
        typedArray.recycle();
    }

    @OnClick({R.id.custom_actionbar_back_img, R.id.custom_actionbar_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_actionbar_back_img:
                if (listener != null) {
                    listener.onLeftBtnClick();
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

    public void setImage(Drawable drawable){
        if (drawable != null) {
            mBackImg.setImageDrawable(drawable);
        }
    }

    public interface setBarListener {
        void onLeftBtnClick();
    }
}
