package com.xiao.hui.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("mmm", "绑定了");
        return new MyBinder();
    }

    class MyServer extends IMyAidlInterface.Stub {

        @Override
        public Person getValue() {
            return new Person("renxh3");
        }
    }

}
