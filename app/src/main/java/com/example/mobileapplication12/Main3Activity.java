package com.example.mobileapplication12;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    EditText etName,etData;
    Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String name2 = (String)msg.obj;
            etData.setText(name2);
        }
    };

    @Override
    // 이것만 해주면 루퍼를 종료시킬 수 있어!!!푠을 깔끔하게 쓰자구우!?
    protected void onDestroy() {
        super.onDestroy();
        sub.subhandler.getLooper().quit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        etName = (EditText)findViewById(R.id.etName);
        etData = (EditText)findViewById(R.id.etData);
        sub.start();
    }
    MyThread sub = new MyThread();

    class MyThread extends Thread{
        subHandler subhandler = new subHandler();
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Looper.loop();
        }
    }

    class subHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String name = (String) msg.obj;
            name = "남주가 최고다! "+name;

            Message msg2 = Message.obtain();
            msg2.obj = name;
            mainHandler.sendMessage(msg2);
        }
    }

    public void onClick(View view){
        if(view.getId() == R.id.buttonSend){
            String name = etName.getText().toString();

            Message msg = Message.obtain();
            msg.obj = name;

            sub.subhandler.sendMessage(msg);//이거 받은 거 처리하는건 섭의 섭핸들러
        }
    }
}
