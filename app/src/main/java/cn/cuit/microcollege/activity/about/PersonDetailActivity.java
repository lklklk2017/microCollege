package cn.cuit.microcollege.activity.about;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.PersonDetailEditActivity;
import cn.cuit.microcollege.adapter.DetailFragmentPagerAdapter;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.PersonDetailContract;
import cn.cuit.microcollege.entity.DataEntity.UserDataBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.fragment.person.PersonDetailPageOneFragment;
import cn.cuit.microcollege.fragment.person.PersonDetailPageTwoFragment;
import cn.cuit.microcollege.presenter.PersonDetailPresenter;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.TransFormUtil;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Rod Eden
 * @Date: 2019/5/3
 * @Description: 个人信息
 */
public class PersonDetailActivity extends BaseActivity<PersonDetailPresenter> implements
        PersonDetailContract.View, ViewPager.OnPageChangeListener {

    @BindView(R.id.activity_person_detail_bg_img)
    ImageView mDetailBgImg;
    @BindView(R.id.activity_person_detail_back)
    ImageView mBack;
    @BindView(R.id.activity_person_detail_portrait_img)
    ImageView mPortraitImg;
    @BindView(R.id.activity_person_detail_username_tv)
    TextView mNameTv;
    @BindView(R.id.activity_person_detail_job_tv)
    TextView mJobTv;
    @BindView(R.id.activity_person_detail_job_tag_lyt)
    LinearLayout mJobTagLyt;
    @BindView(R.id.activity_person_detail_gender_tag_lyt)
    LinearLayout mGenderTagLyt;
    @BindView(R.id.activity_person_detail_place_tv)
    TextView mPlaceTv;
    @BindView(R.id.activity_person_detail_place_tag_lyt)
    LinearLayout mPlaceTagLyt;
    @BindView(R.id.activity_person_detail_college_tv)
    TextView mCollegeTv;
    @BindView(R.id.activity_person_detail_college_tag_lyt)
    LinearLayout mCollegeTagLyt;
    @BindView(R.id.activity_person_detail_circleCreatedCount_text_tv)
    TextView mCircleCreatedCountTextTv;
    @BindView(R.id.activity_person_detail_circleCreatedCount_lyt)
    LinearLayout mCircleCreatedCountLyt;
    @BindView(R.id.activity_person_detail_circleJoinedCount_text_tv)
    TextView mlCircleJoinedCountTextTv;
    @BindView(R.id.activity_person_detail_circleJoinedCount_lyt)
    LinearLayout mCircleJoinedCountLyt;
    @BindView(R.id.activity_person_detail_frendsCount_text_tv)
    TextView mDynamicCountTextTv;
    @BindView(R.id.activity_person_detail_frendsCount_lyt)
    LinearLayout mDynamicCountLyt;
    @BindView(R.id.activity_person_detail_collapseToolBarLyt)
    CollapsingToolbarLayout mCollapseToolBarLyt;
    @BindView(R.id.activity_person_detail_tabLyt)
    TabLayout mTabLyt;
    @BindView(R.id.activity_person_detail_appBarLyt)
    AppBarLayout mAppBarLyt;
    @BindView(R.id.activity_person_detail_vp)
    CustomViewPager mVp;
    @BindView(R.id.activity_person_detail_nsv)
    NestedScrollView mNsv;
    @BindView(R.id.activity_person_detail_cooLyt)
    CoordinatorLayout mCooLyt;
    @BindView(R.id.activity_person_detail_edit_lyt)
    LinearLayout mEditLyt;
    @BindView(R.id.activity_person_detail_edit_tv)
    TextView activityPersonDetailEditTv;
    @BindView(R.id.activity_person_detail_gender_img)
    ImageView mGenderImg;
    private PersonDetailPageTwoFragment mPersonBaseInfoFragment;
    private String[] titles = new String[]{"动态", "个人信息"};
    private DetailFragmentPagerAdapter mVpAdpater;
    private ArrayList<Fragment> mFragmentList;
    private PersonDetailPageOneFragment mPersonDetailFragment;
    private UserDataBean userDataBean;
    private int state = -1;
    private static final int PERSON_STATE = 1;
    private static final int OTHER_STATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*沉浸式状态栏*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void preStatusForOther() {
        Intent intent = getIntent();
        if (intent != null) {
            String from = intent.getStringExtra("from");
            Bundle data = intent.getBundleExtra("data");
            if (from != null && from.equals("person")) {//个人
                personInfo();
                state = PERSON_STATE;
            }
            if (from != null && from.equals("other")) {//其他人
                if (data != null) {
                    otherInfo(data);
                    state = OTHER_STATE;
                }
            }
        }
    }

    /**
     * 封装他人数据
     */
    private void otherInfo(Bundle data) {
        GetUserBaseInfoResultBean.UserListBean userBean = data.getParcelable("userBean");

        userDataBean = new UserDataBean();
        //birthday
        String birthday = userBean.getBirthday();
        if (birthday != null) {
            userDataBean.setBirthday(TransFormUtil.getLocalDate(birthday));
        }
        //gender
        String gender = userBean.getGender();
        userDataBean.setGender(gender);
        //loginState
        int loginState = userBean.getLoginState();
        userDataBean.setLoginState(loginState);
        //campus
        String campus = userBean.getCampus();
        if (campus != null) {
            userDataBean.setCampus(campus);
        }
        //circle add count
        int circleAddedCount = userBean.getCircleAddedCount();
        userDataBean.setCircleAddedCount(circleAddedCount);
        //circle add count
        String sign = userBean.getSign();
        if (sign != null) {
            userDataBean.setSign(sign);
        }
        //email
        String email = userBean.getEmail();
        userDataBean.setEmail(email);
        //job
        String industry = userBean.getIndustry();
        userDataBean.setIndustry(industry);
        //id
        int userId = userBean.getUserId();
        userDataBean.setUserId(userId);
        //avatarUrl
        String avatarUrl = userBean.getAvatarImgUrl();
        userDataBean.setAvatarImgUrl(TransFormUtil.getLocalImageUrl(avatarUrl));
        //coverImgUrl
        String coverImgUrl = userBean.getCoverImgUrl();
        userDataBean.setCoverImgUrl(TransFormUtil.getLocalImageUrl(coverImgUrl));
        //userRegistTime
        String userRegistTime = userBean.getUserRegistTime();
        userDataBean.setUserRegistTime(TransFormUtil.getLocalDate(userRegistTime));
        //nativePlace
        String nativePlace = userBean.getNativePlace();
        userDataBean.setNativePlace(nativePlace);
        //tel
        String tel = userBean.getTel();
        userDataBean.setTel(tel);
        //circleCreatedCount
        int circleCreatedCount = userBean.getCircleCreatedCount();
        userDataBean.setCircleCreatedCount(circleCreatedCount);
        //username
        String username = userBean.getUsername();
        if (userBean != null) {
            userDataBean.setUsername(username);
        }
    }

    /**
     * 封装个人数据
     */
    private void personInfo() {
        userDataBean = new UserDataBean();
        SharedPreferences sp = SPUtil.getmUserSp();
        //birthday
        String birthday = sp.getString("birthday", "");
        userDataBean.setBirthday(TransFormUtil.getLocalDate(birthday));
        //gender
        String gender = sp.getString("gender", "");
        userDataBean.setGender(gender);
        //loginState
        int loginState = sp.getInt("loginState", 0);
        userDataBean.setLoginState(loginState);
        //campus
        String campus = sp.getString("campus", "");
        userDataBean.setCampus(campus);
        //circle add count
        int circleAddedCount = sp.getInt("circleAddedCount", 0);
        userDataBean.setCircleAddedCount(circleAddedCount);
        //circle add count
        String sign = sp.getString("sign", "");
        userDataBean.setSign(sign);
        //email
        String email = sp.getString("eamil", "");
        userDataBean.setEmail(email);
        //job
        String industry = sp.getString("industry", "");
        userDataBean.setIndustry(industry);
        //id
        int userId = sp.getInt("userId", 0);
        userDataBean.setUserId(userId);
        //avatarUrl
        String avatarUrl = sp.getString("avatarImgUrl", "");
        userDataBean.setAvatarImgUrl(TransFormUtil.getLocalImageUrl(avatarUrl));
        //coverImgUrl
        String coverImgUrl = sp.getString("coverImgUrl", "");
        userDataBean.setCoverImgUrl(TransFormUtil.getLocalImageUrl(coverImgUrl));
        //userRegistTime
        String userRegistTime = sp.getString("userRegistTime", "");
        userDataBean.setUserRegistTime(TransFormUtil.getLocalDate(userRegistTime));
        //nativePlace
        String nativePlace = sp.getString("nativePlace", "");
        userDataBean.setNativePlace(nativePlace);
        //tel
        String tel = sp.getString("tel", "");
        userDataBean.setTel(tel);
        //circleCreatedCount
        int circleCreatedCount = sp.getInt("circleCreatedCount", 0);
        userDataBean.setCircleCreatedCount(circleCreatedCount);
        //username
        String username = sp.getString("username", "");
        userDataBean.setUsername(username);
    }

    @Override
    public void initFragment() {
        //page two data
        Bundle bundle = new Bundle();
        bundle.putParcelable("userDataBean", userDataBean);
        mPersonDetailFragment = PersonDetailPageOneFragment.newInstance(bundle);
        mPersonBaseInfoFragment = PersonDetailPageTwoFragment.newInstance(bundle);
        mPersonDetailFragment.setViewPager(mVp);
        mPersonBaseInfoFragment.setViewPager(mVp);
    }

    @Override
    public void initView() {
        //tab
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.setupWithViewPager(mVp);

        //vp
        mVpAdpater = new DetailFragmentPagerAdapter(getSupportFragmentManager());
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mPersonDetailFragment);
        mFragmentList.add(mPersonBaseInfoFragment);
        mVpAdpater.initList(mFragmentList, titles);
        mVp.setAdapter(mVpAdpater);
        mVp.setOffscreenPageLimit(1);
        mVp.setCurrentItem(0);

        if (state == OTHER_STATE) {
            mEditLyt.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public PersonDetailPresenter initPresenter() {
        return new PersonDetailPresenter(this);
    }

    @Override
    public void bindListener() {
        mVp.addOnPageChangeListener(this);
    }

    @OnClick({R.id.activity_person_detail_back, R.id.activity_person_detail_circleCreatedCount_lyt, R.id.activity_person_detail_circleJoinedCount_lyt, R.id.activity_person_detail_edit_lyt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_person_detail_back:
                finish();
                break;
            case R.id.activity_person_detail_circleCreatedCount_lyt:
                break;
            case R.id.activity_person_detail_circleJoinedCount_lyt:
                break;
            case R.id.activity_person_detail_edit_lyt:
                toPage(PersonDetailEditActivity.class);
                break;
        }
    }

    /***********************tablayout call back*******************************************************/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        mVp.resetHeight(position);
        if (position == 1) {
            mPersonBaseInfoFragment.getPresent().personBaseInfoTask();
        }
    }

    @Override
    protected void initData() {

//        if (state == PERSON_STATE) {
//            getPresent().initBaseInfo();//基本信息设置
//        } else if (state == OTHER_STATE) {
        getPresent().initBaseInfoByUserBean();//基本信息设置
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public ImageView getPortraitView() {
        return mPortraitImg;
    }

    @Override
    public ImageView getBgView() {
        return mDetailBgImg;
    }

    @Override
    public TextView getNameTv() {
        return mNameTv;
    }

    @Override
    public TextView getJobTv() {
        return mJobTv;
    }

    @Override
    public ImageView getGenderImg() {
        return mGenderImg;
    }

    @Override
    public TextView getNativePlaceTv() {
        return mPlaceTv;
    }

    @Override
    public TextView getCollegeTv() {
        return mCollegeTv;
    }

    @Override
    public TextView getCircleCreatedCountTv() {
        return mCircleCreatedCountTextTv;
    }

    @Override
    public TextView getCircleJoinedCountTv() {
        return mlCircleJoinedCountTextTv;
    }

    @Override
    public TextView getDynamicCountTv() {
        return mDynamicCountTextTv;
    }

    @Override
    public UserDataBean getUserBean() {
        return userDataBean;
    }

    public void updateDynamicCounts(int count) {
        getDynamicCountTv().setText(count + "");
    }
}
