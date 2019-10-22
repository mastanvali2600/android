package com.example.am;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.am.Adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager homeViewPager;
    TabLayout homeTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpViewPager(homeViewPager);
        homeTabs.setupWithViewPager(homeViewPager);
    }

    private void setUpViewPager(ViewPager homeViewPager) {
        ViewPageAdapter viewPageAdapter= new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new CreateMatchFragment(),"Create Match");
        viewPageAdapter.addFragment(new JoinMatchFragment(),"Join Match");
        homeViewPager.setAdapter(viewPageAdapter);
    }

    private void init() {
        homeViewPager=(ViewPager)findViewById(R.id.homeviewpager);
        homeTabs=(TabLayout)findViewById(R.id.hometabs);
    }

}
