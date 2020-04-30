package com.digimaple.eims.repository;

import com.digimaple.eims.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findById(Integer id);

    @Transactional
    void deleteById(Integer id);

    List<Student> findStudentBySno(Integer sno);

    //未交名单
    @Transactional
    @Query(value = "select u.id  as id,u.sname as sname,u.sno as sno ,u.classname as classname," +
            "u.xyname as xyname ,u.gender as gender ,u.password as password,u.role as role ," +
            "u.phone as phone,u.status as status " +
            "from student u ,xuan_ke x where u.id not in (select t.stid as stid" +
            " from  student_test t,student s ,test e,course c" +
            "  where  e.kcid=?1  and t.testid = e.id  and e.kcid = c.id  and t.stid = s.id " +
            " and t.testid = ?2)" +
            " and x.kcid = ?1 and x.stid = u.id "

            ,nativeQuery = true
    )
    List<Student> findNottestStudent(Integer kcid,Integer testid);

    //缺勤名单
    @Transactional
    @Query(value = "select u.id  as id,u.sname as sname,u.sno as sno,u.classname as classname," +
            "u.xyname as xyname,u.gender as gender,u.password as password,u.role as role," +
            "u.phone as phone,u.status as status " +
            "from student u ,xuan_ke x where u.id not in (select t.stid as stid" +
            " from Kao_Qin t,Course c,Student s" +
            " where t.kcid=?1  and c.id = ?1  and t.stid = s.id and t.kcid = c.id and t.kqdate =?2)" +
            " and x.kcid = ?1 and x.stid = u.id "

            ,nativeQuery = true
    )
    List<Student> findNotKaoqinStudent(Integer kcid, Date kqdate );

    @Query(value = "select count (t.sno) from Student t where t.sno=?1 and t.password=?2")
    int findBySnoAndPassword(Integer sno,String password);
   //  int findBySnoAndPassword(String sno,String password);

    @Transactional
    @Query("select u from Student u where u.sno = ?1 and u.password = ?2")
     Student  findloginList(Integer sno,String password);

    @Transactional
    @Modifying
    @Query("update Student u set u.password = ?1 where u.password = ?2 and u.sno = ?3")
    int updatePassword(String newpwd,String oldpwd,Integer sno);

    //修改姓名
    @Transactional
    @Modifying
    @Query("update Student u set u.sname = ?1 where u.id = ?2 ")
    int updateSname(String sname,Integer id);

    //修改性别
    @Transactional
    @Modifying
    @Query("update Student u set u.gender = ?1 where u.id = ?2 ")
    int updateGender(String gender,Integer id);

    //修改电话
    @Transactional
    @Modifying
    @Query("update Student u set u.phone = ?1 where u.id = ?2 ")
    int updatePhone(String phone,Integer id);
}