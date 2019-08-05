package cn.cuit.microcollege.widget.actionBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/26
 * @Descirption:
 */
public class CustomSearchBar extends LinearLayout implements TextWatcher {

    @BindView(R.id.custom_icon)
    ImageView mIcon;
    private SearchBarListener listener;
    private boolean canClean = true;

    public void setCanClean(boolean canClean) {
        this.canClean = canClean;
    }

    public void setListener(SearchBarListener listener) {
        this.listener = listener;
    }

    @BindView(R.id.custom_searchbar_back_img)
    ImageView mBackImg;
    @BindView(R.id.custom_searchBar_Content_et)
    EditText mContentEt;
    @BindView(R.id.custom_clear_img)
    ImageView mClearImg;

    public CustomSearchBar(Context context) {
        this(context, null);
    }

    public CustomSearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_searchbar, this, true);
        ButterKnife.bind(this);
        initAttr(context, attrs);
        initDefaultViewState();
    }

    private void initDefaultViewState() {
        mClearImg.setVisibility(INVISIBLE);
        mContentEt.addTextChangedListener(this);
    }

    public void initAttr(Context context, @Nullable AttributeSet attrs) {
        TypedArray typeArr = context.obtainStyledAttributes(attrs, R.styleable.CustomSearchBar);
        int attributeCount = attrs.getAttributeCount();

        for (int i = 0; i < attributeCount; i++) {
            int type = typeArr.getIndex(i);
            switch (type) {
                case R.styleable.CustomSearchBar_cs_hint:
                    String content = typeArr.getText(type).toString();
                    LogU.i("这是获取到的内容：" + content, getClass().getName());
                    if (content != null) {
                        mContentEt.setHint(content);
                    }
                    break;
                case R.styleable.CustomSearchBar_cs_img:
                    Drawable drawable = typeArr.getDrawable(type);
                    if (drawable != null) {
                        mIcon.setImageDrawable(drawable);
                    }
                    break;
            }
        }
        typeArr.recycle();
    }

    @OnClick({R.id.custom_searchbar_back_img, R.id.custom_clear_img, R.id.custom_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_searchbar_back_img:
                if (listener != null) {
                    listener.onBack();
                }
                break;
            case R.id.custom_clear_img:
                if (!canClean) {
                    return;
                }
                mContentEt.setText("");
                mClearImg.setVisibility(INVISIBLE);
                if (listener != null) {
                    listener.onClear();
                }
                break;

            case R.id.custom_icon:
                Editable text = mContentEt.getText();
                if (text == null) {
                    return;
                }
                if (listener != null) {
                    listener.onSearch(text.toString().trim());
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals("")) {
            mClearImg.setVisibility(VISIBLE);
        } else {
            mClearImg.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public EditText getEd() {
        if (mContentEt != null) {
            return mContentEt;
        }
        return null;
    }

    public interface SearchBarListener {
        void onBack();

        void onSearch(String content);

        void onClear();//search 获取焦点
    }

    public void setSearchBarEditTextFoucs(boolean foucs) {

        mContentEt.setFocusable(foucs);
        mContentEt.setFocusableInTouchMode(foucs);
        if (foucs) {
            mContentEt.requestFocus();
            mContentEt.findFocus();
        }
    }

}
