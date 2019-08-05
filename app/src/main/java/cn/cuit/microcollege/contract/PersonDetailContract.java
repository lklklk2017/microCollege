package cn.cuit.microcollege.contract;

import android.widget.ImageView;
import android.widget.TextView;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.DataEntity.UserDataBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/3
 * @Descirption:
 */
public interface PersonDetailContract {
    interface Model extends BaseModel {
    }

    interface View {
        //portrait
        ImageView getPortraitView();

        //bg
        ImageView getBgView();

        //name
        TextView getNameTv();

        //job
        TextView getJobTv();

        //gender
        ImageView getGenderImg();

        //home place
        TextView getNativePlaceTv();

        //college
        TextView getCollegeTv();

        //create circle count
        TextView getCircleCreatedCountTv();

        //join circle
        TextView getCircleJoinedCountTv();

        //dynamic couont
        TextView getDynamicCountTv();

        //getUserBean
        UserDataBean getUserBean();
    }

    interface Presenter {
        void initBaseInfo();
        void initBaseInfoByUserBean();
    }
}
