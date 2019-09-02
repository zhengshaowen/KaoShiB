package com.example.lenovo.kaoshib.presenter;


import com.example.lenovo.kaoshib.bean.ProBean;
import com.example.lenovo.kaoshib.model.MainModel;
import com.example.lenovo.kaoshib.view.MainView;

import java.util.List;

/**
 * Created by lenovo on 2019/9/1.
 */

public class MainPresenter implements MainModel.Questray{

    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModel();
    }

    public void requestServer(int page){
        mainModel.requestServer(page,this);
    };

    @Override
    public void onSuccess(List<ProBean.DataBean.DatasBean> datas) {
        mainView.onSuccess(datas);
    }

    @Override
    public void onFail(String str) {
        mainView.onFail(str);
    }
}
