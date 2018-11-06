package com.wdk.xiaoyu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wdk.xiaoyu.R;
import com.wdk.xiaoyu.net.publicWebBean.GetPublicWebs;
import com.wdk.xiaoyu.tool.ClickableSpans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @class name: IndexRecyclerViewAdapter
 * @anthor ：卫士
 * @time :Create on 2018/10/23  23:17
 */
public class IndexUserWebAdapter extends RecyclerView.Adapter<IndexUserWebAdapter.ViewHolder> {

    private Context mContext;
    private View view;
    private List<GetPublicWebs.GetPublicWeb> getPublicWebList;

    public IndexUserWebAdapter(List<GetPublicWebs.GetPublicWeb> getPublicWebList){
        getPublicWebList=new ArrayList<>();
        this.getPublicWebList=getPublicWebList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext=viewGroup.getContext();
        view= LayoutInflater.from(mContext).inflate(R.layout.layout_card_recycler_index,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        GetPublicWebs.GetPublicWeb getPublicWeb=getPublicWebList.get(i);
        GetPublicWebs.User user=getPublicWeb.getUser();
        Glide.with(mContext).load(user.getProfile_image_url()).into(viewHolder.cardCir);
        viewHolder.cardTitle.setText(user.getScreen_name());
        //设置时间
        Date date=new Date(getPublicWeb.getCreated_at());
        viewHolder.txtTime.setText(""+date.getMonth()+"-"+date.getDate());
        viewHolder.txtTimes.setText(""+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
        //超链接

        CharSequence charSequence = Html.fromHtml(String.valueOf(getPublicWeb.getSource()));
        SpannableString spannableString=new SpannableString(charSequence);
        //设置无下划线
        spannableString.setSpan(new ClickableSpans(),0,spannableString.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置颜色
//        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),0,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.txtGj.setText(spannableString);
        viewHolder.txtGj.setMovementMethod(LinkMovementMethod.getInstance());
        //添加文本内容和超链接
        if(!"".equals(getPublicWeb.getText())&&getPublicWeb.getText()!=null){
            viewHolder.txtCt.setVisibility(View.VISIBLE);
            String s=getPublicWeb.getText();

            if(s.contains("http://")){
                viewHolder.txtCt.setText(s.split("http://")[0]+"  ");
                SpannableString str=new SpannableString("资源链接");
                str.setSpan(new URLSpan("http://"+s.split("http://")[1]),0,str.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                str.setSpan(new ForegroundColorSpan(Color.BLUE),0,str.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.txtCt.append(str);
                viewHolder.txtCt.setMovementMethod(LinkMovementMethod.getInstance());
            }else{
                viewHolder.txtCt.setText(s);
            }

        }

        //添加图片

        if(!"".equals(getPublicWeb.getOriginal_pic())&&getPublicWeb.getOriginal_pic()!=null){
            viewHolder.imgCt.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(getPublicWeb.getOriginal_pic()).into(viewHolder.imgCt);
        }


        viewHolder.txtSend.setText("  "+getPublicWeb.getReposts_count());
        viewHolder.txtRp.setText("  "+getPublicWeb.getComments_count());
        viewHolder.txtGood.setText("  "+getPublicWeb.getAttitudes_count());
    }

    @Override
    public int getItemCount() {
        return getPublicWebList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private   CircleImageView cardCir;
        private   TextView cardTitle;
        private   CircleImageView cardCirLg;
        private   TextView txtTime;
        private   TextView txtTimes;
        private   TextView txtGj;
        private   ImageView imgMore;
        private   TextView txtCt;
        private   ImageView imgCt;
        private   GridView carGv;
        private   TextView txtSend;
        private   TextView txtRp;
        private   TextView txtGood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardCir=(CircleImageView)itemView.findViewById(R.id.card_cir);
            cardTitle=(TextView)itemView.findViewById(R.id.card_title);
            cardCirLg=(CircleImageView)itemView.findViewById(R.id.card_cir_lg);
            txtTime=(TextView)itemView.findViewById(R.id.txt_time);
            txtTimes=(TextView)itemView.findViewById(R.id.txt_times);
            txtGj=(TextView)itemView.findViewById(R.id.txt_gj);
            imgMore=(ImageView)itemView.findViewById(R.id.img_more);
            txtCt=(TextView)itemView.findViewById(R.id.txt_ct);
            imgCt=(ImageView)itemView.findViewById(R.id.img_ct);
            carGv=(GridView)itemView.findViewById(R.id.car_gv);
            txtSend=(TextView)itemView.findViewById(R.id.txt_send);
            txtRp=(TextView)itemView.findViewById(R.id.txt_rp);
            txtGood=(TextView)itemView.findViewById(R.id.txt_good);
        }
    }
}
