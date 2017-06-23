package com.glen.mysdk.okhttp;

import com.glen.mysdk.okhttp.https.HttpsUtils;
import com.glen.mysdk.okhttp.listener.DisposeDataHandle;
import com.glen.mysdk.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Gln on 2017/6/12.
 *
 * @function 请求的发送，请求参数的配置，https支持
 */
public class CommonOkHttpClient {
    private static final int TIEE_OUT = 30;//超时参数
    private static OkHttpClient mOkHttpClient;

    //为client配置参数
    static {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(TIEE_OUT, TimeUnit.SECONDS)
                .readTimeout(TIEE_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIEE_OUT, TimeUnit.SECONDS)
                .followRedirects(true)//https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * @param request
     * @param commCallback
     * @return Call
     * @function 发送具体的http/https请求
     */
    public static Call sendRequest(Request request, CommonJsonCallback commCallback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallback);

        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));

        return call;
    }

    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));

        return call;
    }

}
