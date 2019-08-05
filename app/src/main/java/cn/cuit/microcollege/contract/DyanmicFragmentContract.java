package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.fragment.dynamic.NewFashionFragment;
import cn.cuit.microcollege.fragment.dynamic.NewHealthFragment;
import cn.cuit.microcollege.fragment.dynamic.NewHotFragment;
import cn.cuit.microcollege.fragment.dynamic.NewInternetFragment;
import cn.cuit.microcollege.fragment.dynamic.NewTravelFragment;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public interface DyanmicFragmentContract {
    interface Model extends BaseModel {

    }

    interface View {
        //热点    {"热点", "互联网", "时尚", "健康", "旅行"};
        NewHotFragment getHotFragment();

        //互联网
        NewInternetFragment getInternetFragment();

        //时尚
        NewFashionFragment getFashionFragment();

        //健康
        NewHealthFragment getHealthFragment();

        //旅行
        NewTravelFragment getTravelFragment();

        SwipeRefreshLayout getSwip();
    }

    interface Presenter {
    }
}
