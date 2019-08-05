package cn.cuit.microcollege.contract;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.fragment.search.detail.DetailCircleFragment;
import cn.cuit.microcollege.fragment.search.detail.DetailDynamicFragment;
import cn.cuit.microcollege.fragment.search.detail.DetailUserFragment;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/26
 * @Descirption:
 */
public interface SearchFragmentDetailContract {
    interface Model extends BaseModel {
    }

    interface View {
        DetailCircleFragment getDetailCircleFragment();

        DetailDynamicFragment getDetailDynamicFragment();

        DetailUserFragment getDetailUserFragment();
    }

    interface Presenter {
    }
}
