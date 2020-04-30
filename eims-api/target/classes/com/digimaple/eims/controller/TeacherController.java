package com.digimaple.eims.controller;

import com.digimaple.eims.model.Teacher;
import com.digimaple.eims.model.dto.TeacherPwdDTO;
import com.digimaple.eims.model.dto.updateTeacherPwdDTO;
import com.digimaple.eims.service.TeacherService;
import com.digimaple.eims.utils.MD5Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作

@CrossOrigin
@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;
//    @Autowired
//    private TokenService tokenService;

    @ApiOperation("登录接口")
    @PostMapping("/tologin")
    public ResponseEntity toLogin(@Validated @RequestBody TeacherPwdDTO teacherPwdDTO, HttpServletResponse response  ){
        //密码加密
        System.out.println(teacherPwdDTO.getPassword().toString());
        teacherPwdDTO.setPassword(MD5Utils.MD5Encode(teacherPwdDTO.getPassword(),"utf8"));
        int teacher = teacherService.tologin(teacherPwdDTO);
        if(teacher == 0) {
            return ResponseEntity.ok("登录失败,账号或密码错误");
        }else {
            //查询记录并生成token
           Teacher teacherToken = teacherService.findloginList(teacherPwdDTO.getJsno(),teacherPwdDTO.getPassword());
            //生成loginlist
            //    Student studentlist = studentService.findloginList(pwdDTO.getSno(),pwdDTO.getPassword());

            System.out.println("查询之后的Student记录"+teacherToken.toString());
            //  String token = tokenService.getToken(studentToken);
            //将token保存到cookie中
//            Cookie cookie = new Cookie("token", token);
//            cookie.setPath("/");
//            response.addCookie(cookie);
            return ResponseEntity.ok(teacherToken);
            //  return ResponseEntity.ok(cookie);
        }

    }


    @ApiOperation("教师用户列表")
    //@TeacherLoginToken
    @GetMapping("/list")
    public ResponseEntity<List<Teacher>> getAll(){
        List<Teacher> teacher = teacherService.getTeacherList();
        return ResponseEntity.ok(teacher);
    }
    @ApiOperation("按工号查询用户")
    @PostMapping("/findByJsno")
    public ResponseEntity<List<Teacher>> getByJsno(@RequestParam(value = "jsno",required = true)  Integer jsno){
        List<Teacher> teachers = teacherService.findByJsno(jsno);
        return ResponseEntity.ok(teachers);
    }

    @ApiOperation("按id查询用户")
    @GetMapping("/teacherfindById/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Integer id){
        Teacher teacher = teacherService.findById(id);
        return ResponseEntity.ok(teacher);
    }



    @ApiOperation("新增用户")
    @PostMapping("/teacheradd")
    public String add( @RequestBody Teacher teacher) {
        teacher.setPassword(MD5Utils.MD5Encode(teacher.getPassword(),"utf8"));
        teacherService.save(teacher);
        return "1";
    }



    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public String delete(Integer id) {
        teacherService.delete(id);
        return "redirect:/teacherlist";
    }

    @ApiOperation("修改教师用户密码")
    @PutMapping("/updPwd")
    public ResponseEntity updateUserPwd(@Validated @RequestBody updateTeacherPwdDTO updateTeacherPwdDTO){
        //密码加密
        updateTeacherPwdDTO.setNewpwd(MD5Utils.MD5Encode(updateTeacherPwdDTO.getNewpwd(),"utf8"));
        updateTeacherPwdDTO.setOldpwd(MD5Utils.MD5Encode(updateTeacherPwdDTO.getOldpwd(),"utf8"));
        int YesOrNo = teacherService.updatePassword(updateTeacherPwdDTO.getNewpwd(),updateTeacherPwdDTO.getOldpwd(),updateTeacherPwdDTO.getJsno());
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("修改姓名")
    @PostMapping("/updatejsname")
    public ResponseEntity updatename(@RequestParam(value = "jsname",required = true) String jsname,
                                     @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = teacherService.updateJsname(jsname,id);
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("修改性别")
    @PostMapping("/updategender")
    public ResponseEntity updategender(@RequestParam(value = "gender",required = true) String gender,
                                       @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = teacherService.updateGender(gender,id);
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("修改电话")
    @PostMapping("/updatephone")
    public ResponseEntity updatephone(@RequestParam(value = "phone",required = true) String phone,
                                      @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = teacherService.updatePhone(phone,id);
        return ResponseEntity.ok(YesOrNo);
    }





}
