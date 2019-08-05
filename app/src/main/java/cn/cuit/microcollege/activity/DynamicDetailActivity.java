package cn.cuit.microcollege.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.CommentAdapter;
import cn.cuit.microcollege.adapter.DynamicDetailContentAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.DynamicDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.presenter.DynamicDetailActivityPresenter;
import cn.cuit.microcollege.widget.CustomDynamicDetailDialog;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

public class DynamicDetailActivity extends BaseActivity<DynamicDetailActivityPresenter>
        implements
        DynamicDetailActivityContract.View,
        CommentAdapter.OnItemClickListener,
        CustomDefaultActionBar.setBarListener,
        DynamicDetailContentAdapter.TrendItemClick,
        CustomDynamicDetailDialog.OnSeclectClick {

    @BindView(R.id.dynamic_detail_actionbar)
    CustomDefaultActionBar mActionbar;
    @BindView(R.id.dynamic_detail_content_rcy)
    RecyclerView mContentRcy;
    @BindView(R.id.dynamic_detail_comment_rcy)
    RecyclerView mCommentRcy;
    @BindView(R.id.dynamic_detail_no_result)
    LinearLayout mNoResult;
    @BindView(R.id.dynamic_detail_comment_content_et)
    EditText mCommentContentEt;
    @BindView(R.id.dynamic_detail_comment_send_tv)
    TextView mCommentSendTv;
    private LinearLayoutManager mContentLm;
    private LinearLayoutManager mCommentLm;
    private DynamicDetailContentAdapter mContentAdapter;
    private CommentAdapter mCommentAdapter;
    private int mCurrPage = 1;
    private int mDynamicId = -1;
    private GetDynamicResultBean.TrendsBean trendsBean;
    private GetDynamicWithCircleResultBean.TrendsBean trendsWithCircleBean;
    private int mLastCompletePositon;
    private int itemCount;

    private int currToId = 0;//默认的被评论者是动态评论者 但是数据不知道 默认为0 确保type为0 即可
    private int mCurrCommentType = 0;//默认是对动态userid 发起的评论
    private CustomDynamicDetailDialog mDynamicDialog;
    private ClipboardManager mClipBord;
    private int mCurrCommentSelectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynaimc_detail);
        ButterKnife.bind(this);
    }

    @Override
    public GetDynamicResultBean.TrendsBean getTrendsBean() {
        return trendsBean;
    }

    @Override
    public GetCommentResultBean.CommentsBean getCommentBean() {
        return getCommentAdapter().getmList().get(mCurrCommentSelectedPosition);
    }

    @Override
    protected void preStatusForOther() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            String from = intent.getStringExtra("from");

            if (data != null && from != null) {
                if (from.equals("HomePageOne")) {
                    //page one
                    trendsBean = new GetDynamicResultBean.TrendsBean();
                    trendsBean = data.getParcelable("trend");
                    mDynamicId = trendsBean.getDynamicId();
                } else if (from.equals("HomePage")) {
                    //page two or three
                    trendsBean = new GetDynamicResultBean.TrendsBean();
                    trendsWithCircleBean = data.getParcelable("trend");

                    //temp
                    trendsBean.setDynamicId(trendsWithCircleBean.getDynamicId());
                    trendsBean.setDynamicUserAvaterUrl(trendsWithCircleBean.getDynamicUserAvaterUrl());
                    trendsBean.setDynamicUserName(trendsWithCircleBean.getDynamicUserName());
                    trendsBean.setDynamicType(trendsWithCircleBean.getDynamicType());
                    trendsBean.setPublishTime(trendsWithCircleBean.getPublishTime());
                    trendsBean.setDynamicContent(trendsWithCircleBean.getDynamicContent());
                    trendsBean.setImageList(trendsWithCircleBean.getImageList());
                    trendsBean.setLikeCount(trendsWithCircleBean.getLikeCount());
                    trendsBean.setCommentCount(trendsWithCircleBean.getCommentCount());

                    //开始封装当前数据
                    GetDynamicResultBean.TrendsBean.CircleBean circleBean = new GetDynamicResultBean.TrendsBean.CircleBean();
                    circleBean.setCircleName(trendsWithCircleBean.getCircle().getCircleName());
                    circleBean.setCircleId(trendsWithCircleBean.getCircle().getCircleId());
                    circleBean.setCircleTitle(trendsWithCircleBean.getCircle().getCircleTitle());
                    circleBean.setCircleAvaterUrl(trendsWithCircleBean.getCircle().getCircleTopicImgUrl());
                    circleBean.setCircleTopicImgUrl(trendsWithCircleBean.getCircle().getCircleTopicImgUrl());
                    circleBean.setCircleCreatedTime(trendsWithCircleBean.getCircle().getCircleCreatedTime());
                    circleBean.setDynamicJoinedCount(trendsWithCircleBean.getCircle().getDynamicJoinedCount());
                    circleBean.setUserJoinedCount(trendsWithCircleBean.getCircle().getUserJoinedCount());
                    circleBean.setFounderId(trendsWithCircleBean.getCircle().getFounderId());
                    trendsBean.setCircle(circleBean);

                    //get id
                    mDynamicId = trendsBean.getDynamicId();

                } else if (from.equals("other")) {
                    //...
                }
            }

        } else {
            toast("获取动态信息失败", false);
        }
    }

    @Override
    public void initView() {
        mContentLm = new LinearLayoutManager(MyApplication.getMyContext());
        mCommentLm = new LinearLayoutManager(MyApplication.getMyContext());
        mContentLm.setOrientation(LinearLayoutManager.VERTICAL);
        mCommentLm.setOrientation(LinearLayoutManager.VERTICAL);

        mContentRcy.setLayoutManager(mContentLm);
        mCommentRcy.setLayoutManager(mCommentLm);

        mContentRcy.addItemDecoration(new CustomItemDecoration(0, 10, 0, 5));
        mCommentRcy.addItemDecoration(new CustomItemDecoration(0, 10, 0, 5));

        mContentAdapter = new DynamicDetailContentAdapter();
        mCommentAdapter = new CommentAdapter();

        mContentRcy.setAdapter(mContentAdapter);
        mCommentRcy.setAdapter(mCommentAdapter);
    }

    @Override
    public DynamicDetailActivityPresenter initPresenter() {
        return new DynamicDetailActivityPresenter(this);
    }

    @Override
    public void initDialog() {
        super.initDialog();
        mDynamicDialog = new CustomDynamicDetailDialog(this);
    }

    @Override
    public void bindListener() {
        mContentAdapter.setListener(this);
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
                mLastCompletePositon = mCommentLm.findLastCompletelyVisibleItemPosition();
                itemCount = recyclerView.getAdapter().getItemCount();
                if (itemCount - 1 == mLastCompletePositon) {
                    getPresent().getCommetAddListTask(++mCurrPage);
                }
            }
        });

        //dialog
        mDynamicDialog.setListener(this);
    }

    @Override
    protected void initData() {
//        default
        //comment btn
        String dynamicUserName = trendsBean.getDynamicUserName();
        if (dynamicUserName != null) {
            mCommentContentEt.setHint("回复:" + dynamicUserName);
        }

        getPresent().initDynamicTask();
        getPresent().getCommetTask(getCurrentPage());
    }

    @Override
    public DynamicDetailContentAdapter getContentAdapter() {
        return mContentAdapter;
    }

    @Override
    public CommentAdapter getCommentAdapter() {
        return mCommentAdapter;
    }

    @Override
    public RecyclerView getContentRcy() {
        return mContentRcy;
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
    public int getDynamicId() {
        return mDynamicId;
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

    /***************comment call back**************************************************************/
    @Override
    public void onItemClick(int position) {
        //点击评论item
        mCurrCommentSelectedPosition = position;
        setCurrCommentType(1);
        setCurrToId(getCommentAdapter().getmList().get(mCurrCommentSelectedPosition).getUser().getUserId());
        mDynamicDialog.show();
    }

    @Override
    public void onBack() {
        hideInput();
        finish();
    }

    /*********************************content call back **********************************************/
    @Override
    public void onItemClick(GetDynamicResultBean.TrendsBean trendsBean) {
    }

    @Override
    public void onLikeClick(int position) {
        getPresent().addLike(getContentAdapter().getmList().get(position).getDynamicId() + "");
    }

    @Override
    public void onCommentClick() {
        //添加评论
        setCurrCommentType(0);
        getCommentEt().setFocusable(true);
        getCommentEt().setFocusableInTouchMode(true);
        getCommentEt().requestFocus();
        getCommentEt().setHint("回复:" + getTrendsBean().getDynamicUserName());
        showInput(mCommentContentEt);
    }

    @Override
    public void onCircleTagClick(GetDynamicResultBean.TrendsBean trendsBean) {
        Intent intent = new Intent(MyApplication.getMyContext(), CircleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("circle", trendsBean.getCircle());
        intent.putExtra("data", bundle);
        intent.putExtra("from", "DynamicDetailActivity");
        startActivity(intent);
    }

    /**
     * 添加评论点击事件
     */
    @OnClick(R.id.dynamic_detail_comment_send_tv)
    public void onClick() {
        getPresent().addCommetTask(getCurrCommentType());
    }

    /************************** 关于默认 被评论者 id 的设置**************************************/
    public int getCurrToId() {
        return currToId;
    }

    public void setCurrToId(int currToId) {
        this.currToId = currToId;
    }

    @Override
    public int getCurrCommentType() {
        return mCurrCommentType;
    }

    @Override
    public void setCurrCommentType(int type) {
        this.mCurrCommentType = type;
    }

    /************************** dialog call back**************************************/
    @Override
    public void onItemOne() {
        //回复

        getCommentEt().setFocusable(true);
        getCommentEt().setFocusableInTouchMode(true);
        getCommentEt().requestFocus();
        getCommentEt().setHint("回复:" + getCommentAdapter().getmList().get(mCurrCommentSelectedPosition).getUser().getUsername());
        showInput(mCommentContentEt);
    }

    @Override
    public void onItemTwo() {
        //复制
        mClipBord = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData comment_from_item = ClipData.newPlainText("comment from item", getCommentAdapter().getmList().get(mCurrCommentSelectedPosition).getCommentContent());
        mClipBord.setPrimaryClip(comment_from_item);
        Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemThree() {
        //查看详情
        GetCommentResultBean.CommentsBean commentsBean = getCommentAdapter().getmList().get(mCurrCommentSelectedPosition);
        Intent intent = new Intent(MyApplication.getMyContext(), CommentDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("comment", commentsBean);
        intent.putExtra("from", "DynamicDetailActivity");
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    @Override
    public void onItemDelete() {
        //删除逻辑
        getPresent().deleteComment();
    }

    public void updateView() {
        getCommentAdapter().removeItem(mCurrCommentSelectedPosition);
    }
}