package com.biwe.day08.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biwe.day08.R;
import com.biwe.day08.bean.Bean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class Adapter extends XRecyclerView.Adapter<Adapter.MyViewHolder>{
    Context context;
    private TextView tv;
    private Bean bean;
    private SimpleDraweeView sim;
    private onItemClickListener itemClickListener;

    public Adapter(Context context,  Bean bean) {
        this.context=context;
        this.bean=bean;
    }

    public void setOnItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface onItemClickListener{
        void onItemClickListener(View v,int position);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position1 = holder.getLayoutPosition();
                itemClickListener.onItemClickListener(view,position1);
            }
        });
        tv.setText(bean.getNewslist().get(position).getTitle());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(bean.getNewslist().get(position).getPicUrl())
                .setAutoPlayAnimations(true)
                .build();
        sim.setController(controller);
    }



    @Override
    public int getItemCount() {
        return bean.getNewslist().size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            sim=itemView.findViewById(R.id.sim);
        }
    }

}