package com.example.lenovo.kaoshib;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.bikenavi.BikeNavigateHelper;
import com.baidu.mapapi.bikenavi.adapter.IBRouteGuidanceListener;
import com.baidu.mapapi.bikenavi.adapter.IBTTSPlayer;
import com.baidu.mapapi.bikenavi.model.BikeRouteDetailInfo;
import com.baidu.mapapi.walknavi.model.RouteGuideKind;

public class WNaviGuideActivity extends Activity {

    private BikeNavigateHelper mNaviHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wnavi_guide);

        //获取BikeNavigateHelper示例
        mNaviHelper = BikeNavigateHelper.getInstance();
// 获取诱导页面地图展示View
        View view = mNaviHelper.onCreate(WNaviGuideActivity.this);

        if (view != null) {
            setContentView(view);
        }

// 开始导航
        mNaviHelper.startBikeNavi(WNaviGuideActivity.this);

        // 设置诱导监听, 具体诱导信息请参考javadoc类参考
// com.baidu.mapapi.bikenavi.adapter -> IBRouteGuidanceListener
        mNaviHelper.setRouteGuidanceListener(this, new IBRouteGuidanceListener() {
            @Override
            public void onRouteGuideIconUpdate(Drawable icon) {
                //诱导图标更新
            }

            @Override
            public void onRouteGuideKind(RouteGuideKind routeGuideKind) {
                //诱导类型枚举
            }

            @Override
            public void onRoadGuideTextUpdate(CharSequence charSequence, CharSequence charSequence1) {
                //诱导信息
            }

            @Override
            public void onRemainDistanceUpdate(CharSequence charSequence) {
                //总的剩余距离
            }

            @Override
            public void onRemainTimeUpdate(CharSequence charSequence) {
                //总的剩余时间
            }

            @Override
            public void onGpsStatusChange(CharSequence charSequence, Drawable drawable) {
                //GPS状态发生变化，来自诱导引擎的消息
            }

            @Override
            public void onRouteFarAway(CharSequence charSequence, Drawable drawable) {
                //已经开始偏航
            }

            @Override
            public void onRoutePlanYawing(CharSequence charSequence, Drawable drawable) {
                //偏航规划中
            }

            @Override
            public void onReRouteComplete() {
                //重新算路成功
            }

            @Override
            public void onArriveDest() {
                //到达目的地
            }

            @Override
            public void onVibrate() {
                //震动
            }

            @Override
            public void onGetRouteDetailInfo(BikeRouteDetailInfo bikeRouteDetailInfo) {
                //获取骑行导航路线详细信息类
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mNaviHelper.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNaviHelper.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNaviHelper.quit();
    }
}
