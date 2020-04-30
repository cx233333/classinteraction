package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Course;
import com.digimaple.eims.model.XuanKe;
import com.digimaple.eims.model.view.StudentCourseView;
import com.digimaple.eims.model.view.XuanKeView;
import com.digimaple.eims.repository.CourseRepository;
import com.digimaple.eims.repository.XuanKeRepository;
import com.digimaple.eims.service.CourseService;
import com.digimaple.eims.service.XuanKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("XuanKeService")
public class XuanKeServiceImpl implements XuanKeService {

    @Autowired(required = false)
    private XuanKeRepository xuanKeRepository;

    //实现返回用户列表
    @Override
    public List<XuanKe> getXuanKeList() {
        return xuanKeRepository.findAll();
    }

    @Override
    public List<XuanKeView> findList() {
        return xuanKeRepository.findList();
    }

    @Override
    public void delete(Integer id) {
        xuanKeRepository.deleteById(id);
    }

    @Override
    public List<XuanKeView> findByKcname(String kcname) {
        return xuanKeRepository.findByKcnameLike(kcname);
    }

    //保存
    @Override
    public void save(XuanKe xuanKe) {
        xuanKeRepository.save(xuanKe);
    }


}
