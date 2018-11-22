package com.umeng.soexample.listdisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.umeng.soexample.listdisplay.Bean.LeftBean;
import com.umeng.soexample.listdisplay.Bean.RightBean;
import com.umeng.soexample.listdisplay.adapter.LeftAdapter;
import com.umeng.soexample.listdisplay.adapter.RightAdapter;
import com.umeng.soexample.listdisplay.net.HttpOk;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mLeftRe;
    private RecyclerView mRightRe;
    private LeftAdapter leftAdapter;
    private List<LeftBean.DataBean> leftlist;
    private RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //获取左侧数据
        doHttpLeft();
        //右侧数据
        doHttpRight(1);
        //左边
        leftAdapter = new LeftAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mLeftRe.setLayoutManager(manager);
        mLeftRe.setAdapter(leftAdapter);

        //右侧
        rightAdapter = new RightAdapter(this);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        mRightRe.setLayoutManager(manager1);
        mRightRe.setAdapter(rightAdapter);


        //点击事件
        leftAdapter.setCilck(new LeftAdapter.ClickListener() {
            @Override
            public void onitemCilck(int position) {
                //传cid的值
                int cid = leftlist.get(position).getCid();
                for (int i = 0; i < leftlist.size(); i++) {
                    doHttpRight(cid);
                }
                leftAdapter.notifyDataSetChanged();
            }
        });

    }

    //右侧
    private void doHttpRight(int cid) {
        String url = "http://www.zhaoapi.cn/product/getProductCatagory?cid=" + cid;
        new HttpOk().get(url).Result(new HttpOk.OkHttpListener() {
            @Override
            public void success(String data) {
                RightBean rightBean = new Gson().fromJson(data, RightBean.class);
                rightAdapter.setRightlist(rightBean.getData());
            }
        });
    }

    //左侧
    private void doHttpLeft() {
        String url = "http://www.zhaoapi.cn/product/getCatagory";
        new HttpOk().get(url).Result(new HttpOk.OkHttpListener() {
            @Override
            public void success(String data) {
                LeftBean bean = new Gson().fromJson(data, LeftBean.class);
                leftlist = bean.getData();
                leftAdapter.setLeftlist(leftlist);
            }
        });
    }

    private void initView() {
        mLeftRe = findViewById(R.id.left_re);
        mRightRe = findViewById(R.id.right_re);
    }
}
