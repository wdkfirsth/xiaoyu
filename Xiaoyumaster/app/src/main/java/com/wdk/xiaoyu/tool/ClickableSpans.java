package com.wdk.xiaoyu.tool;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * @class name: ClickableSpan
 * @anthor ：卫士
 * @time :Create on 2018/10/29  6:49
 */
public class ClickableSpans extends ClickableSpan {


    public  ClickableSpans(){
        super();
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        //设置颜色
        ds.setColor(Color.BLUE);
        //去掉下划线
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
    }


}
