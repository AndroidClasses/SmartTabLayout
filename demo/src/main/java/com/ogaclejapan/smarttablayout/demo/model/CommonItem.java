package com.ogaclejapan.smarttablayout.demo.model;

/**
 * Created by yangfeng on 16-1-6.
 */
public class CommonItem {
    public String getmLabel() {
        return mLabel;
    }

    public void setmLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    private String mLabel;
    private String mTime;

    public CommonItem() {
        this("DEMO", System.currentTimeMillis());
    }

    public CommonItem(String name, long time) {
        this.mLabel = name;
        this.mTime = "" + time;
    }
}
