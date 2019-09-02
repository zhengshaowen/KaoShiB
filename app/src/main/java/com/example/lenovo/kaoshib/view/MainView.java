package com.example.lenovo.kaoshib.view;

import com.example.lenovo.kaoshib.bean.ProBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/1.
 */

public interface MainView {
    void onSuccess(List<ProBean.DataBean.DatasBean> datas);
    void onFail(String str);
}
