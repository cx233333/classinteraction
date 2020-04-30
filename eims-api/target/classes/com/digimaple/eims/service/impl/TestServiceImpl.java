package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Test;
import com.digimaple.eims.repository.TestRepository;
import com.digimaple.eims.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TestService")
public class TestServiceImpl implements TestService {

    @Autowired(required = false)
    private TestRepository testRepository;

    //实现返回用户列表
    @Override
    public List<Test> getTestList() {
        return testRepository.findAll();
    }

//    //实现通过id查询用户，返回列表
//    @Override
//    public Test findById(Integer id) {
//        return testRepository.findById(id);
//    }

    //保存
    @Override
    public void save(Test test) {
        testRepository.save(test);
    }

    //编辑
    @Override
    public void edit(Test test) {
        testRepository.save(test);
    }

    @Override
    public List<Test> findByTestname(String testname) {
        return  testRepository.findByTestname(testname);
    }

    @Override
    public List<Test> findByKcid(Integer kcid) {
        return testRepository.findByKcid(kcid);
    }
}
