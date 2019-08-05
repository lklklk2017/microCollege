package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.ExploreFragmentContract;
import cn.cuit.microcollege.fragment.ExploreFragment;
import cn.cuit.microcollege.model.ExploreFragmentModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class ExploreFragmentPresenter extends BasePresenter<ExploreFragment, ExploreFragmentModel> implements ExploreFragmentContract.Presenter {

    public ExploreFragmentPresenter(ExploreFragment view) {
        super(view);
    }

    @Override
    public void initModel() {

    }
}
