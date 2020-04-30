package com.digimaple.eims.controller;

import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.updatePwdDTO;
import com.digimaple.eims.model.view.KaoQinView;
import com.digimaple.eims.service.StudentService;
import com.digimaple.eims.service.TokenService;
import com.digimaple.eims.utils.MD5Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作
@CrossOrigin
@RestController

@RequestMapping("student")
public class StudentController {
    @Autowired
    @Resource
    private StudentService studentService;
    @Autowired
    private TokenService tokenService;



//    @AbsoluteCancelToken
//    @PassToken
    @ApiOperation("登录接口")
    @PostMapping("/tologin")
    public ResponseEntity toLogin( @Validated @RequestBody PwdDTO pwdDTO, HttpServletResponse response  ){
        //密码加密
        System.out.println(pwdDTO.getPassword().toString());
        pwdDTO.setPassword(MD5Utils.MD5Encode(pwdDTO.getPassword(),"utf8"));
        int student = studentService.tologin(pwdDTO);
        if(student == 0) {
            return ResponseEntity.ok("登录失败,账号或密码错误");
        }else {
            Student students = studentService.findloginList(pwdDTO.getSno(),pwdDTO.getPassword());
           return ResponseEntity.ok(students);
        }
    }
//
//    //退出登录
//    @ApiOperation("退出登录接口")
//    @GetMapping("/quit/{name}")
//    public ResponseEntity quit(@PathVariable String name){
//        studentService.quit(name);
//        return ResponseEntity.ok(null);
//    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @ApiOperation("用户列表")
    @GetMapping("/list")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> student = studentService.getStudentList();
        return ResponseEntity.ok(student);
    }

    @ApiOperation("按id查询用户")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Student> getById(@PathVariable Integer id){
        Student student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @ApiOperation("按学号查询用户")
    @PostMapping("/findBySno")
    public ResponseEntity<List<Student>> getBySno(@RequestParam(value = "sno",required = true)  Integer sno){
        List<Student> student = studentService.findBySno(sno);
        return ResponseEntity.ok(student);
    }

    @ApiOperation("按kcid和testid查询未交名单")
    @GetMapping("/findNotTestStudent/{kcid}/{testid}")
    public ResponseEntity<List<Student>> getNottestStudent(@PathVariable Integer kcid, @PathVariable Integer testid){
        List<Student> student = studentService.findNottestStudent(kcid,testid);
        return ResponseEntity.ok(student);
    }


    @ApiOperation("按kcid和kqdate查询缺勤名单")
    @PostMapping("/findNotKaoqinStudent")
    public ResponseEntity<List<Student>> getNotKaoqinStudent(@RequestParam(value = "kcid",required = true)  Integer kcid,
    @RequestParam(value = "kqdate",required = true)  Date kqdate){
        
        List<Student> student = studentService.findNotKaoqinStudent(kcid, kqdate);
        return ResponseEntity.ok(student);
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public String add( @RequestBody Student student) {
        student.setPassword(MD5Utils.MD5Encode(student.getPassword(),"utf8"));
        studentService.save(student);

        return "redirect:/list";
    }



    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public String delete(Integer id) {
        studentService.delete(id);
        return "redirect:/list";
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/updPwd")
    public ResponseEntity updateUserPwd(@Validated @RequestBody updatePwdDTO updatepwdDTO){
        //密码加密
        updatepwdDTO.setNewpwd(MD5Utils.MD5Encode(updatepwdDTO.getNewpwd(),"utf8"));
        updatepwdDTO.setOldpwd(MD5Utils.MD5Encode(updatepwdDTO.getOldpwd(),"utf8"));
        int YesOrNo = studentService.updatePassword(updatepwdDTO.getNewpwd(),updatepwdDTO.getOldpwd(),updatepwdDTO.getSno());
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("修改姓名")
    @PostMapping("/updatename")
    public ResponseEntity updatename(@RequestParam(value = "sname",required = true) String sname,
                                     @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = studentService.updateSname(sname,id);
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("修改性别")
    @PostMapping("/updategender")
    public ResponseEntity updategender(@RequestParam(value = "gender",required = true) String gender,
                                     @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = studentService.updateGender(gender,id);
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("修改电话")
    @PostMapping("/updatephone")
    public ResponseEntity updatephone(@RequestParam(value = "phone",required = true) String phone,
                                     @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = studentService.updatePhone(phone,id);
        return ResponseEntity.ok(YesOrNo);
    }




}
