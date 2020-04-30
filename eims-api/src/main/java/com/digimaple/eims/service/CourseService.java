package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Course;
import com.digimaple.eims.model.view.CourseView;
import com.digimaple.eims.model.view.StudentCourseView;

import java.util.List;

public interface CourseService {

    //用户列表
    public List<Course> getCourseList();

    //id 查询
    public Course findById(Integer id);

    public void save(Course course);

    public void edit(Course Course);

    public void delete(Integer id);

    //jsid查询
    public  List<Course> findByJsid(Integer jsid);

    //学生查询自己的课程
    public List<StudentCourseView> findStudentCourse(Integer stid);

    //课程名称查课程id
    public List<Course> findByKcname(String kcname);

    //课程view
    public  List<CourseView> findListView();
}
