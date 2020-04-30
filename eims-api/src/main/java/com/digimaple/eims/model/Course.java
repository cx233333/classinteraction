package com.digimaple.eims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Course {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false, unique = false)
  private  String kcname;

  @Column(nullable = false, unique = false)
  private Integer jsid;





  public Integer getId() {
    return id;
  }

  public Course setId(Integer id) {
    this.id = id;
    return this;
  }



  public String getKcname() {
    return kcname;
  }

  public Course setKcname(String kcname) {
    this.kcname = kcname;
    return this;
  }

  public Integer getJsid() {
    return jsid;
  }

  public Course setJsid(Integer jsid) {
    this.jsid = jsid;
    return this;
  }
}
