package com.example.lenovo.kaoshib;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.kaoshib.adapter.ViewAdapter;
import com.example.lenovo.kaoshib.bean.ProBean;
import com.example.lenovo.kaoshib.bean.PsBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 友盟：5d6b68fe570df3ba3100062e
 * 极光：00585b83f51a5d4402e2a45a
 * 地图ak：UhC6Dk0fc5KSxfqP4XU9epbmeTRGBDU6
 */
public class ViewpageActivity extends AppCompatActivity {

    @BindView(R.id.toolbar1)
    Toolbar toolbar1;
    @BindView(R.id.viewPager1)
    ViewPager viewPager1;
    @BindView(R.id.viewPager_name)
    TextView viewPagerName;
    private Button btn;
    private ViewAdapter viewAdapter;
    private ArrayList<ProBean.DataBean.DatasBean> list;
    private int index;
    private PopupWindow popupWindow;
    private Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        int i = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i == PackageManager.PERMISSION_GRANTED) {

        } else {
            String[] pers = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, pers, 100);
        }
        initView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            int grantResult = grantResults[0];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        toolbar1.setTitle("");
        setSupportActionBar(toolbar1);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void eventbus(PsBean ps){
        list = ps.getList();
        Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show();
        index = ps.getIndex();
        viewAdapter = new ViewAdapter(list);
        viewPager1.setAdapter(viewAdapter);
        viewPager1.setCurrentItem(index);
        viewAdapter.setMyonLongclike(new ViewAdapter.MyonLongclike() {
            @Override
            public void onLongclike(int position) {
                View view = LayoutInflater.from(ViewpageActivity.this).inflate(R.layout.po,null);
                btn = view.findViewById(R.id.btn);
                map = view.findViewById(R.id.map);
                popupWindow = new PopupWindow(view, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
                //popupWindow.setBackgroundDrawable(null);
                popupWindow.setOutsideTouchable(true);
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 0.3f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
                getWindow().setAttributes(attributes);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams attributes = getWindow().getAttributes();
                        attributes.alpha = 1.0f;
                        getWindow().addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
                        getWindow().setAttributes(attributes);
                    }
                });
                popupWindow.showAsDropDown(view, Gravity.CENTER,0,0);
                //点击分享
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        shareBoard();
                    }
                });
                //点击地图
                map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        startActivity(new Intent(ViewpageActivity.this,MapActivity.class));
                    }
                });
            }
        });
        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPagerName.setText("第"+position+"张/共"+ list.size()+"张");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void shareBoard() {
        UMImage image = new UMImage(this, list.get(index).getEnvelopePic());
        new ShareAction(ViewpageActivity.this)
                .withText("hello")
                .withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ViewpageActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ViewpageActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ViewpageActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
