package com.xiao.hui.aidldemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by xiaohui on 2018/1/5.
 */

public class MyBinder extends Binder implements MyPersonManage {

    /**
     * Construct the stub at attach it to the interface.
     */
    public MyBinder() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an com.xiao.hui.aidldemo.IMyAidlInterface interface,
     * generating a proxy if needed.
     */
    public static MyPersonManage asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof com.xiao.hui.aidldemo.IMyAidlInterface))) {
            return ((MyPersonManage) iin);
        }
        return new Proxy(obj);
    }

    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getValue: {
                data.enforceInterface(DESCRIPTOR);
                com.xiao.hui.aidldemo.Person _result = this.getValue();
                reply.writeNoException();
                if ((_result != null)) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
            default:
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public Person getValue() throws RemoteException {
        return new Person("renxh");
    }

    private static class Proxy implements MyPersonManage {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public com.xiao.hui.aidldemo.Person getValue() throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            com.xiao.hui.aidldemo.Person _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getValue, _data, _reply, 0);
                _reply.readException();
                if ((0 != _reply.readInt())) {
                    _result = com.xiao.hui.aidldemo.Person.CREATOR.createFromParcel(_reply);
                } else {
                    _result = null;
                }
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }
    }
}
