package com.xiao.hui.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiao.hui.aidldemo.IMyAidlInterface;
import com.xiao.hui.aidldemo.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private TextView mTextView;
    private IMyAidlInterface mService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.aidl_button);
        mButton2 = (Button) findViewById(R.id.aidl_button_get);
        mTextView = (TextView) findViewById(R.id.aidl_text);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aidl_button:
                Intent intent = new Intent();
                intent.setAction("com.example.lambert.aidlproject.MyService");
                //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
                intent.setPackage("com.xiao.hui.aidldemo");
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.aidl_button_get:
                try {
                    Person person = mService.getValue();
                    mTextView.setText(person.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
