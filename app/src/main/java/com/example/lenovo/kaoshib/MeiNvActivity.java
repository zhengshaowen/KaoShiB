package com.example.lenovo.kaoshib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiNvActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_nv);
        ButterKnife.bind(this);
        initView();
        url = getIntent().getStringExtra("url");
    }

    private void initView() {
        Glide.with(this).load(url).into(iv);
    }
}
