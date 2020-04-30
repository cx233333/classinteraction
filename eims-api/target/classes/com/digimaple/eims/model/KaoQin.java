package com.digimaple.eims.model;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@IdClass(com.digimaple.eims.model.KaoQinPrimaryKey.class)
public class KaoQin {



  @Id
  @Column(name = "stid", nullable = false)
  private Integer stid;

  @Id
  @Column(name = "kcid", nullable = false)
  private Integer kcid;

  @Id
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  @Column(name = "kqdate", nullable = false)
  private Date kqdate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(name = "kqtime", nullable = false)
  private Date kqtime;



  public Integer getStid() {
    return stid;
  }

  public KaoQin setStid(Integer stid) {
    this.stid = stid;
    return this;
  }

  public Integer getKcid() {
    return kcid;
  }

  public KaoQin setKcid(Integer kcid) {
    this.kcid = kcid;
    return this;
  }

  public Date getKqdate() {
    return kqdate;
  }

  public void setKqdate(Date kqdate) {
    this.kqdate = kqdate;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date getKqtime() {
    return kqtime;
  }

  public KaoQin setKqtime(Date kqtime) {
    this.kqtime = kqtime;
    return this;
  }
}
