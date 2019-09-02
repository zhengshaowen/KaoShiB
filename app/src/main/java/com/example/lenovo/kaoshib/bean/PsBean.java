package com.example.lenovo.kaoshib.bean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/9/1.
 */

public class PsBean {

    private ArrayList<ProBean.DataBean.DatasBean> list;
    private int index;

    public PsBean(ArrayList<ProBean.DataBean.DatasBean> list, int index) {
        this.list = list;
        this.index = index;
    }

    public ArrayList<ProBean.DataBean.DatasBean> getList() {
        return list;
    }

    public void setList(ArrayList<ProBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "PsBean{" +
                "list=" + list +
                ", index=" + index +
                '}';
    }
}
