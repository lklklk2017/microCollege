package cn.cuit.microcollege.presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.about.PersonDetailActivity;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.PersonDetailContract;
import cn.cuit.microcollege.entity.DataEntity.UserDataBean;
import cn.cuit.microcollege.model.PersonDetailModel;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/3
 * @Descirption:
 */
public class PersonDetailPresenter extends BasePresenter<PersonDetailActivity, PersonDetailModel> implements PersonDetailContract.Presenter {

    public PersonDetailPresenter(PersonDetailActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new PersonDetailModel());
    }

    @Override
    public void initBaseInfo() {
        //portrait
        String avatarImgUrl = SPUtil.getmUserSp().getString("avatarImgUrl", "");
        if (avatarImgUrl != null && avatarImgUrl.length() != 0) {
            Glide.with(MyApplication.getMyContext())
                    .load(TransFormUtil.getLocalImageUrl(avatarImgUrl))
                    .apply(new RequestOptions().centerCrop().error(R.drawable.error))
                    .into(getV().getPortraitView());
        }

        //bg
        String coverImgUrl = SPUtil.getmUserSp().getString("coverImgUrl", "");
        if (coverImgUrl != null && coverImgUrl.length() != 0) {
            Glide.with(MyApplication.getMyContext())
                    .load(TransFormUtil.getLocalImageUrl(coverImgUrl))
                    .apply(new RequestOptions().centerCrop().placeholder(R.drawable.no_result).error(R.drawable.error))
                    .into(getV().getBgView());
        }

        //name
        String username = SPUtil.getmUserSp().getString("username", "");
        if (username != null && username.length() != 0) {
            getV().getNameTv().setText(username);
        }

        //job
        String job = SPUtil.getmUserSp().getString("industry", "");
        if (job != null && job.length() != 0) {
            getV().getJobTv().setText(job);
        }

        //gender
        String gender = SPUtil.getmUserSp().getString("gender", "");
        if (gender != null && gender.length() != 0) {
            if (gender.equals("男")) {
                //man
                getV().getGenderImg().setImageResource(R.drawable.man);
            } else {
                //woman
                getV().getGenderImg().setImageResource(R.drawable.feman);
            }
        }

        //home place
        String nativePlace = SPUtil.getmUserSp().getString("nativePlace", "");
        if (nativePlace != null && nativePlace.length() != 0) {
            getV().getNativePlaceTv().setText(nativePlace);
        }

        //college
        String college = SPUtil.getmUserSp().getString("campus", "");
        if (college != null && college.length() != 0) {
            getV().getCollegeTv().setText(college);
        }

        //create circle count
        int circleCreatedCount = SPUtil.getmUserSp().getInt("circleCreatedCount", 0);
        getV().getCircleCreatedCountTv().setText(circleCreatedCount + "");

        //join circle
        int circleJoinedCount = SPUtil.getmUserSp().getInt("circleAddedCount", 0);
        getV().getCircleJoinedCountTv().setText(circleJoinedCount + "");

        //dynamic count //todo

    }

    @Override
    public void initBaseInfoByUserBean() {
        UserDataBean userBean = getV().getUserBean();
        if (userBean == null) {
            LogU.i("参数获取失败: userBean", getClass().getName());
            return;
        }

        //portrait
        String avatarImgUrl = userBean.getAvatarImgUrl();
        if (avatarImgUrl != null && avatarImgUrl.length() != 0) {
            Glide.with(MyApplication.getMyContext())
                    .load(avatarImgUrl)
                    .apply(new RequestOptions().centerCrop().error(R.drawable.error))
                    .into(getV().getPortraitView());
        }

        //bg
        String coverImgUrl = userBean.getCoverImgUrl();

        if (coverImgUrl != null && coverImgUrl.length() != 0) {
            Glide.with(MyApplication.getMyContext())
                    .load(coverImgUrl)
                    .apply(new RequestOptions().centerCrop().placeholder(R.drawable.no_result).error(R.drawable.error))
                    .into(getV().getBgView());
        }

        //name
        String username = userBean.getUsername();
        if (username != null && username.length() != 0) {
            getV().getNameTv().setText(username);
        }

        //job
        String job = userBean.getIndustry();
        if (job != null && job.length() != 0) {
            getV().getJobTv().setText(job);
        }

        //gender
        String gender = userBean.getGender();
        if (gender != null && gender.length() != 0) {
            if (gender.equals("男")) {
                //man
                getV().getGenderImg().setImageResource(R.drawable.man);
            } else {
                //woman
                getV().getGenderImg().setImageResource(R.drawable.feman);
            }
        }

        //home place
        String nativePlace = userBean.getNativePlace();
        if (nativePlace != null && nativePlace.length() != 0) {
            getV().getNativePlaceTv().setText(nativePlace);
        }

        //college
        String college = userBean.getCampus();
        if (college != null && college.length() != 0) {
            getV().getCollegeTv().setText(college);
        }

        //create circle count
        int circleCreatedCount = userBean.getCircleCreatedCount();
        getV().getCircleCreatedCountTv().setText(circleCreatedCount + "");

        //join circle
        int circleJoinedCount = userBean.getCircleAddedCount();
        getV().getCircleJoinedCountTv().setText(circleJoinedCount + "");

    }
}
