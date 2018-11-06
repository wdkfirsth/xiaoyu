package com.wdk.xiaoyu.mvp.view;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wdk.xiaoyu.R;

/**
 * @class name: DivMenu
 * @anthor ：卫士
 * @time :Create on 2018/10/30  2:38
 */
public class DivMenu extends ActionProvider {

    private ImageView menuImg;
    private TextView menuTxt;
    public DivMenu(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        //系统menu的高度
        int size=getContext().getResources().getDimensionPixelSize(
                android.support.design.R.dimen.abc_action_bar_default_height_material
        );

        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(size,size);
        View view= LayoutInflater.from(getContext())
                .inflate(R.layout.layout_divmenu,null,false);

        view.setLayoutParams(layoutParams);
        menuImg=(ImageView)view.findViewById(R.id.menu_img);
        menuTxt=(TextView)view.findViewById(R.id.menu_txt);


        return view;
    }
    //设置图标
    public void setIcon(@DrawableRes int incon){
        menuImg.setImageResource(incon);
    }

    //设置文字
    public void setTextInt(@StringRes int i){
        menuTxt.setText(i);
    }

    //设置显示的文字
    public void setText(CharSequence i){
        menuTxt.setText(i);
    }

}
