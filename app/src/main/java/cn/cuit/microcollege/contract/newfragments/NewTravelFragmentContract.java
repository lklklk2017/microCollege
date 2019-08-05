package cn.cuit.microcollege.contract.newfragments;

import android.support.v4.widget.SwipeRefreshLayout;

import cn.cuit.microcollege.adapter.NewHotAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.NewResultBean;
import cn.cuit.microcollege.entity.PostEntity.NewPostBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public interface NewTravelFragmentContract {
    interface Model extends BaseModel {
        void getNew(NewPostBean bean, TravelNewResult reusult);

        interface TravelNewResult {
            void onSuccess(NewResultBean newResultBean);

            void onError(String s);
        }
    }

    interface View {
        NewHotAdapter getAdpater();

        SwipeRefreshLayout getSwip();

        void showNoResult();
        void showResult();

    }

    interface Presenter {

        //get info list
        void getNewListTask(int currentPage);
    }
}
