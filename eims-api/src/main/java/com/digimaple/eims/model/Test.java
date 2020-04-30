package com.digimaple.eims.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Test {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer kcid;

    @Column(nullable = false, unique = true)
    private Integer jsid;

    @Column(nullable = false, unique = false)
    private String testname;

    @Column(nullable = false, unique = false)
    private String testcontent;
            ;
    @Column(nullable = false, unique = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    @Column(nullable = false, unique = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKcid() {
        return kcid;
    }

    public void setKcid(Integer kcid) {
        this.kcid = kcid;
    }

    public Integer getJsid() {
        return jsid;
    }

    public void setJsid(Integer jsid) {
        this.jsid = jsid;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getTestcontent() {
        return testcontent;
    }

    public void setTestcontent(String testcontent) {
        this.testcontent = testcontent;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
