package com.xiao.hui.aidldemo;

import android.os.IBinder;
import android.os.IInterface;

/**
 * Created by xiaohui on 2018/1/5.
 */
//自定义binder类
public interface MyPersonManage extends IInterface {

     static final java.lang.String DESCRIPTOR = "com.xiao.hui.aidldemo.IMyAidlInterface";

    static final int TRANSACTION_getValue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);


    public com.xiao.hui.aidldemo.Person getValue() throws android.os.RemoteException;

}
