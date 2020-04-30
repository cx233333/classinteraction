package com.digimaple.eims.repository;

import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.XuanKe;
import com.digimaple.eims.model.view.XuanKeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface XuanKeRepository extends JpaRepository<XuanKe, Long> {
    @Transactional
    void deleteById(Integer id);

    @Query("select x.id as id ,x.stid as stid ,s.sname as sname,s.sno as sno," +
            "x.kcid as kcid,c.kcname as kcname,t.jsname as jsname from" +
            " Student s,Course c,Teacher t, XuanKe x " +
            "where s.id = x.stid and c.id=x.kcid and t.id = c.jsid")
    List<XuanKeView> findList();

    @Query("select x.id as id ,x.stid as stid ,s.sname as sname,s.sno as sno," +
            "x.kcid as kcid,c.kcname as kcname,t.jsname as jsname from" +
            " Student s,Course c,Teacher t, XuanKe x " +
            "where s.id = x.stid and c.id=x.kcid and t.id = c.jsid and c.kcname like ?1 ")
    List<XuanKeView> findByKcnameLike(String kcname);



}

