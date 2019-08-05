package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.MeFragmentContract;
import cn.cuit.microcollege.fragment.MeFragment;
import cn.cuit.microcollege.model.MeFragmentModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class MeFragmentPresenter extends BasePresenter<MeFragment, MeFragmentModel> implements MeFragmentContract.Presenter {

    public MeFragmentPresenter(MeFragment view) {
        super(view);
    }

    @Override
    public void initModel() {

    }
}
