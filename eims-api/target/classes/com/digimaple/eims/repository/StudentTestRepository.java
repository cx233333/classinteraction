package com.digimaple.eims.repository;
import com.digimaple.eims.model.StudentTest;
import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.view.StudentTestView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface StudentTestRepository extends JpaRepository<StudentTest, Long> {

//  List<Test> findByKcid(Integer kcid);

    //教师查看学生提交测试答案记录
    @Transactional
    @Query(value = "select t.id as id,t.testid as testid,t.stid as stid,s.sname as sname ,s.sno as sno," +
            "e.kcid as kcid,c.kcname as kcname,t.score as score,t.answer as answer," +
            "t.testtime as testtime ,e.testname as testname " +
            " from  Student_Test t,Student s ,Test e,Course c " +
            "where  e.kcid=?1  and t.testid = e.id  and e.kcid = c.id and t.stid = s.id and t.testid = ?2"

            ,nativeQuery = true
    )
    List<StudentTestView> findStudentTestViewByKcidAndTestid(Integer kcid,Integer testid);




    //学生查看学生提交测试答案记录
    @Transactional
    @Query(value = "select t.id as id,t.testid as testid,t.stid as stid,s.sname as sname ,s.sno as sno," +
            "e.kcid as kcid,c.kcname as kcname,t.score as score,t.answer as answer," +
            "t.testtime as testtime ,e.testname as testname " +
            " from  Student_Test t,Student s ,Test e,Course c " +
            "where e.kcid=?1  and t.testid = e.id  and e.kcid = c.id and t.stid = s.id and t.stid =?2 "

            ,nativeQuery = true
    )
    List<StudentTestView> findStudentTestViewByKcidAndStid(Integer kcid,Integer stid);


    //教师评分
    @Transactional
    @Modifying
    @Query("update StudentTest u set u.score = ?1 where u.id = ?2 ")
    int updateScore(String score,Integer id);

}