package com.example.lenovo.kaoshib.server;

import com.example.lenovo.kaoshib.bean.ProBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/9/1.
 */

public interface ApiServer {

    String url = "https://www.wanandroid.com/";

    @GET("project/list/{page}/json")
    Observable<ProBean> getServer(@Path("page") int page, @Query("cid") int cid);
}
