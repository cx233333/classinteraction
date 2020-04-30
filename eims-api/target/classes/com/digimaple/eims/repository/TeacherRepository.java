package com.digimaple.eims.repository;

import com.digimaple.eims.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findById(Integer id);

    @Transactional
    void deleteById(Integer id);

    List<Teacher> findByJsno(Integer jsno);



    @Query(value = "select count (t.jsno) from Teacher t where t.jsno=?1 and t.password=?2")
    int findBySnoAndPassword(Integer jsno,String password);

    @Transactional
    @Query("select u from Teacher u where u.jsno = ?1 and u.password = ?2")
    Teacher  findloginList(Integer jsno,String password);

    @Transactional
    @Modifying
    @Query("update Teacher u set u.password = ?1 where u.password = ?2 and u.jsno = ?3")
    int updatePassword(String newpwd,String oldpwd,Integer jsno);

    //修改姓名
    @Transactional
    @Modifying
    @Query("update Teacher u set u.jsname = ?1 where u.id = ?2 ")
    int updateSname(String jsname,Integer id);

    //修改性别
    @Transactional
    @Modifying
    @Query("update Teacher u set u.gender = ?1 where u.id = ?2 ")
    int updateGender(String gender,Integer id);

    //修改电话
    @Transactional
    @Modifying
    @Query("update Teacher u set u.phone = ?1 where u.id = ?2 ")
    int updatePhone(String phone,Integer id);
}