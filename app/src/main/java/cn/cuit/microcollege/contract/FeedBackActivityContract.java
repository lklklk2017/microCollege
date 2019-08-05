package cn.cuit.microcollege.contract;

import cn.cuit.microcollege.base.BaseModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/3
 * @Descirption:
 */
public interface FeedBackActivityContract {
    interface Model extends BaseModel {
        void addFeedback(int userId, String content, AddFeedBackHttpResult result);

        interface AddFeedBackHttpResult {
            void success();

            void error(String error);
        }
    }

    interface View {
    }

    interface Presenter {
    }
}
