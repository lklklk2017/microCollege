package cn.cuit.microcollege.fragment.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.personDetailFragment.PersonDetailPageTwoFragmentContract;
import cn.cuit.microcollege.presenter.personDetailFragment.PersonDetailPageTwoFragmentPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public class PersonDetailPageTwoFragment extends BaseFragment<PersonDetailPageTwoFragmentPresenter> implements PersonDetailPageTwoFragmentContract.View {


    @BindView(R.id.fragment_person_detail_page_two_name_et)
    TextView mNameTv;
    @BindView(R.id.fragment_person_detail_page_two_sign_tv)
    TextView mSignTv;
    @BindView(R.id.fragment_person_detail_page_two_gender_tv)
    TextView mGenderTv;
    @BindView(R.id.fragment_person_detail_page_two_place_tv)
    TextView mPlaceTv;
    @BindView(R.id.fragment_person_detail_page_two_college_et)
    TextView mCollegeTv;
    @BindView(R.id.fragment_person_detail_page_two_job_et)
    TextView mJobTv;
    Unbinder unbinder;
    private boolean mInitState = false;

    public PersonDetailPageTwoFragment() {
    }

    public static PersonDetailPageTwoFragment newInstance(Bundle bundle) {
        PersonDetailPageTwoFragment currFragment = new PersonDetailPageTwoFragment();
        if (bundle != null) {
            currFragment.setArguments(bundle);
        }
        return currFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.i("执行了two-onStart()", getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.i("执行了two-onResume()", getClass().getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_person_detail_page_two, container, false);
        setCreatedView(inflate);//toast 会用到
        CustomViewPager viewPager = (CustomViewPager) getViewPager();
        if (viewPager != null) {
            viewPager.setObjectForPosition(inflate, 1);
        }
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public PersonDetailPageTwoFragmentPresenter initPresenter() {
        return new PersonDetailPageTwoFragmentPresenter(this);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initData() {
//        getPresent().personBaseInfoTask();//加载个人信息
    }

    @Override
    public void bindListener() {

    }

    @Override
    public TextView getGenderView() {
        return mGenderTv;
    }

    @Override
    public TextView getJobView() {
        return mJobTv;
    }

    @Override
    public TextView getCollegeView() {
        return mCollegeTv;
    }

    @Override
    public TextView getNativePalaceView() {
        return mPlaceTv;
    }

    @Override
    public TextView getSignView() {
        return mSignTv;
    }

    @Override
    public boolean getInitState() {
        return mInitState;
    }

    @Override
    public void setInitState(boolean inited) {
        mInitState = inited;
    }

    @Override
    public TextView getNameView() {
        return mNameTv;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @Override
//    public int getUserId() {
//        return mUserId;
//    }
}
