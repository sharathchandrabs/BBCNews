package com.example.shara.bbcnews;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by shara on 3/17/2017.
 */

public class News implements Comparable<News>{
    public long _id;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String title,thumbNailImage, newsLink;
    public Date publishedDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbNailImage() {
        return thumbNailImage;
    }

    public void setThumbNailImage(String thumbNailImage) {
        this.thumbNailImage = thumbNailImage;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }


    @Override
    public int compareTo(@NonNull News o) {
        return o.getPublishedDate().compareTo(this.getPublishedDate());
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", thumbNailImage='" + thumbNailImage + '\'' +
                ", newsLink='" + newsLink + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}
