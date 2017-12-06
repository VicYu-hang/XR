package com.biwe.day08.net;



import com.biwe.day08.bean.Bean;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by VicYu on 2017/12/6.
 */

public interface ServerApi {

    @GET(Api.URL)
    Observable<Bean> getBean();

}
