package cn.cuit.microcollege.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.HomeActivityContract;
import cn.cuit.microcollege.fragment.ExploreFragment;
import cn.cuit.microcollege.fragment.HomeFragment;
import cn.cuit.microcollege.fragment.MeFragment;
import cn.cuit.microcollege.fragment.dynamic.DynamicFragment;
import cn.cuit.microcollege.presenter.HomeActivityPresenter;
import cn.ycbjie.ycstatusbarlib.bar.YCAppBar;


public class HomeActivity extends BaseActivity<HomeActivityPresenter>
        implements HomeActivityContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.home_nav)
    BottomNavigationView homeNav;
    private Fragment currentFragment;//当前显示的fragment
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private DynamicFragment dynamicFragment;
    private ExploreFragment exploreFragment;
    private MeFragment meFragment;
    private TextView msgCountTv;

    private int[] colors = new int[]{R.color.colorPrimaryDarkMain, R.color.colorDefaultwhite, R.color.colorDefaultwhite, R.color.colorPrimaryDarkMain};
    private long lastTime  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initView() {
        /*底部导航栏相关*/
//        disableShiftMode(homeNav);//设置均分
        //设置消息提示
//        initBadgeWithNavMenuItem();

        //初始化fragments
        homeFragment = HomeFragment.newInstance(null);
        dynamicFragment = DynamicFragment.newInstance(null);
        exploreFragment = ExploreFragment.newInstance(null);
        meFragment = MeFragment.newInstance(null);

        //设置默认显示的fragment
        setDefaultCurrentFragment();
    }

    /**
     * 初始化通知徽章
     */
    private void initBadgeWithNavMenuItem() {
        BottomNavigationMenuView menuView = ((BottomNavigationMenuView) homeNav.getChildAt(0));
        View tab = menuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;
        //加载通知徽章
        View badge = LayoutInflater.from(this).inflate(R.layout.activity_home_nav_item_msg, itemView, false);
        itemView.addView(badge);
        msgCountTv = (TextView) badge.findViewById(R.id.nav_msg_count);
    }

    @Override
    public HomeActivityPresenter initPresenter() {

        return mPresenter = new HomeActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        homeNav.setOnNavigationItemSelectedListener(this);//底部导航栏
    }

    /*****************************************view init / listener**************************************/
    /**
     * 设置底部导航栏的均匀分布
     *
     * @param
     */
//    public void disableShiftMode(BottomNavigationView navigationView) {
//
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
//        try {
//            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//            shiftingMode.setAccessible(true);
//            shiftingMode.setBoolean(menuView, false);
//            shiftingMode.setAccessible(false);
//
//            for (int i = 0; i < menuView.getChildCount(); i++) {
//                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
//                itemView.setShiftingMode(false);
//                itemView.setChecked(itemView.getItemData().isChecked());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.home_nav_item_home://首页
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance(null);
                    shiftCurrentFragment(homeFragment);
                } else {
                    shiftCurrentFragment(homeFragment);
                }
                //更改状态栏颜色
                YCAppBar.setStatusBarLightMode(this, getResources().getColor(colors[0]));
                break;
            case R.id.home_nav_item_dynamic://动态
                if (dynamicFragment == null) {
                    dynamicFragment = DynamicFragment.newInstance(null);
                    shiftCurrentFragment(dynamicFragment);
                } else {
                    shiftCurrentFragment(dynamicFragment);
                }
                //更改状态栏颜色
                YCAppBar.setStatusBarLightMode(this, getResources().getColor(colors[1]));
                break;
            case R.id.home_nav_item_add://发布
                toPage(PublishDynamicActivity.class);

                break;
//            case R.id.home_nav_item_explore://探索
//                if (exploreFragment == null) {
//                    exploreFragment = ExploreFragment.newInstance(null);
//                    shiftCurrentFragment(exploreFragment);
//                } else {
//                    shiftCurrentFragment(exploreFragment);
//                }
//                //更改状态栏颜色
//                YCAppBar.setStatusBarLightMode(this, getResources().getColor(colors[2]));
//                break;
            case R.id.home_nav_item_me://我
                if (meFragment == null) {
                    meFragment = MeFragment.newInstance(null);
                    shiftCurrentFragment(meFragment);
                } else {
                    shiftCurrentFragment(meFragment);
                }
                //更改状态栏颜色
                YCAppBar.setStatusBarLightMode(this, getResources().getColor(colors[3]));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void setCurrentFragment(Fragment fragment) {
        currentFragment = fragment;
    }

    @Override
    public Fragment getCurrentFragment() {
        if (null != currentFragment) {
            return currentFragment;
        } else {
            return null;
        }
    }


    @Override
    public void shiftCurrentFragment(Fragment fragment) {

        //主页
        if (fragment instanceof HomeFragment) {
            boolean same = getCurrentFragment() == (HomeFragment) fragment;
            if (!same) {
                FragmentTransaction t = fm.beginTransaction();
                t.hide(getCurrentFragment());
                if (!homeFragment.isAdded()) {
                    t.add(R.id.home_fragment, homeFragment);
                }
                t.show(homeFragment);
                t.commit();
                setCurrentFragment(homeFragment);
            }
        }

        //动态
        if (fragment instanceof DynamicFragment) {
            boolean same = getCurrentFragment() == (DynamicFragment) fragment;
            if (!same) {
                FragmentTransaction t = fm.beginTransaction();
                t.hide(getCurrentFragment());
                if (!dynamicFragment.isAdded()) {
                    t.add(R.id.home_fragment, dynamicFragment);
                }
                t.show(dynamicFragment);
                t.commit();
                setCurrentFragment(dynamicFragment);
            }
        }

        //探索
        if (fragment instanceof ExploreFragment) {
            boolean same = getCurrentFragment() == (ExploreFragment) fragment;
            if (!same) {
                FragmentTransaction t = fm.beginTransaction();
                t.hide(getCurrentFragment());
                if (!exploreFragment.isAdded()) {
                    t.add(R.id.home_fragment, exploreFragment);
                }
                t.show(exploreFragment);
                t.commit();
                setCurrentFragment(exploreFragment);
            }
        }

        //我
        if (fragment instanceof MeFragment) {
            boolean same = getCurrentFragment() == (MeFragment) fragment;
            if (!same) {
                FragmentTransaction t = fm.beginTransaction();
                t.hide(getCurrentFragment());
                if (!meFragment.isAdded()) {
                    t.add(R.id.home_fragment, meFragment);
                }
                t.show(meFragment);
                t.commit();
                setCurrentFragment(meFragment);
            }
        }
    }

    /**
     * 设置默认fragment:首页fragment
     */
    @Override
    public void setDefaultCurrentFragment() {
        fm = getSupportFragmentManager();
        FragmentTransaction t = fm.beginTransaction();
        t.add(R.id.home_fragment, homeFragment);
        t.commit();
        setCurrentFragment(homeFragment);//设置当前
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTime <= 2000) {
            finish();
        } else {
            lastTime = System.currentTimeMillis();
            toast("再次按退格返回桌面哦~",false);
        }
    }
}
