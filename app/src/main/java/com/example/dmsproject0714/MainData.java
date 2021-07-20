package com.example.dmsproject0714;

public class MainData {
    private String tv_title;
    private String tv_content;
    private String tv_id;
    private String tv_createDate;
    private String tv_modifyDate;

    public MainData(String tv_title, String tv_content, String tv_id, String tv_createDate, String tv_modifyDate) {
        this.tv_title = tv_title;
        this.tv_content = tv_content;
        this.tv_id = tv_id;
        this.tv_createDate = tv_createDate;
        this.tv_modifyDate = tv_modifyDate;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

    public String getTv_content() {
        return tv_content;
    }

    public void setTv_content(String tv_content) {
        this.tv_content = tv_content;
    }

    public String getTv_id() {
        return tv_id;
    }

    public void setTv_id(String tv_id) {
        this.tv_id = tv_id;
    }

    public String getTv_createDate() {
        return tv_createDate;
    }

    public void setTv_createDate(String tv_createDate) {
        this.tv_createDate = tv_createDate;
    }

    public String getTv_modifyDate() {
        return tv_modifyDate;
    }

    public void setTv_modifyDate(String tv_modifyDate) {
        this.tv_modifyDate = tv_modifyDate;
    }
}