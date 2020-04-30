package com.digimaple.eims.service;

import com.digimaple.eims.model.Test;


import java.util.List;

public interface TestService {

    //用户列表
    public List<Test> getTestList();

    //kcid 查询
    public List<Test> findByKcid(Integer kcid);

    public List<Test> findByTestname(String testname);

    public void save(Test Test);

    public void edit(Test Test);

 //   public void delete(Integer id);


}
