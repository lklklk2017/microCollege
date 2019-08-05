package cn.cuit.microcollege.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;
    private List<Fragment> list = new ArrayList<>();
    private String[] titles;

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void initList(List<Fragment> list, String[] titles) {
        this.list = list;
        this.titles = titles;
    }

    public void changeList(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
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
        LogU.i("getItem()被调用当前位置：" + position, this.getClass().getName());
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
