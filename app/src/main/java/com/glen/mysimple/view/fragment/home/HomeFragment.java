package com.glen.mysimple.view.fragment.home;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.glen.mysdk.okhttp.listener.DisposeDataListener;
import com.glen.mysimple.R;
import com.glen.mysimple.adapter.CourseAdapter;
import com.glen.mysimple.module.recommand.BaseRecommandModel;
import com.glen.mysimple.module.recommand.RecommandBodyValue;
import com.glen.mysimple.network.http.RequestCenter;
import com.glen.mysimple.view.fragment.BaseFragment;
import com.glen.mysimple.view.home.HomeHeaderLayout;

/**
 * Created by Gln on 2017/6/12.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public final String TAG = "sss";

    private View mContentView;
    private ListView mListView;

    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;

    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandData;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    /**
     * 发送首页列表数据请求
     */
    private void requestRecommandData() {
        //Log.e(TAG, "开始前 " );
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                //完成真正的功能逻辑
                Log.e(TAG, "onSuccess: " + responseObj.toString());
                /**
                 * 获取到数据后更新UI
                 */
                mRecommandData = (BaseRecommandModel) responseObj;
                showSuccessView();
            }


            @Override
            public void onFailure(Object reasonObj) {
                //提示用户网络有问题
                Log.e(TAG, "onFailure: " + reasonObj.toString());
            }
        });
        // Log.e(TAG, "结束了 " );
    }

    /**
     * 请求成功执行的方法
     */
    private void showSuccessView() {
        //判断数据是否为空
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            //为ListView添加列表头
            mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));
            //创建我们的adapter
            mAdapter = new CourseAdapter(mContext, mRecommandData.data.list);
            mListView.setAdapter(mAdapter);
        } else {
            showErrorView();
        }
    }

    /**
     * 请求失败执行的方法
     */
    private void showErrorView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
//        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
//        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
        //启动Loading动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
