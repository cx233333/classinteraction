package com.digimaple.eims.service;

import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.StudentTest;
import com.digimaple.eims.model.view.StudentTestView;

import java.util.List;

public interface StudentTestService {

    //用户列表
    public List<StudentTest> getStudentTestList();



    public void save(StudentTest studentTest);

   //教师按kcid查询学生测试view
    public  List<StudentTestView> findStudentTestViewByKcidAndTestid(Integer kcid,Integer testid);

    //学生查询学生测试view
    public  List<StudentTestView> findStudentTestViewByKcidAndStid(Integer kcid,Integer stid);


    public int updateScore(String score,Integer id);



}
