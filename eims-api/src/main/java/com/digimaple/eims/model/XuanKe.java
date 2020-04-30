package com.digimaple.eims.model;

import javax.persistence.*;

@Entity
@IdClass(com.digimaple.eims.model.XuanKePrimaryKey.class)
public class XuanKe {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Id
  @Column(name = "stid", nullable = false)
  private Integer stid;

  @Id
  @Column(name = "kcid", nullable = false)
  private Integer kcid;





  public Integer getId() {
    return id;
  }

  public XuanKe setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getStid() {
    return stid;
  }

  public void setStid(Integer stid) {
    this.stid = stid;
  }

  public Integer getKcid() {
    return kcid;
  }

  public void setKcid(Integer kcid) {
    this.kcid = kcid;
  }
}
