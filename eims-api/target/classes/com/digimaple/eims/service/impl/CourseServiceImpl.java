package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Course;
import com.digimaple.eims.model.view.CourseView;
import com.digimaple.eims.model.view.StudentCourseView;
import com.digimaple.eims.repository.CourseRepository;
import com.digimaple.eims.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

    @Autowired(required = false)
    private CourseRepository courseRepository;

    //实现返回用户列表
    @Override
    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }

    //实现通过id查询用户，返回列表
    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id);
    }

    //保存
    @Override
    public void save(Course Course) {
        courseRepository.save(Course);
    }

    //编辑
    @Override
    public void edit(Course Course) {
        courseRepository.save(Course);
    }

    // 删除
    @Override
    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }


    //教师id查询

    @Override
    public List<Course> findByJsid(Integer jsid) {
        return courseRepository.findCoursesByJsid(jsid);
    }

    //学生查询自己的课程
    @Override
    public List<StudentCourseView> findStudentCourse(Integer stid) {
        return courseRepository.findStudentCourse(stid);
    }

    @Override
    public List<CourseView> findListView() {
        return courseRepository.findListView();
    }

    @Override
    public List<Course> findByKcname(String kcname) {
        return courseRepository.findByKcname(kcname);
    }
}
