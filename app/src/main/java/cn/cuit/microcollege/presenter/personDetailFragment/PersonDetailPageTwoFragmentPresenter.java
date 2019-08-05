package cn.cuit.microcollege.presenter.personDetailFragment;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.personDetailFragment.PersonDetailPageTwoFragmentContract;
import cn.cuit.microcollege.entity.DataEntity.UserDataBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.fragment.person.PersonDetailPageTwoFragment;
import cn.cuit.microcollege.model.personDetailFragment.PersonDetailPageTwoFragmentModel;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public class PersonDetailPageTwoFragmentPresenter extends BasePresenter<PersonDetailPageTwoFragment, PersonDetailPageTwoFragmentModel> implements PersonDetailPageTwoFragmentContract.Presenter {
    public PersonDetailPageTwoFragmentPresenter(PersonDetailPageTwoFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new PersonDetailPageTwoFragmentModel());
    }

    @Override
    public void personBaseInfoTask() {
        boolean state = getV().getInitState();
        if (state == true) {
            return;
        }

        if (getV() == null) {
            return;
        }

        Bundle arguments = getV().getArguments();
        if (arguments == null) {
            LogU.i("获取参数失败 arguments", getClass().getName());
            return;
        }
        UserDataBean userDataBean = arguments.getParcelable("userDataBean");
        if (userDataBean == null) {
            LogU.i("获取参数失败 userDataBean", getClass().getName());
            return;
        }

        //get userId
        int userId = userDataBean.getUserId();
        if (userId == 0) {
            LogU.i("获取参数失败:user id from personBaseInfoTask", getClass().getName());
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("tel", "");
        map.put("name", "");
        map.put("email", "");
        map.put("gender", "");
        map.put("fromTime", "");
        map.put("toTime", "");
        map.put("page", "");
        map.put("pageSize", "");
        getV().showDialog(true);
        getM().getBaseInfoTask(map, new PersonDetailPageTwoFragmentContract.Model.BaseInfoHttpResult() {
            @Override
            public void onSuccess(GetUserBaseInfoResultBean.UserListBean userBean) {
                getV().showDialog(false);

                if (userBean != null) {
                    //name
                    String username = userBean.getUsername();
                    if (username != null || username.length() != 0) {
                        getV().getNameView().setText(username);
                    }
                    //gender
                    String gender = userBean.getGender();
                    if (!TextUtils.isEmpty(gender)) {
                        getV().getGenderView().setText(gender);
                    }

                    //sign
                    String sign = userBean.getSign();
                    if (!TextUtils.isEmpty(gender)) {
                        getV().getSignView().setText(sign);
                    }

                    //job
                    String job = userBean.getIndustry();
                    if (!TextUtils.isEmpty(job)) {
                        getV().getJobView().setText(job);
                    }
                    //college
                    String college = userBean.getCampus();
                    if (!TextUtils.isEmpty(college)) {
                        getV().getCollegeView().setText(college);
                    }
                    //place
                    String place = userBean.getNativePlace();
                    if (!TextUtils.isEmpty(place)) {
                        getV().getNativePalaceView().setText(place);
                    }

                    //更改加载状态
                    getV().setInitState(true);
                }
            }

            @Override
            public void onError(String error) {
                getV().showDialog(false);
                LogU.i("获取失败:" + error, getClass().getName());
                getV().setInitState(false);
            }
        });

    }
}
