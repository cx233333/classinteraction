package com.digimaple.eims.repository;

import com.digimaple.eims.model.Course;
import com.digimaple.eims.model.view.CourseView;
import com.digimaple.eims.model.view.KaoQinView;
import com.digimaple.eims.model.view.StudentCourseView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findById(Integer id);

    @Transactional
    void deleteById(Integer id);

    List<Course> findCoursesByJsid(Integer jsid) ;

    List<Course> findByKcname(String kcname);

//展示列表
    @Query("select c.id as kcid ,c.kcname as kcname ,c.jsid as jsid,t.jsname as jsname," +
            " t.jsno as jsno from" +
            " Course c,Teacher t " +
            "where c.jsid = t.id")
    List<CourseView> findListView();

    //教师查询缺勤学生
    @Transactional
    @Query(value = "select stid from Xuan_Ke x where x.kcid = ?1 and x.stid not in  select t.stid stid" +
            " from Kao_Qin t,Course c,Student s  " +
            "where if(?1 !='',t.kcid=?1,1=1) and  " +
            " if(?1 !='',c.id=?1,1=1) "
            ,nativeQuery = true
    )
        Course findQueQin(Integer kcid, Integer stid);


    //学生查询自己的课程
    @Transactional
    @Query(value = "select x.stid as stid,x.kcid as kcid ,c.kcname as kcname" +
            " from xuan_ke x,course c where  c.id = x.kcid and  x.stid= ?1"   ,nativeQuery = true)
    List<StudentCourseView> findStudentCourse(Integer stid);

}