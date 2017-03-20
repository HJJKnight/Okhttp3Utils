package com.example.knight.myutils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.knight.libraryutils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.start_download)
    Button startDownload;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= (String) msg.obj;
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        startDownload = (Button) findViewById(R.id.start_download);
        startDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkUtils.getInstance(MainActivity.this).DownloadFile("http://192.168.20.105:8080/mv/test.mp4", new NetworkUtils.onDownloadListener() {
                    @Override
                    public void onSuccess(String msg) {

                        Message message = Message.obtain();
                        message.obj = msg;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onDownloading(int progress) {
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void onFailed(String msg) {
                        Message message = Message.obtain();
                        message.obj = msg;
                        handler.sendMessage(message);
                    }
                });
            }
        });

    }

    @OnClick(R.id.start_download)
    public void onClick() {


    }
}
