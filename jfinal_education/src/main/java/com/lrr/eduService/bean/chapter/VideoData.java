package com.lrr.eduService.bean.chapter;

public class VideoData {
    private String id;
    private String title;
    private int isFree;
    private String videoSourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getVideoSourceId() {
        return videoSourceId;
    }

    public void setVideoSourceId(String videoSourceId) {
        this.videoSourceId = videoSourceId;
    }

    public VideoData() {
    }

    public VideoData(String id, String title, int isFree, String videoSourceId) {
        this.id = id;
        this.title = title;
        this.isFree = isFree;
        this.videoSourceId = videoSourceId;
    }
}
