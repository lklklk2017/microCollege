package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.base.BaseView;
import cn.cuit.microcollege.contract.ChatActivityContract;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class ChatActivityPresenter extends BasePresenter implements ChatActivityContract.Presenter {



    public ChatActivityPresenter(BaseView view) {
        super(view);
    }

    @Override
    public void initModel() {

    }
}
