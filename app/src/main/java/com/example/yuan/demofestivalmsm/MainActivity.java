package com.example.yuan.demofestivalmsm;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuan.demofestivalmsm.fragment.FestivalFragment;

public class MainActivity extends AppCompatActivity
{
    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private String[] mTitles = new String[]{"节日祝福","短信记录"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews()
    {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return new FestivalFragment();
            }

            @Override
            public int getCount()
            {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return mTitles[position];
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
