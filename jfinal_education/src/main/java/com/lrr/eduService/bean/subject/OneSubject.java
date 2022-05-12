package com.lrr.eduService.bean.subject;

import java.util.ArrayList;
import java.util.List;

public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> childrens = new ArrayList<TwoSubject>();

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

    public List<TwoSubject> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<TwoSubject> childrens) {
        this.childrens = childrens;
    }

    public OneSubject() {
    }

    public OneSubject(String id, String title, List<TwoSubject> childrens) {
        this.id = id;
        this.title = title;
        this.childrens = childrens;
    }
}
