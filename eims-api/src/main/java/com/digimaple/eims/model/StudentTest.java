package com.digimaple.eims.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(com.digimaple.eims.model.StudentTestPrimaryKey.class)
public class StudentTest {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Id
  @Column(name = "testid", nullable = false)
  private Integer testid;


  @Id
  @Column(name = "stid", nullable = false)
  private Integer stid;


  @Column(nullable = false, unique = false)
  private String answer;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(nullable = false, unique = false)
  private Date testtime;

  @Column(nullable = false, unique = false)
  private String score;


  public Integer getId() {
    return id;
  }

  public StudentTest setId(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getTestid() {
    return testid;
  }

  public void setTestid(Integer testid) {
    this.testid = testid;
  }

  public Integer getStid() {
    return stid;
  }

  public void setStid(Integer stid) {
    this.stid = stid;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public Date getTesttime() {
    return testtime;
  }

  public void setTesttime(Date testtime) {
    this.testtime = testtime;
  }
}
