package com.wdk.xiaoyu.net.userandfriendsbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @class name: GetUserAndFriendWebId
 * @anthor ：卫士
 * @time :Create on 2018/10/30  18:39
 */
public class GetUserAndFriendWebId {
    private List<String> statuses=new ArrayList<>();
    private long previous_cursor;
    private long next_cursor;
    private int total_number;

    public long getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public long getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }


    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }
}
