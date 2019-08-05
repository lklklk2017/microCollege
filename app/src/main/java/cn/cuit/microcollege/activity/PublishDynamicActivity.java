package cn.cuit.microcollege.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.ImageCollectorAdapter;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.PublishDynamicActivityContract;
import cn.cuit.microcollege.entity.DataEntity.CircleDataBean;
import cn.cuit.microcollege.entity.DataEntity.ImageDataBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.presenter.PublishDynamicActivityPresenter;
import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.ResourceUtil;
import cn.cuit.microcollege.widget.actionBar.CustomPublishActionBar;
import cn.cuit.microcollege.widget.recycleview.CustomGridLayoutManager;

public class PublishDynamicActivity extends BaseActivity<PublishDynamicActivityPresenter> implements PublishDynamicActivityContract.View, ImageCollectorAdapter.OnItemClickListener, CustomPublishActionBar.setBarListener, TextWatcher, ViewTreeObserver.OnGlobalLayoutListener {

    @BindView(R.id.activity_publish_actionBar)
    CustomPublishActionBar mBar;
    @BindView(R.id.activity_publish_publish_content_et)
    EditText mPublishContentEt;
    @BindView(R.id.activity_publish_imagesContent_rcy)
    RecyclerView mImagesContentRcy;
    @BindView(R.id.activity_publish_imagesWrapper_lyt)
    LinearLayout mImagesWrapperLyt;
    @BindView(R.id.activity_publish_circleImg_img)
    ImageView mTagCircleImg;
    @BindView(R.id.activity_publish_circle_name_tv)
    TextView mTagCircleNameTv;
    @BindView(R.id.activity_publish_locationImg_img)
    ImageView mTagLocationImg;
    @BindView(R.id.activity_publish_location_name_tv)
    TextView mTagLocationNameTv;
    @BindView(R.id.activity_publish_circleTag)
    LinearLayout mCircleTag;
    @BindView(R.id.activity_publish_locationTag)
    LinearLayout mLocationTag;
    private ArrayList<ImageDataBean> mImageUrlList;
    private ImageCollectorAdapter mImagesAdatper;
    private int currPosition = 0;
    public static final int REQUEST_CODE_PHOTO = 1;//照片
    public static final int REQUEST_CODE_CIRCLE = 2;//圈子
    public static final int REQUEST_CODE_LOCATION = 3;//位置
    private GetMyCircleResultBean.CirclesBean selectedBean;

    /**
     * 数据回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PHOTO:
                if (resultCode == RESULT_CANCELED) {
                    //取消选择照片
                    return;
                }
                //开始读取照片数据
                Uri uriData = data.getData();
                //获取绝对路径
                String realPath = ResourceUtil.getRealPathFromURI(uriData, this);
                LogU.i("获取的绝对路径：" + realPath, getClass().getName());

//                if (realPath != null) {
//
//                    File file = FileUtil.makeFile(PHOTO_FILE_PATH, "Hello.txt");
//                }

                if (realPath != null) {
                    //添加并更新列表
                    mImagesAdatper.addItemDesc(new ImageDataBean(1, realPath));

                    if (currPosition == 8) {//最后一个图片的时候移除加号
                        mImagesAdatper.removeItem(9);
                    }
                    int itemCount = mImagesAdatper.getItemCount();
                    if (itemCount > 1) {
                        mBar.setPublishBtnEnable(true);
                    }
                }
                break;
            case REQUEST_CODE_CIRCLE://从mycircle返回的结果
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Bundle dataFromMyCircle = data.getBundleExtra("dataFromMyCircle");
                        if (dataFromMyCircle != null) {
                            GetMyCircleResultBean.CirclesBean getBean = dataFromMyCircle.getParcelable("selectedBean");
                            if (getBean != null) {
                                selectedBean = getBean;
                                mTagCircleImg.setImageResource(R.drawable.circle_topic_blue);
                                mTagCircleNameTv.setText(selectedBean.getCircleName().trim());
                                mTagCircleNameTv.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
                            }
                        }
                    }
                }
                break;

            case REQUEST_CODE_LOCATION:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_dynamic);
        ButterKnife.bind(this);
    }

    @Override
    protected void preStatusForOther() {
        Intent intent = getIntent();
        if (intent != null) {
            String from = intent.getStringExtra("from");
            Bundle data = intent.getBundleExtra("data");
            if (from!=null&&from.equals("circle_detail")) {
                if (data != null) {
                    CircleDataBean circleFromDetail = data.getParcelable("circle_bean");
                    if (circleFromDetail != null) {
                        //开始分装本地数据
                        selectedBean = new GetMyCircleResultBean.CirclesBean();
                        selectedBean.setCircleId(circleFromDetail.getCircleId());//id
                        selectedBean.setCircleName(circleFromDetail.getCircleName());//name
                        selectedBean.setCircleTitle(circleFromDetail.getCircleTitle());//title
                        selectedBean.setCircleAvaterUrl(circleFromDetail.getCircleAvaterUrl());//avater url
                        selectedBean.setCircleTopicImgUrl(circleFromDetail.getCircleTopicImgUrl());//topic imgurl
                        selectedBean.setCircleCreatedTime(circleFromDetail.getCircleCreatedTime());//creat-time
                        selectedBean.setDynamicJoinedCount(circleFromDetail.getDynamicJoinedCount());//join dy count
                        selectedBean.setUserJoinedCount(circleFromDetail.getUserJoinedCount());//join user count
                        selectedBean.setFounderId(circleFromDetail.getFounderId());//foundid
                    }
                }
            }
        }
        //intent为空
    }

    @Override
    public void initView() {
        //rcy
        CustomGridLayoutManager layout = new CustomGridLayoutManager(this, 3);
        layout.setScrollView(false);
        mImagesContentRcy.setLayoutManager(layout);
        mImageUrlList = new ArrayList<>();
        mImageUrlList.add(new ImageDataBean(0, R.drawable.add));//默认添加按钮
        mImagesAdatper = new ImageCollectorAdapter(mImageUrlList);
        mImagesAdatper.setContext(this);
        mImagesContentRcy.setAdapter(mImagesAdatper);
        mImagesContentRcy.setItemAnimator(new DefaultItemAnimator());
        mImagesContentRcy.setHasFixedSize(true);
    }

    @Override
    public PublishDynamicActivityPresenter initPresenter() {
        return new PublishDynamicActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        //actionbar
        mBar.setBarListener(this);
        mImagesAdatper.setListener(this);
        mPublishContentEt.addTextChangedListener(this);
        mImagesContentRcy.getViewTreeObserver().addOnGlobalLayoutListener(this);
        mCircleTag.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectedBean = null;
                mTagCircleImg.setImageResource(R.drawable.circle_topic_white);
                mTagCircleNameTv.setText("圈子名称");
                mTagCircleNameTv.setTextColor(Color.WHITE);
                return true;
            }
        });
    }


    @Override
    protected void initData() {
        if (selectedBean != null) {
            //开始设置内容-圈子信息
            mTagCircleImg.setImageResource(R.drawable.circle_topic_blue);
            mTagCircleNameTv.setText(selectedBean.getCircleName().trim());
            mTagCircleNameTv.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
        }
    }


    @OnClick({R.id.activity_publish_circleTag, R.id.activity_publish_locationTag})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_publish_circleTag://关联圈子
//                List<ImageDataBean> imageDataBeans = mImagesAdatper.getmList();
//                StringBuffer sb = new StringBuffer();
//                for (ImageDataBean imageDataBean : imageDataBeans) {
//                    sb.append(imageDataBean.getImgUrl().toString());
//                    sb.append("\n");
//                }
//                LogU.i("所有的文件路径：\n" + sb.toString(), getClass().getName());

                Intent intent_tocircle = new Intent(this, MyCircleActivity.class);
                intent_tocircle.putExtra("from", Config.FLAG_PUBLISH);
                startActivityForResult(intent_tocircle, REQUEST_CODE_CIRCLE);
                break;
            case R.id.activity_publish_locationTag://关联位置

                break;
        }
    }

    /***************************************image item click call back*********************************************/
    @Override
    public void onItemClick(int position) {
        toast("点击了" + position + "个", false);
    }

    @Override
    public void onAddItemClick(int position) {
        currPosition = position;
        toast("点击了添加按钮 position:" + position, false);
        //add
        //1.send intent
        //配合  ResourceUtil.GetRealPathFromURI
        // 完美获取图片路径  (文件+相册都可以)
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    @Override
    public void onItemLongClick(final int position) {
        toast("确认删除？", "确认", 8000, new ToastCallBack() {
            @Override
            public void onEvent() {
                //add +
                int itemCount = mImagesAdatper.getItemCount();
                List<ImageDataBean> imageDataBeans = mImagesAdatper.getmList();
                boolean hasPlus = false;

                for (ImageDataBean imageDataBean : imageDataBeans) {
                    int type = imageDataBean.getType();
                    if (type == 0) {
                        hasPlus = true;
                    }
                }

                /**
                 * 添加+ 的情况满足以下条件：
                 *  1.当前数量为9
                 *  2.当前没有+
                 */
                if (itemCount == 9 && !hasPlus) {
                    //delete
                    mImagesAdatper.removeItem(position);
                    //add
                    mImagesAdatper.addItemAse(new ImageDataBean(0, R.drawable.add));
                } else {
                    //delete
                    mImagesAdatper.removeItem(position);
                }

                int newItemCount = mImagesAdatper.getItemCount();
                if (newItemCount == 1) {
                    mBar.setPublishBtnEnable(false);
                }
            }
        });
    }

    /***************************************bar call back*********************************************/
    @Override
    public void onBack() {
        finish();
    }

    @Override
    public void onPublish() {
//        toast("发布逻辑", false);
        getPresent().publishTask();
    }

    /***************************************text watch back*********************************************/

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null && !"".equals(s.toString().trim())) {
            mBar.setPublishBtnEnable(true);
        } else if (s != null && s.toString().equals("")) {
            mBar.setPublishBtnEnable(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /***************************************on rcy view tree change*********************************************/

    @Override
    public void onGlobalLayout() {
        mImagesContentRcy.requestLayout();
    }

    @Override
    public GetMyCircleResultBean.CirclesBean getCircleBean() {
        return selectedBean;
    }

    @Override
    public String getLocal() {

        //todo 介入地图后的工作
        return null;
    }

    @Override
    public ImageCollectorAdapter getImgAdapter() {
        return mImagesAdatper;
    }

    @Override
    public String getPublishContent() {
        return mPublishContentEt.getText().toString();
    }
}
