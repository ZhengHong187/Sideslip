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

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private SystemBarTintManager tintManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menu, morebtn;
    private TopRightMenu mTopRightMenu;

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
        View headerView = navigationView.getHeaderView(0);//获取头布局
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

    }





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
                menuItems.add(new com.zaaach.toprightmenu.MenuItem(R.mipmap.multichat,"创建群聊"));
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

        }
    }
}
