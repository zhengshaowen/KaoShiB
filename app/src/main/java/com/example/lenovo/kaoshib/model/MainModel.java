package com.example.lenovo.kaoshib.model;

import com.example.lenovo.kaoshib.bean.ProBean;
import com.example.lenovo.kaoshib.server.ApiServer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2019/9/1.
 */

public class MainModel {

    public void requestServer(int page, final Questray questray){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer server = retrofit.create(ApiServer.class);
        server.getServer(page,294)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProBean proBean) {
                        if (proBean != null && proBean.getData() != null &&
                                proBean.getData().getDatas()!= null){
                            List<ProBean.DataBean.DatasBean> datas = proBean.getData().getDatas();
                            questray.onSuccess(datas);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        questray.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface Questray{
        void onSuccess(List<ProBean.DataBean.DatasBean> datas);
        void onFail(String str);
    }
}
