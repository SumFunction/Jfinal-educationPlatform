package com.lrr.eduService.bean.chapter;

import java.util.ArrayList;
import java.util.List;

/*
封装某课程章节以及视频的信息返回给前端
 */
public class ChapterData {
    private String id;
    private String title;
    private List<VideoData> children = new ArrayList<>();;

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

    public List<VideoData> getChildren() {
        return children;
    }

    public void setChildren(List<VideoData> children) {
        this.children = children;
    }

    public ChapterData() {
    }

    public ChapterData(String id, String title, List<VideoData> children) {
        this.id = id;
        this.title = title;
        this.children = children;
    }
}
