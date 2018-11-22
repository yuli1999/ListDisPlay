package com.umeng.soexample.listdisplay.net;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * data:2018/11/20
 * author: HJL (磊)
 * function:
 */

public class HttpOk {
    public HttpOk(){}

    public HttpOk get(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(url).build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                Message message = new Message();
                message.obj = data;
                handler.sendMessage(message);
            }
        });
        return this;
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            okHttpListener.success(data);
        }
    };

    private OkHttpListener okHttpListener;

    public void Result(OkHttpListener okHttpListener){
        this.okHttpListener = okHttpListener;
    }


    public interface OkHttpListener{
        void success(String data);
    }

}
