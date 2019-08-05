package cn.cuit.microcollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.CommentAdapter;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.CommentDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.presenter.CommentDetailActivityPresenter;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

public class CommentDetailActivity extends BaseActivity<CommentDetailActivityPresenter> implements CommentDetailActivityContract.View, CommentAdapter.OnItemClickListener, CustomDefaultActionBar.setBarListener {

    @BindView(R.id.comment_detail_actionbar)
    CustomDefaultActionBar mActionbar;
    @BindView(R.id.comment_detail_portrait_img)
    ImageView mPortraitImg;
    @BindView(R.id.comment_detail_name_tv)
    TextView mNameTv;
    @BindView(R.id.comment_detail_date_tv)
    TextView mDateTv;
    @BindView(R.id.comment_detail_main_content_tv)
    TextView mMainContentTv;
    @BindView(R.id.comment_detail_comment_rcy)
    RecyclerView mCommentRcy;
    @BindView(R.id.comment_detail_no_result)
    LinearLayout mNoResult;
    @BindView(R.id.comment_detail_comment_content_et)
    EditText mCommentContentEt;
    @BindView(R.id.comment_detail_comment_send_tv)
    TextView mSendTv;
    @BindView(R.id.rcyItem_comment_img)
    ImageView mCommentImg;
    @BindView(R.id.rcyItem_comment_count_tv)
    TextView mCommentCountTv;
    @BindView(R.id.comment_detail_content_lyt)
    LinearLayout mContentLyt;
    private int mCurrToId = -1;
    private int mCurrPage = 1;
    private CommentAdapter mCommentAdapter;
    private LinearLayoutManager mLm;
    private int mLastCompletePositon;
    private int itemCount;
    private GetCommentResultBean.CommentsBean bean = new GetCommentResultBean.CommentsBean();


    public GetCommentResultBean.CommentsBean getCommentBean() {
        return bean;
    }

    public void setCommentBean(GetCommentResultBean.CommentsBean bean) {
        this.bean = bean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void preStatusForOther() {
        Intent intent = getIntent();
        if (intent != null) {
            String from = intent.getStringExtra("from");
            Bundle data = intent.getBundleExtra("data");

            if (from != null && data != null) {
                if (from.equals("DynamicDetailActivity")) {
                    //来自动态详情
                    bean = data.getParcelable("comment");
                }
            } else {
                toast("comment 数据获取失败！", false);
            }
        }

    }

    @Override
    public void initView() {
        mLm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCommentRcy.setLayoutManager(mLm);
        mCommentRcy.addItemDecoration(new CustomItemDecoration(0, 10, 0, 5));
        mCommentAdapter = new CommentAdapter();
        mCommentRcy.setAdapter(mCommentAdapter);
    }

    @Override
    public CommentDetailActivityPresenter initPresenter() {
        return new CommentDetailActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        mActionbar.setBarListener(this);

        mCommentAdapter.setmListener(this);

        mCommentRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastCompletePositon = mLm.findLastCompletelyVisibleItemPosition();
                itemCount = recyclerView.getAdapter().getItemCount();
                if (itemCount - 1 == mLastCompletePositon) {
                    getPresent().getCommetAddListTask(++mCurrPage);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getPresent().initMainComment();
        getPresent().getCommetTask(getCurrentPage());
    }

    @OnClick({R.id.comment_detail_portrait_img, R.id.comment_detail_name_tv, R.id.comment_detail_comment_send_tv, R.id.comment_detail_content_lyt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_detail_portrait_img:
                //TODO 通往个人主页

                break;
            case R.id.comment_detail_name_tv:
                //TODO 通往个人主页

                break;
            case R.id.comment_detail_comment_send_tv:
                //TODO 添加评论
                getPresent().addCommetTask(1);
                break;
            case R.id.comment_detail_content_lyt:
                getCommentEt().setFocusable(true);
                getCommentEt().setFocusableInTouchMode(true);
                getCommentEt().requestFocus();
                showInput(getCommentEt());
                break;
        }
    }

    @Override
    public ImageView getPortait() {
        return mPortraitImg;
    }

    @Override
    public TextView getNameTv() {
        return mNameTv;
    }

    @Override
    public TextView getDateTv() {
        return mDateTv;
    }

    @Override
    public TextView getCommentCountTv() {
        return mCommentCountTv;
    }

    @Override
    public TextView getMainCommentTv() {
        return mMainContentTv;
    }

    @Override
    public int getCurrToId() {
        return mCurrToId;
    }

    @Override
    public void setCurrToId(int currToId) {
        mCurrToId = currToId;
    }

    @Override
    public int getCurrCommentType() {
        return 1;
    }

    @Override
    public CommentAdapter getCommentAdapter() {
        return mCommentAdapter;
    }

    @Override
    public RecyclerView getCommentRcy() {
        return mCommentRcy;
    }

    @Override
    public EditText getCommentEt() {
        return mCommentContentEt;
    }

    @Override
    public int getCurrentPage() {
        return mCurrPage;
    }

    @Override
    public int setCurrentPage(int page) {
        return mCurrPage = page;
    }

    @Override
    public void showNoComment() {
        getCommentRcy().setVisibility(View.GONE);
        mNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasComment() {
        getCommentRcy().setVisibility(View.VISIBLE);
        mNoResult.setVisibility(View.GONE);
    }

    /******************* 评论item click call back************************************************/
    @Override
    public void onItemClick(int position) {
        getCommentEt().setFocusable(true);
        getCommentEt().setFocusableInTouchMode(true);
        getCommentEt().requestFocus();
        showInput(getCommentEt());
    }

    @Override
    public void onBack() {
        finish();
    }
}