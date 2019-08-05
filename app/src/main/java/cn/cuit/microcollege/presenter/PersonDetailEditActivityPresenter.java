package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.activity.PersonDetailEditActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.PersonDetailEditActivityContract;
import cn.cuit.microcollege.model.PersonDetailEditActivityModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class PersonDetailEditActivityPresenter extends BasePresenter<PersonDetailEditActivity, PersonDetailEditActivityModel> implements PersonDetailEditActivityContract.Presenter {

    public PersonDetailEditActivityPresenter(PersonDetailEditActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new PersonDetailEditActivityModel());
    }
}
