package com.ccx.demochenapp.entity;

import android.graphics.drawable.Drawable;

/**
 * @author chuangxin.chen
 */
public class News {
    /**
     * 新闻标题
     */
    public String title;
    /**
     * 作者
     */
    public String author;

    /**
     * 新闻内容
     */
    public String newsContent;
    /**
     * 新闻时间
     */
    public String dataTime;
    /**
     * 新闻图片
     */
    public int[] icon;

    public int type;

    public News() {
    }

    public News(String title, String author, String newsContent, String dataTime, int[] icon, int type) {
        this.title = title;
        this.author = author;
        this.newsContent = newsContent;
        this.dataTime = dataTime;
        this.icon = icon;
        this.type = type;
    }

    public News setType(int type) {
        this.type = type;
        return this;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public News setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public News setNewsContent(String newsContent) {
        this.newsContent = newsContent;
        return this;
    }

    public String getDataTime() {
        return dataTime;
    }

    public News setDataTime(String dataTime) {
        this.dataTime = dataTime;
        return this;
    }

    public int[] getIcon() {
        return icon;
    }

    public News setIcon(int[] icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", dataTime='" + dataTime + '\'' +
                ", icon=" + icon + '\'' +
                ",type=’" + type + '\'' +
                '}';
    }
}
