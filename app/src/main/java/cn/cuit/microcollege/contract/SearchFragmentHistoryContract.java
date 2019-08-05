package cn.cuit.microcollege.contract;

import java.util.ArrayList;

import cn.cuit.microcollege.adapter.SearchFragmentHistoryAdapter;
import cn.cuit.microcollege.base.BaseModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption:
 */
public interface SearchFragmentHistoryContract {
    interface Model extends BaseModel {
    }

    interface View {

        void showHistory(boolean show);

        ArrayList<String> getHistoryList();

        void setHistoryList(ArrayList<String> list);

        SearchFragmentHistoryAdapter getHistoryAdatper();
    }

    interface Presenter {
        void addRecord(String content);

        void clearAllRecords();
    }
}
