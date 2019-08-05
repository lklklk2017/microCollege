package cn.cuit.microcollege.contract;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.fragment.search.SearchDetailFragment;
import cn.cuit.microcollege.fragment.search.SearchHistoryFragment;
import cn.cuit.microcollege.widget.actionBar.CustomSearchBar;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption:
 */
public interface SearchActivityContract {
    interface Model extends BaseModel {
        //网络请求
        void getSearchResult(String content, HttpResult httpResult);

        interface HttpResult {
            void success();

            void error();
        }
    }

    interface View {
        void setReFresh(boolean canRefresh);

        void showHistoryFragment();

        void showDetailFragment(String condiction);

        //设置当前的fragment
        void setCurrentFragment(Fragment fragment);

        //获取当前的fragment
        Fragment getCurrentFragment();

        //切换当前的fragment
        void shiftCurrentFragment(Fragment fragment);

        //设置默认的显示的第一个fragment
        void setDefaultCurrentFragment();

        SearchHistoryFragment getHistoryFragment();

        SearchDetailFragment getDetailFragment(String condiction);

        SwipeRefreshLayout getSwip();

        //获取搜索栏
        CustomSearchBar getSearchBar();
    }

    interface Presenter {

        void search(String content);

        void clearAllHistory();
    }
}
