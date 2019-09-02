package com.example.lenovo.kaoshib;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.kaoshib.adapter.FragmentAdapter;
import com.example.lenovo.kaoshib.fragment.HomeFragment;
import com.example.lenovo.kaoshib.fragment.WebpageFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ArrayList<Fragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        WebpageFragment webpageFragment = new WebpageFragment();
        fragments.add(homeFragment);
        fragments.add(webpageFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdapter);
        tablayout.setupWithViewPager(viewPager);

        for (int i = 0; i < fragments.size(); i++) {
            tablayout.getTabAt(i).setCustomView(setCustomView(i));
        }
    }

    public View setCustomView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
        ImageView images = view.findViewById(R.id.images);
        TextView name = view.findViewById(R.id.name);
        switch (i){
            case 0:
                title.setText("首页");
                name.setText("首页");
                Glide.with(this).load(R.mipmap.menu_home).into(images);
                break;
            case 1:
                //title.setText("网页");
                name.setText("网页");
                Glide.with(this).load(R.mipmap.menu_history).into(images);
                break;
        }
        return view;
    }

}
