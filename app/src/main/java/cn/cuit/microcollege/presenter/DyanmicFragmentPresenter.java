package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.DyanmicFragmentContract;
import cn.cuit.microcollege.fragment.dynamic.DynamicFragment;
import cn.cuit.microcollege.model.DyanmicFragmentModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class DyanmicFragmentPresenter extends BasePresenter<DynamicFragment, DyanmicFragmentModel> implements DyanmicFragmentContract.Presenter {

    public DyanmicFragmentPresenter(DynamicFragment view) {
        super(view);
    }

    @Override
    public void initModel() {

    }
}
