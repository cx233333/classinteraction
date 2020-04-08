package com.digimaple.eims.model.urpModel;

import java.util.List;

public class Power {
    private Integer id;
    private String name;
    private String url;
    //private Integer grade;
    private Integer pid;
    //private Integer sort;
    //private String remark;
    private List<Power> subs;

    @Override
    public String toString() {
        return "Power{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", subs=" + subs +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Power> getSubs() {
        return subs;
    }

    public void setSubs(List<Power> subs) {
        this.subs = subs;
    }
}
