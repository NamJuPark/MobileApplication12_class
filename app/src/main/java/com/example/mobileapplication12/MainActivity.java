package com.example.mobileapplication12;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView counter;
    int index;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        counter = (TextView)findViewById(R.id.tv);
    }

    public void onClick(View view){
        if(view.getId() == R.id.button){
            MyThread mythread = new MyThread();
            mythread.setDaemon(true);
            mythread.start();
        }
    }

    public class MyThread extends Thread{
        int count = 0;
        @Override
        public void run() {//꼭 구현해야 하는 함수
            super.run();

            for( index = 0; index < 10; index++){
//                try {
//                    Thread.sleep(1000);
//
//                    //UI의 객체를 참조할 수가 없다! -> 중간 모듈이 필요한데 그게 핸들러
//                    // counter.setText("숫자 : " + i);
//
//                    //이 안에서만 UI에 접근 가능
////                    mHandler.post(new Runnable() {
////                        @Override
////                        public void run() {
////                            counter.setText("숫자 : " + index);
////                        }
////                    });
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Message msg = mHandler.obtainMessage();
                try {
                    Thread.sleep(1000);
                    count++;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
