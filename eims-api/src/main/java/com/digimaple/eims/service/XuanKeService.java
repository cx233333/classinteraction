package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.XuanKe;
import com.digimaple.eims.model.view.XuanKeView;


import java.util.List;

public interface XuanKeService {

    //用户列表
    public List<XuanKe> getXuanKeList();

    public List<XuanKeView> findList();

    public  List<XuanKeView> findByKcname(String kcname);

    public void save(XuanKe xuanKe);

    public void delete(Integer id);


}
