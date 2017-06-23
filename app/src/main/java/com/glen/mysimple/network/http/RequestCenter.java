package com.glen.mysimple.network.http;


import com.glen.mysdk.okhttp.CommonOkHttpClient;
import com.glen.mysdk.okhttp.listener.DisposeDataHandle;
import com.glen.mysdk.okhttp.listener.DisposeDataListener;
import com.glen.mysdk.okhttp.request.CommonRequest;
import com.glen.mysdk.okhttp.request.RequestParams;
import com.glen.mysimple.module.recommand.BaseRecommandModel;

/**
 * @author: vision
 * @function: 存放应用中所有的请求
 * @date: 16/8/12
 */
public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 真正发送我们的首页请求
     * @param listener
     */
    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener,
                BaseRecommandModel.class);
    }
}
