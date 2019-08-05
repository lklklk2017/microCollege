package cn.cuit.microcollege.presenter.personDetailFragment;

import android.os.Bundle;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.personDetailFragment.PersonDetailPageOneFragmentContract;
import cn.cuit.microcollege.entity.DataEntity.UserDataBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.fragment.person.PersonDetailPageOneFragment;
import cn.cuit.microcollege.model.personDetailFragment.PersonDetailPageOneFragmentModel;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/24
 * @Descirption:
 */
public class PersonDetailPageOneFragmentPresenter extends BasePresenter<PersonDetailPageOneFragment, PersonDetailPageOneFragmentModel> implements PersonDetailPageOneFragmentContract.Presenter {

    public PersonDetailPageOneFragmentPresenter(PersonDetailPageOneFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new PersonDetailPageOneFragmentModel());
    }

    @Override
    public void personDynamicTask(int page) {
        if (getV() == null) {
            return;
        }
        Bundle arguments = getV().getArguments();
        if (arguments == null) {
            LogU.i("参数获取失败: arguments", getClass().getName());
            return;
        }

        UserDataBean userDataBean = arguments.getParcelable("userDataBean");
        if (userDataBean == null) {
            LogU.i("参数获取失败: userDataBean", getClass().getName());
            return;
        }

        //get tel
        String tel = userDataBean.getTel();
        if (tel == null || tel.equals("")) {
            LogU.i("参数获取失败 tel", getClass().getName());
            return;
        }

        //获取数据
        int pageSize = getV().getPageSize();

        //封装数据
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("trendType", "");
        data.put("tel", tel);
        data.put("email", "");
        data.put("fromTime", "");
        data.put("toTime", "");
        data.put("circleName", "");
        data.put("content", "");
        data.put("page", page);
        data.put("pageSize", pageSize);

        getM().getPersonDynamic(data, new PersonDetailPageOneFragmentContract.Model.PersonDynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicResultBean.TrendsBean> trends) {
                if (getV() == null) {
                    return;
                }
                if (trends == null) {
                    int itemCount = getV().getAdapter().getItemCount();
                    if (itemCount == 0) {
                        getV().showNoResult();
                        return;
                    }
                    getV().toast("没有更多数据了", false);
                    int currentPage1 = getV().getCurrentPage();
                    if (currentPage1 == 1) {
                        return;
                    }
                    getV().setCurrentPage(--currentPage1);
                    return;
                }
                //适配数据
                getV().getAdapter().addList(trends);
                //update
                getV().setDynamicCount(getV().getAdapter().getItemCount());
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().toast("网络异常", false);
            }
        });
    }

    @Override
    public void deleteDyanmicTask(int tid, final int position) {
        getM().deletePersonDyanmic(String.valueOf(tid), new PersonDetailPageOneFragmentContract.Model.DeleteDynamicHttpResult() {
            @Override
            public void onSuccess() {
                if (getV() == null) {
                    return;
                }
                getV().toast("已删除", false);
//                getV().getAdapter().removeItem(position);
                getV().getAdapter().getmList().remove(getV().getAdapter().getmList().get(position));
                getV().getAdapter().notifyItemRemoved(position);
                getV().getAdapter().notifyItemRangeChanged(position, getV().getAdapter().getmList().size() - position);
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().toast(error, false);
            }
        });
    }

    @Override
    public void addLike(String tid) {

        getM().addLike(tid, new PersonDetailPageOneFragmentContract.Model.AddLikeHttpResult() {
            @Override
            public void success() {

            }

            @Override
            public void error(String error) {

            }
        });
    }
}
