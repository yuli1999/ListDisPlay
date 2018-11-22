package com.umeng.soexample.listdisplay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.soexample.listdisplay.Bean.LeftBean;
import com.umeng.soexample.listdisplay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2018/11/20
 * author: HJL (磊)
 * function:
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private Context context;
    private List<LeftBean.DataBean> leftlist = new ArrayList<>();

    public LeftAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.left_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mleftname.setText(leftlist.get(position).getName());
        holder.mleftname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onitemCilck(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return leftlist.size();
    }

    public void setLeftlist(List<LeftBean.DataBean> leftlist) {
        this.leftlist = leftlist;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mleftname;
        RelativeLayout mlayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mleftname = itemView.findViewById(R.id.left_name);
            mlayout = itemView.findViewById(R.id.layout);
        }
    }

    //点击事件接口回调
    private ClickListener clickListener;

    public void setCilck(ClickListener clickListener){
       this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onitemCilck(int position);
    }

}
