package com.umeng.soexample.listdisplay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.listdisplay.Bean.RightBean;
import com.umeng.soexample.listdisplay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/11/20
 * author: HJL (磊)
 * function:
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {

    private Context context;
    private List<RightBean.DataBean> rightlist = new ArrayList<>();

    public RightAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mRightname.setText(rightlist.get(position).getName());
        List<RightBean.DataBean.ListBean> list = rightlist.get(position).getList();
        //获取子条目的Receiver适配器
        RightChildAdapter childAdapter = new RightChildAdapter(context, list);
        //布局管理器,网格布局
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        //设置布局,和适配器
        holder.mRightre.setLayoutManager(layoutManager);
        holder.mRightre.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return rightlist.size();
    }

    public void setRightlist(List<RightBean.DataBean> rightlist) {
        this.rightlist = rightlist;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mRightname;
        RecyclerView mRightre;

        public MyViewHolder(View itemView) {
            super(itemView);
            mRightname = itemView.findViewById(R.id.right_name);
            mRightre = itemView.findViewById(R.id.right_re);
        }
    }


}
