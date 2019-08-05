package cn.cuit.microcollege.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public class CustomTelInputView extends RelativeLayout implements TextWatcher {
    private static final String TAG = "来自自定义控件：CustomTelInputView";
    @BindView(R.id.custom_telinput_telContent_et)
    EditText mTelContentET;
    @BindView(R.id.custom_clear_iv)
    ImageView mClearIv;
    private TextWatcher mTextWatcher;

    public CustomTelInputView(Context context) {
        this(context, null);
    }

    public void addTextChangedListener(TextWatcher listener){
        mTextWatcher = listener;
    }

    public CustomTelInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_telinputview, this);
        ButterKnife.bind(this);
        mTelContentET.addTextChangedListener(this);
//        initAttrs(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        String defTel = "";//设置默认

        TypedArray typeArr = context.obtainStyledAttributes(attrs, R.styleable.CustomTelInputView);
        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            int attr = typeArr.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTelInputView_cs_text:
                    CharSequence text = typeArr.getText(attr);
                    if (text == null) {
                        break;
                    }
                    defTel = text.toString();
                    break;
                default:
                    break;
            }
        }
        typeArr.recycle();

        mTelContentET.setText(defTel);
    }


    /**
     * 获取tel
     *
     * @return
     */
    public String getTelContent() {

        Editable text = mTelContentET.getText();
        boolean empty = TextUtils.isEmpty(text);
        if (empty) {
            return null;
        }
        return text.toString();
    }

    @OnClick(R.id.custom_clear_iv)
    public void onClick() {
        mTelContentET.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mTextWatcher != null) {
            mTextWatcher.beforeTextChanged(s,start,count,after);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().equals("")) {
            mClearIv.setVisibility(INVISIBLE);
        } else {
            mClearIv.setVisibility(VISIBLE);
        }
        if (mTextWatcher != null) {
            mTextWatcher.onTextChanged(s,start,before,count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mTextWatcher != null) {
            mTextWatcher.afterTextChanged(s);
        }
    }

    public interface TextWatcher {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable s);
    }
}
