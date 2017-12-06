package com.biwe.day08.net;

/**
 * Created by VicYu on 2017/12/6.
 */

public interface onNetListener<T> {

    public void onSuccess(T t);
    public void onFaliuer(Exception e);
}
