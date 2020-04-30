package com.digimaple.eims.service.impl;


import com.digimaple.eims.model.CourseFile;
import com.digimaple.eims.model.view.CourseFileView;
import com.digimaple.eims.repository.CourseFileRepository;
import com.digimaple.eims.service.CourseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseFileService")
public class CourseFileServiceImpl implements CourseFileService {

    @Autowired(required = false)
    private CourseFileRepository courseFileRepository;

    //实现返回用户列表
    @Override
    public List<CourseFile> getCourseFileList(){
        return courseFileRepository.findAll();
    }

    //实现通过id查询用户，返回列表
    @Override
    public  CourseFile  findById(Integer id) {
        return courseFileRepository.findById(id);
    }
    //保存
    @Override
    public void save(CourseFile courseFile) {

        courseFileRepository.save(courseFile);
    }

    @Override
    public List<CourseFileView> findFileView(Integer kcid, Integer stid) {
           return   courseFileRepository.findFileView(kcid, stid);
    }

    //按课程id查询

    @Override
    public List<CourseFile> findByKcid(Integer kcid)
    {
        return courseFileRepository.findCourseFilesByKcid(kcid);
    }

    @Override
    public List<CourseFile> findByFilenameLike(Integer kcid,String filename) {
        return courseFileRepository.findByKcidAndAndFilenameLike(kcid ,filename);
    }
}
