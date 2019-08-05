package cn.cuit.microcollege.contract;


import android.support.v4.app.Fragment;

import cn.cuit.microcollege.base.BaseModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/14
 * @Descirption:
 */
public interface HomeActivityContract {

    interface Model extends BaseModel {

    }

    interface View {
        //设置当前的fragment
        void setCurrentFragment(Fragment fragment);

        //获取当前的fragment
        Fragment getCurrentFragment();

        //切换当前的fragment
        void shiftCurrentFragment(Fragment fragment);

        //设置默认的显示的第一个fragment
        void setDefaultCurrentFragment();
    }

    interface Presenter {

    }
}
