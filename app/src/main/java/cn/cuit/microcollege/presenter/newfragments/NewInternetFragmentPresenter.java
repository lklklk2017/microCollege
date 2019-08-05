package cn.cuit.microcollege.presenter.newfragments;

import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.newfragments.NewInternetFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.NewResultBean;
import cn.cuit.microcollege.entity.PostEntity.NewPostBean;
import cn.cuit.microcollege.fragment.dynamic.NewInternetFragment;
import cn.cuit.microcollege.model.newfragments.NewInternetFragmentModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class NewInternetFragmentPresenter extends BasePresenter<NewInternetFragment, NewInternetFragmentModel> implements NewInternetFragmentContract.Presenter {

    public NewInternetFragmentPresenter(NewInternetFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new NewInternetFragmentModel());
    }

    @Override
    public void getNewListTask(int currentPage) {
        //vertifiy
        if (currentPage == 0) {
            getV().toast("页码参数错误", false);
            return;
        }

        //开始请求数据
        getV().getSwip().setRefreshing(true);
        NewPostBean bean = new NewPostBean();
        //互联网 13
        bean.setCid("13");
        bean.setSize(currentPage * 10);
        getM().getNew(bean, new NewInternetFragmentContract.Model.InternetNewResult() {
            @Override
            public void onSuccess(NewResultBean resultBean) {
                getV().showResult();
                getV().getSwip().setRefreshing(false);
                //返回数据
                List<NewResultBean.ResultBean.ListBean> list = resultBean.getResult().getList();
                if (list != null) {
                    getV().getAdpater().changeList(list);
                }
            }

            @Override
            public void onError(String s) {
                getV().getSwip().setRefreshing(false);
                getV().showNoResult();
                getV().toast(s, false);
            }
        });
    }
}
