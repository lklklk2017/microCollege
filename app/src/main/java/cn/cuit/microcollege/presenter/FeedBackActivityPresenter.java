package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.activity.FeedBackActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.FeedBackActivityContract;
import cn.cuit.microcollege.model.FeedBackActivityModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/3
 * @Descirption:
 */
public class FeedBackActivityPresenter extends BasePresenter<FeedBackActivity, FeedBackActivityModel> implements FeedBackActivityContract.Presenter {

    public FeedBackActivityPresenter(FeedBackActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new FeedBackActivityModel());
    }
}
