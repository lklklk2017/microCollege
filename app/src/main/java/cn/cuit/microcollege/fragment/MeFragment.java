package cn.cuit.microcollege.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.FeedBackActivity;
import cn.cuit.microcollege.activity.LoginActivity;
import cn.cuit.microcollege.activity.MyCircleActivity;
import cn.cuit.microcollege.activity.about.AboutActivity;
import cn.cuit.microcollege.activity.about.PersonDetailActivity;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.MeFragmentContract;
import cn.cuit.microcollege.presenter.MeFragmentPresenter;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class MeFragment extends BaseFragment<MeFragmentPresenter> implements
        MeFragmentContract.View {

    @BindView(R.id.me_fragment_portrait_img)
    ImageView mPortraitImg;
    @BindView(R.id.me_fragment_name_tv)
    TextView mNameTv;
    @BindView(R.id.me_fragment_sign_tv)
    TextView meSignTv;
    @BindView(R.id.me_fragment_baseInfo_cardv)
    CardView mBaseInfoCardv;
    @BindView(R.id.me_fragment_item_one_cardv)
    CardView mItemOneCardv;
    @BindView(R.id.me_fragment_item_two_cardv)
    CardView mItemTwoCardv;
    @BindView(R.id.me_fragment_item_three_cardv)
    CardView mThreeCardv;
    @BindView(R.id.me_fragment_item_four_cardv)
    CardView mFourCardv;
    @BindView(R.id.me_fragment_item_five_cardv)
    CardView mItemFiveCardv;
    Unbinder unbinder;
    private AlertDialog mAlertDialogClean;
    private AlertDialog mAlertDialogExit;

    public MeFragment() {

    }

    public static MeFragment newInstance(Bundle bundle) {

        MeFragment fragment = new MeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        setCreatedView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public MeFragmentPresenter initPresenter() {
        return new MeFragmentPresenter(this);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initData() {
        // base info
        String avatarImgUrl = SPUtil.getmUserSp().getString("avatarImgUrl", "");
        if (avatarImgUrl != null) {
            Glide.with(this)
                    .load(avatarImgUrl)
                    .apply(RequestOptions.bitmapTransform(new CenterCrop()).placeholder(R.drawable.logo_temp))
                    .into(mPortraitImg);
        }

        String sign = SPUtil.getmUserSp().getString("sign", "");
        if (sign != null) {
            meSignTv.setText(sign);
        }
    }

    @Override
    protected void initDialog() {
        super.initDialog();
        //alert
        AlertDialog.Builder builder_clean = new AlertDialog.Builder(getActivity())
                .setTitle("清理缓存")
                .setMessage("所有缓存将会被清除")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清除缓存
                        cleanCache();
                    }
                })//-1
                .setNegativeButton("取消", null);//-2
        mAlertDialogClean = builder_clean.create();

        //dialog-exit
        AlertDialog.Builder builder_exit = new AlertDialog.Builder(getActivity())
                .setTitle("退出登录")
                .setMessage("退出登录后,下次登录需要重新载入账户数据")
                .setPositiveButton("退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //退出
                        toPage(LoginActivity.class);
                        getActivity().finish();
                    }
                })//-1
                .setNegativeButton("取消", null);//-2
        mAlertDialogExit = builder_exit.create();
    }

    private void cleanCache() {

        toast("已清理完毕所有缓存", false);
    }

    @Override
    public void bindListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.me_fragment_baseInfo_cardv, R.id.me_fragment_item_one_cardv, R.id.me_fragment_item_two_cardv, R.id.me_fragment_item_three_cardv, R.id.me_fragment_item_four_cardv, R.id.me_fragment_item_five_cardv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_fragment_baseInfo_cardv:
                Intent intent = new Intent(MyApplication.getMyContext(), PersonDetailActivity.class);
                intent.putExtra("from", "person");
                startActivity(intent);
                break;
            case R.id.me_fragment_item_one_cardv:
                toPage(MyCircleActivity.class);
                break;
            case R.id.me_fragment_item_two_cardv:
                toPage(FeedBackActivity.class);
                break;
            case R.id.me_fragment_item_three_cardv:
                toPage(AboutActivity.class);
                break;
            case R.id.me_fragment_item_four_cardv:
                mAlertDialogClean.show();
                mAlertDialogClean.getWindow().setBackgroundDrawableResource(R.drawable.me_fragment_dialog_bg);
                mAlertDialogClean.getWindow().setLayout(950, LinearLayout.LayoutParams.WRAP_CONTENT);
                break;
            case R.id.me_fragment_item_five_cardv:
                mAlertDialogExit.show();
                mAlertDialogExit.getWindow().setBackgroundDrawableResource(R.drawable.me_fragment_dialog_bg);
                mAlertDialogExit.getWindow().setLayout(950, LinearLayout.LayoutParams.WRAP_CONTENT);
                break;
        }
    }
}
