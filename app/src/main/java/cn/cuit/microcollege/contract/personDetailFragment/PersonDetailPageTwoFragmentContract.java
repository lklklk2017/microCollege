package cn.cuit.microcollege.contract.personDetailFragment;

import android.widget.TextView;

import java.util.Map;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public interface PersonDetailPageTwoFragmentContract {
    interface Model extends BaseModel {
        //获取用户信息
        void getBaseInfoTask(Map<String, Object> map, BaseInfoHttpResult result);

        interface BaseInfoHttpResult {
            void onSuccess(GetUserBaseInfoResultBean.UserListBean userBean);

            void onError(String error);
        }
    }

    interface View {
        TextView getGenderView();

        TextView getNameView();

        TextView getJobView();

        TextView getCollegeView();

        TextView getNativePalaceView();

        TextView getSignView();

//        int getUserId();

        boolean getInitState();

        void setInitState(boolean inited);
    }

    interface Presenter {
        void personBaseInfoTask();
    }
}
