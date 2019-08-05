package cn.cuit.microcollege.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;
    private List<Fragment> list = new ArrayList<>();
    private String[] titles;

    public NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void initList(List<Fragment> list, String[] titles) {
        if (list != null) {
            this.list = list;
            this.titles = titles;
        } else {
            throw new RuntimeException("vp 页面的参数有误:list 不能为空");
        }
    }

    public void changeList(List<Fragment> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles.length == 0) {
            return "";
        }
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
