package com.digimaple.eims.controller;

import com.digimaple.eims.model.Course;
import com.digimaple.eims.model.view.CourseView;
import com.digimaple.eims.model.view.StudentCourseView;
import com.digimaple.eims.repository.TeacherRepository;
import com.digimaple.eims.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作
@CrossOrigin
@RestController

@RequestMapping("Course")
public class CourseController {
    @Autowired
    @Resource
    private CourseService courseService;
    @Autowired
    @Resource
    private TeacherRepository teacherRepository;




    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    public ResponseEntity<List<Course>> getAll(){
        List<Course> course = courseService.getCourseList();
        return ResponseEntity.ok(course);
    }

    //课程view
    @ApiOperation("课程view")
    @GetMapping("/listView")
    public ResponseEntity<List<CourseView>> getListView(){
        List<CourseView> courseViews = courseService.findListView();
        return ResponseEntity.ok(courseViews);
    }


    @ApiOperation("按id查询用户")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Course> getById(@PathVariable Integer id){
        Course course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @ApiOperation("按jsid查询")
    @GetMapping("/findByJsid/{jsid}")
    public ResponseEntity<List<Course>> getByJsid(@PathVariable Integer jsid){
        List<Course> course = courseService.findByJsid(jsid);
        return ResponseEntity.ok(course);
    }

    //学生查询自己的课程
    @ApiOperation("学生查询自己的课程")
    @GetMapping("/findStudentCourseView/{stid}")
    public ResponseEntity<List<StudentCourseView>> getByKcidAndStid( @PathVariable(name = "stid") Integer stid){
        List<StudentCourseView> studentCourseViews = courseService.findStudentCourse(stid);
        return ResponseEntity.ok(studentCourseViews);
    }

    @ApiOperation("课程名称查询课程")
    @GetMapping("/findByKcname/{kcname}")
    public ResponseEntity<List<Course>> getByKcname( @PathVariable(name = "kcname") String kcname){
        List<Course> course = courseService.findByKcname(kcname);
        return ResponseEntity.ok(course);
    }


    @ApiOperation("新增课程")
    @PostMapping("/add")
    public String add( @RequestBody Course course) {
        Integer jsid = teacherRepository.findByJsno(course.getJsid()).get(0).getId();
        course.setJsid(jsid);
        courseService.save(course);

        return "redirect:/list";
    }


    @ApiOperation("保存编辑信息")
    @PostMapping("/edit")
    public String edit(Course course) {
        courseService.edit(course);
        return "redirect:/list";
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public String delete(Integer id) {
        courseService.delete(id);
        return "redirect:/list";
    }



}
