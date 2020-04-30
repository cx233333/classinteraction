package com.digimaple.eims.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity

//@IdClass(FilePrimaryKey.class)
public class CourseFile {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = false)
    private Integer kcid;


    @Column(nullable = false, unique = false)
    private Integer fileid;

    @Column(nullable = false, unique = false)
    private String filename;

    @Column(nullable = false, unique = false)
    private String filepath;

    @Column(nullable = false, unique = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uptime;

    public  CourseFile() {}
    public  CourseFile(long id,Integer kcid,Integer fileid,String filename,String filepath) {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKcid() {
        return kcid;
    }

    public CourseFile setKcid(Integer kcid) {
        this.kcid = kcid;
        return this;
    }

    public Integer getFileid() {
        return fileid;
    }

    public CourseFile setFileid(Integer fileid) {
        this.fileid = fileid;
        return this;
    }

    public String getFilepath() {
        return filepath;
    }

    public CourseFile setFilepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getFilename() {
        return filename;
    }

    public CourseFile setFilename(String filename) {
        this.filename = filename;
        return this;
    }
}
