package com.ccx.demochenapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chuangxin.chen
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton news;
    private RadioButton time;
    private RadioButton me;
    private RadioGroup rgGroup;
    private List<Fragment> fragments;
    private TextView textTop;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news = findViewById(R.id.news);
        time = findViewById(R.id.time);
        me = findViewById(R.id.me);
        rgGroup = findViewById(R.id.rg_group);
        textTop = findViewById(R.id.text_top);
        // 关联ViewPager控件
        viewPager = findViewById(R.id.viewPager);
        //默认选择第一个
        news.setChecked(true);
        rgGroup.setOnCheckedChangeListener(this);
        initFragment();
        initViewPager(); // 调用initViewPager方法
        //默认布局，选第一个

    }



    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(0, new FirstFragment());
        fragments.add(1, new TimeFragment());
        fragments.add(2, new MeFragment());

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {


        if (i == R.id.news) {
            viewPager.setCurrentItem(0);
            textTop.setText(news.getText());
        } else if (i == R.id.time) {
            viewPager.setCurrentItem(1);
            textTop.setText(time.getText());
        } else if (i == R.id.me) {
            viewPager.setCurrentItem(2);
            textTop.setText(me.getText());
        }



    }
    private void initViewPager() {
        // 使用适配器将Fragment添加到ViewPager中
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(pagerAdapter);

        // ViewPager 页面切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 页面滑动时的回调
            }

            @Override
            public void onPageSelected(int position) {
                // 页面选中时的回调
                updateNavigationBar(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 页面滑动状态改变时的回调
            }
        });

        // 设置默认选中的页面
        viewPager.setCurrentItem(0);
        updateNavigationBar(0);
    }

    /**
     * 更新导航栏
     * @param position
     */
    private void updateNavigationBar(int position) {
        switch (position) {
            case 0:
                news.setChecked(true);
                time.setChecked(false);
                me.setChecked(false);
                textTop.setText(news.getText());
                break;
            case 1:
                news.setChecked(false);
                time.setChecked(true);
                me.setChecked(false);
                textTop.setText(time.getText());
                break;
            case 2:
                news.setChecked(false);
                time.setChecked(false);
                me.setChecked(true);
                textTop.setText(me.getText());
                break;
            default:
                break;
        }
    }

}