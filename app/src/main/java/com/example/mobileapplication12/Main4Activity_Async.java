package com.example.mobileapplication12;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main4Activity_Async extends AppCompatActivity {
    TextView tv;
    ProgressBar pb;
    myTasks task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4__async);
        tv = (TextView)findViewById(R.id.tv);
        pb = (ProgressBar)findViewById(R.id.progressBar);
    }

    class myTasks extends AsyncTask<Integer,Integer,Void>{
        @Override
        protected void onPreExecute() {//UI직접 건드림
            super.onPreExecute();
            pb.setProgress(0);
            tv.setTextColor(Color.RED);
            tv.setText("진행률 : 0%");
        }

        @Override
        protected Void doInBackground(Integer... integers) {//반드시 구현해야
            for(int i = 1; i < 101; i++){
                if(isCancelled() == true)return null;
                try {
                    Thread.sleep(200);
                    publishProgress(i);//pb업데이트해주는
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
            tv.setText("진행률 : "+values[0]+"%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setProgress(100);
            tv.setTextColor(Color.BLUE);
            tv.setText("완료");
        }

        @Override
        protected void onCancelled() {//isCancled가 true이면 여기로 옴
            super.onCancelled();
            tv.setText("취소");

        }
    }

    public void onClick(View view){
        if(view.getId() == R.id.bStart){
            task = new myTasks();
            task.execute(0);
        }
        else if(view.getId() == R.id.bStop){
            task.cancel(true);
            task = null;
        }
    }
}
