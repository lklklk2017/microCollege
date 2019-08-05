package cn.cuit.microcollege.contract;

import cn.cuit.microcollege.base.BaseModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public interface ExploreFragmentContract {
    interface Model extends BaseModel {
    }

    interface View {
        void refresh(boolean isRefresh);
    }

    interface Presenter {
//        void getChatList();
    }
}
