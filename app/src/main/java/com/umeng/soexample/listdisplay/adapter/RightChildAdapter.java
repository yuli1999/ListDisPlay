package com.umeng.soexample.listdisplay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.listdisplay.Bean.RightBean;
import com.umeng.soexample.listdisplay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/11/20
 * author: HJL (磊)
 * function:
 */

public class RightChildAdapter extends RecyclerView.Adapter<RightChildAdapter.MyViewHolder>{
    private Context context;
    private List<RightBean.DataBean.ListBean> list = new ArrayList<>();

    public RightChildAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_child_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mtxt.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.mpic);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mpic;
        TextView mtxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            mpic = itemView.findViewById(R.id.ri_pic);
            mtxt = itemView.findViewById(R.id.ri_txt);
        }
    }


}
