package com.glen.mysdk;

import android.support.v7.app.AppCompatActivity;

import com.glen.mysdk.okhttp.CommonOkHttpClient;
import com.glen.mysdk.okhttp.listener.DisposeDataHandle;
import com.glen.mysdk.okhttp.listener.DisposeDataListener;
import com.glen.mysdk.okhttp.request.CommonRequest;
import com.glen.mysdk.okhttp.response.CommonJsonCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Gln on 2017/6/12.
 */
public class BaseOkhttpTestActivity extends AppCompatActivity {
    private void sendRequest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        final Request request = new Request.Builder()
                .url("https://www.imooc.com/")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    private void test() {
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("http://www.imooc.com", null),
                new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {

                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                })));
    }
}
