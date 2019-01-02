package com.alliance.gps_intern;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    Context context;
    List<Upload> mList;

   RecyclerAdapter(Context context,List<Upload> mList){
       this.context=context;
       this.mList=mList;

   }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_item, parent,
                false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mName.setText(mList.get(position).getName());
            String url=mList.get(position).getUrl();
            Glide.with(context).load(url).into(holder.mImagel);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       View mView;
       ImageView mImagel;
       TextView mName;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            mImagel=mView.findViewById(R.id.single_img);
            mName=mView.findViewById(R.id.single_name);

        }
    }
}
