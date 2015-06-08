package com.kmong.testproject;

/**
 * Created by GON on 2015-05-11.
 */
public class BananaItem {

    private int icon;
    private String name;
    private String cnt;


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }


    @Override
    public String toString() {
        return "BananaItem{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", cnt='" + cnt + '\'' +
                '}';
    }
}
