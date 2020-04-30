package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;

import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.StudentTest;
import com.digimaple.eims.model.view.StudentTestView;
import com.digimaple.eims.repository.StudentTestRepository;
import com.digimaple.eims.service.StudentTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentTestService")
public class StudentTestServiceImpl implements StudentTestService {

    @Autowired(required = false)
    private StudentTestRepository studentTestRepository;

    //实现返回用户列表

    @Override
    public List<StudentTest> getStudentTestList() {
        return studentTestRepository.findAll();
    }

    @Override
    public void save(StudentTest studentTest) {
        studentTestRepository.save(studentTest);
    }

    @Override
    public List<StudentTestView> findStudentTestViewByKcidAndTestid(Integer kcid,Integer testid) {
        return studentTestRepository.findStudentTestViewByKcidAndTestid(kcid,testid);
    }


    @Override
    public List<StudentTestView> findStudentTestViewByKcidAndStid(Integer kcid,Integer stid) {
        return studentTestRepository.findStudentTestViewByKcidAndStid(kcid,stid);
    }

    @Override
    public int updateScore(String score, Integer id) {
        return studentTestRepository.updateScore(score,id);
    }


}
