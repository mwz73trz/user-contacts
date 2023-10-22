package com.mzura.model;

public class Grouping {

    private int groupingId;
    private String title;
    private int user_id;

    public Grouping() {};

    public Grouping(int grouping_id, String title, int user_id) {
        this.groupingId = grouping_id;
        this.title = title;
        this.user_id = user_id;
    }

    public int getGroupingId() {
        return groupingId;
    }

    public void setGroupingId(int groupingId) {
        this.groupingId = groupingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
