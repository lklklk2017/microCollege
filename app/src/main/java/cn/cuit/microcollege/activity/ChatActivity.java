package cn.cuit.microcollege.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.ChatActivityContract;
import cn.cuit.microcollege.presenter.ChatActivityPresenter;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

/**
 * @Author: Rod Eden
 * @Date: 2019/5/5
 * @Description: 聊天界面
 */
public class ChatActivity extends BaseActivity<ChatActivityPresenter> implements ChatActivityContract.View, CustomDefaultActionBar.setBarListener {

    @BindView(R.id.activity_chat_actionbar)
    CustomDefaultActionBar mActionbar;
    @BindView(R.id.activity_chat_rcy)
    RecyclerView mRcy;
    @BindView(R.id.activity_chat_edit_et)
    EditText mEdit;
    @BindView(R.id.activity_chat_send_tv)
    TextView mSendTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        //action bar

    }

    @Override
    public ChatActivityPresenter initPresenter() {
        return new ChatActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        mActionbar.setBarListener(this);

    }

    @OnClick(R.id.activity_chat_send_tv)
    public void onClick() {
        //发送逻辑
    }

    @Override
    public void onBack() {
        finish();
    }
}
