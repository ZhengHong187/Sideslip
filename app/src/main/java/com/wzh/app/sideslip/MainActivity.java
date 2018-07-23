package com.wzh.app.sideslip;




import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;

import com.ashokvarma.bottomnavigation.BadgeItem;

public class MainActivity extends BaseActivity implements View.OnClickListener,BottomNavigationBar.OnTabSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menu, morebtn;

    private TopRightMenu mTopRightMenu;
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;

    private String TAG = MainActivity.class.getSimpleName();
    private messagefragment mmessagefragment;
    private PersonFragment personFragment;
    private SettingFragment settingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        menu= (ImageView) findViewById(R.id.main_menu);
        //获取头布局
        View headerView = navigationView.getHeaderView(0);
        menu.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this,item.getTitle().
                        toString(),Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        morebtn = findViewById(R.id.more);
        morebtn.setOnClickListener(this);


        /*
        * 消息小圆圈
        */
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.red)
                .setBorderWidth(0);

        /*
        * 底部导航栏
        */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setBarBackgroundColor(R.color.blue_01);
        bottomNavigationBar.setInActiveColor(R.color.baise);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.icon_one,"消息")
                        .setActiveColorResource(R.color.green).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_two,"联系人")
                .setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three,"设置")
                .setActiveColorResource(R.color.red))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();;

                bottomNavigationBar.setTabSelectedListener(this);
                setDefaultFragment();
       


    }
    /**
     * set the default fragment
     */
    private void setDefaultFragment() {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mmessagefragment = messagefragment.newInstance("消息");
        transaction.replace(R.id.ll_content,mmessagefragment).commit();

    }

    /*
    * 菜单栏加号点击事件处理*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu :
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                }else {
                    drawerLayout.openDrawer(navigationView);
                }
                break;
            case R.id.more :
                mTopRightMenu = new TopRightMenu(MainActivity.this);
                final List<com.zaaach.toprightmenu.MenuItem> menuItems = new ArrayList<>();
                 menuItems.add(new com.zaaach.toprightmenu.MenuItem(R.mipmap.addmember,"加好友/群"));
                menuItems.add(new com.zaaach.toprightmenu.MenuItem(R.mipmap.qr_scan,"扫一扫"));
                mTopRightMenu
                        .setHeight(680)
                        .setWidth(450)
                        .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                        .addMenuList(menuItems)
                        .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.facetoface ,"面对面快传"))
                        .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.pay ,"支付"))
                        .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                            @Override
                            public void onMenuItemClick(int position) {
                                Toast.makeText(MainActivity.this, "点击菜单:" + position, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .showAsDropDown(morebtn, -225, 0);
                break;
                default:
                    break;
        }

    }

    /*
    * 底部导航栏事件处理
    */

    @Override
    public void onTabSelected(int position) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (mmessagefragment == null) {
                    mmessagefragment = messagefragment.newInstance("消息");

                }
                transaction.replace(R.id.ll_content,mmessagefragment);
                break;
            case 1:
                if (personFragment == null) {
                    personFragment = PersonFragment.newInstance("联系人");

                }
                transaction.replace(R.id.ll_content,personFragment);
                break;
            case 3:
                if (settingFragment == null) {
                    settingFragment =SettingFragment.newInstance("设置");

                }
                transaction.replace(R.id.ll_content,settingFragment);
                break;
             default:
                 if (mmessagefragment == null) {
                     mmessagefragment = messagefragment.newInstance("消息");
                 }
                 transaction.replace(R.id.ll_content,mmessagefragment);
                 break;
        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
