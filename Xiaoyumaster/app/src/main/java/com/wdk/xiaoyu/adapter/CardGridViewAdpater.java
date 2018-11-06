package com.wdk.xiaoyu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wdk.xiaoyu.R;
import com.wdk.xiaoyu.net.Pics;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;

import java.util.ArrayList;
import java.util.List;

/**
 * @class name: CardGridViewAdpater
 * @anthor ：卫士
 * @time :Create on 2018/11/1  6:24
 */
public class CardGridViewAdpater extends BaseAdapter {

    private Context mContext;
    private List<Pics> picsList;
    public CardGridViewAdpater(Context context, List<Pics> pics){
        picsList=new ArrayList<>();
        this.mContext=context;
        this.picsList=pics;
    }
    @Override
    public int getCount() {
        Log.i("pp","getCount()"+picsList.size());
        return picsList.size();

    }

    @Override
    public Object getItem(int position) {
        Log.i("pp","getItem()"+picsList.get(position));
        return picsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i("pp","getItemId"+position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView == null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.card_gv_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView=(ImageView)convertView.findViewById(R.id.img_gv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }



//        Glide.with(mContext).load(picsList.get(position).getThumbnail_pic()).into(viewHolder.imageView);
        viewHolder.imageView.setImageResource(R.drawable.ic_launcher_background);
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
    }
}
