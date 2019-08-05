package cn.cuit.microcollege.presenter.newfragments;

import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.newfragments.NewHotFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.NewResultBean;
import cn.cuit.microcollege.entity.PostEntity.NewPostBean;
import cn.cuit.microcollege.fragment.dynamic.NewHotFragment;
import cn.cuit.microcollege.model.newfragments.NewHotFragmentModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class NewHotFragmentPresenter extends BasePresenter<NewHotFragment, NewHotFragmentModel> implements NewHotFragmentContract.Presenter {


    public NewHotFragmentPresenter(NewHotFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new NewHotFragmentModel());
    }

    @Override
    public void getHotNewListTask(int currentPage) {
        //vertifiy
        if (currentPage == 0) {
            getV().toast("页码参数错误", false);
            return;
        }

        //开始请求数据
        getV().getSwip().setRefreshing(true);
        NewPostBean bean = new NewPostBean();
        //热点 2
        bean.setCid("2");
        bean.setSize(currentPage * 10);
        getM().getHotNew(bean, new NewHotFragmentContract.Model.HotNewResult() {
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
