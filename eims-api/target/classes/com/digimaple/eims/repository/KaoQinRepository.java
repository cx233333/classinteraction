package com.digimaple.eims.repository;

import com.digimaple.eims.model.KaoQin;
import com.digimaple.eims.model.view.KaoQinDateView;
import com.digimaple.eims.model.view.KaoQinView;
import com.digimaple.eims.model.Course;
import com.digimaple.eims.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface KaoQinRepository extends JpaRepository<KaoQin, Long> {





    KaoQin findByKcid(Integer kcid);

    KaoQin findByKcidAndStid(Integer kcid,Integer stid);

   // int countDistinctByKcidAndKqdate(Integer kcid);


    @Query(value = "select distinct kqdate as kqdate from kao_qin where kcid=?1"
            ,nativeQuery = true
    )
    List<KaoQinDateView> countKcidAndKqdate(Integer kcid);

//学生查询
    @Transactional
    @Query(value = "select t.kcid as kcid,t.stid stid,t.kqtime as kqtime," +
            "c.kcname as kcname,s.sname as sname from Kao_Qin t,Course c,Student s  " +
            "where if(?1 !='',t.kcid=?1,1=1) and if(?2 !='',t.stid=?2,1=1) and " +
            " if(?1 !='',c.id=?1,1=1) and if(?2 !='',s.id=?2,1=1)"
            ,nativeQuery = true
    )
    List<KaoQinView> findKcidStid(Integer kcid, Integer stid);

  //教师按课程和时间段查询
  @Transactional
  @Query(value = "select t.kcid as kcid,t.stid stid,t.kqtime as kqtime,t.kqdate as kqdate," +
          "c.kcname as kcname,s.sname as sname from Kao_Qin t,Course c,Student s  " +
          "where t.kcid=?1  and c.id = ?1  and t.stid = s.id and t.kcid = c.id and t.kqtime between ?2 and ?3"

          ,nativeQuery = true
  )
    List<KaoQinView> findKaoQinByKcidAndKqtimeBetween(Integer kcid, Date starttime ,Date endtime);

    //教师按课程和时间查询
    @Transactional
    @Query(value = "select t.kcid as kcid,t.stid stid,t.kqtime as kqtime,t.kqdate as kqdate," +
            "c.kcname as kcname,s.sname as sname from Kao_Qin t,Course c,Student s  " +
            "where t.kcid=?1  and c.id = ?1  and t.stid = s.id and t.kcid = c.id and t.kqdate =?2"

            ,nativeQuery = true
    )
    List<KaoQinView> findKaoQinByKcidAndKqdate(Integer kcid, Date kqdate);




}

