package com.digimaple.eims.controller;

//import com.digimaple.eims.annotation.AbsoluteCancelToken;
//import com.digimaple.eims.annotation.PassToken;
//import com.digimaple.eims.annotation.StudentLoginToken;

import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PageDTO;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.StudentDTO;
import com.digimaple.eims.model.urpModel.Power;
import com.digimaple.eims.service.TokenService;
//import com.digimaple.eims.service.UrpService;
import com.digimaple.eims.service.StudentService;
import com.digimaple.eims.utils.FileUtils;
import com.digimaple.eims.utils.MD5Utils;
import com.digimaple.eims.utils.PageBean;
import com.digimaple.eims.utils.TokenUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作
@CrossOrigin
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TokenService tokenService;



//    @AbsoluteCancelToken
//    @PassToken
    @ApiOperation("登录接口")
    @PostMapping("/tologin")
    public ResponseEntity toLogin( @Validated @RequestBody PwdDTO pwdDTO , HttpServletResponse response  ){
        //密码加密
        System.out.println(pwdDTO.getStpwd().toString());
        pwdDTO.setStpwd(MD5Utils.MD5Encode(pwdDTO.getStpwd(),"utf8"));
        int student = studentService.tologin(pwdDTO);
        if(student == 0) {
            return ResponseEntity.ok("登录失败,账号或密码错误");
        }else {
            //查询记录并生成token
            Student studentToken = studentService.findByStudentname(pwdDTO.getSname(),pwdDTO.getStpwd());
            System.out.println("查询之后的Student记录"+studentToken.toString());
            String token = tokenService.studentGetToken(studentToken);
            //将token保存到cookie中
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.ok(token);
        }
    }

    //退出登录
    @ApiOperation("退出登录接口")
    @GetMapping("/quit/{name}")
    public ResponseEntity quit(@PathVariable String name){
        studentService.quit(name);
        return ResponseEntity.ok(null);
    }

    @ApiOperation("用户列表")
    //@StudentLoginToken
    @GetMapping("/listAll")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> student = studentService.selectAll();
        return ResponseEntity.ok(student);
    }

    @ApiOperation("按id查询用户")
    @GetMapping("/findById/{id}")
    public ResponseEntity<List<Student>> getById(@PathVariable long id){
        List<Student> Student = studentService.selectByPrimaryKey(id);
        return ResponseEntity.ok(Student);
    }

    //这里同一种GetMapping不能一样，不然会出现了两个名称一样的映射
    @ApiOperation("按name查询用户")
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Student>> getByName(@PathVariable String name){
        List<Student> Student = studentService.selectByName(name);
        return ResponseEntity.ok(Student);
    }


//    @ApiOperation("多框精确查询")
//    @PostMapping("/findByOne")
//    public ResponseEntity<List<Student>> getByOne(@RequestBody StudentDTO StudentDTO){
//        List<Student> Student = studentService.selectByOne(StudentDTO);
//        return ResponseEntity.ok(Student);
//    }

    //第二种接口：SQL形式实现
    @ApiOperation("分页查询用户")
    @PostMapping("/findBySql")
    public ResponseEntity<PageBean<Student>> getBySql(@RequestBody PageDTO pageDTO){
        if (pageDTO.getCurrPage() == null) {
            pageDTO.setCurrPage(1);
        }
        if(pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(5);
        }
        PageBean<Student> Student = studentService.queryStudentBySql(pageDTO);
        return ResponseEntity.ok(Student);
    }

    //第二种接口：SQL形式实现
    @ApiOperation("PageHelper分页查询用户")
    @PostMapping("/findByPH")
    public ResponseEntity<Page<Student>> getByPH(@RequestBody PageDTO pageDTO){
        //页数、数量
        PageHelper.startPage(pageDTO.getCurrPage(),pageDTO.getPageSize());
        Page<Student> StudentList = studentService.getStudentList(pageDTO.getCurrPage(),pageDTO.getPageSize());
        return ResponseEntity.ok(StudentList);
    }

    @ApiOperation("获取用户总数")
    //@AbsoluteCancelToken
    @GetMapping("/StudentCount")
    public ResponseEntity<Integer> getCount(){
        int count = studentService.selectStudentListCount();
        return ResponseEntity.ok(count);
    }

//    @ApiOperation("按部门名称查询用户")
//    @GetMapping("/findByDept/{deptname}")
//    public ResponseEntity<List<Student>> getByDept(@PathVariable String  deptname){
//        List<Student> Student = studentService.selectByDept(deptname);
//        return ResponseEntity.ok(Student);
//    }

    @ApiOperation("单框模糊查询(万能框)")
    @GetMapping("/findByAny")
    public ResponseEntity<List<Student>> getByAnyone(String anyone){
        List<Student> Student = studentService.selectByAnyone(anyone);
        return ResponseEntity.ok(Student);
    }

    @ApiOperation("新增用户")
    @PostMapping("add")
    public ResponseEntity<StudentDTO> add(@Validated  @RequestBody StudentDTO StudentDTO){
        //密码加密
        StudentDTO.setSt_pwd(MD5Utils.MD5Encode(StudentDTO.getSt_pwd(),"utf8"));
        //状态添加
        StudentDTO.setStatus(0);
        studentService.insert(StudentDTO);
        return ResponseEntity.ok(StudentDTO);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delById/{id}")
    public ResponseEntity<StudentDTO> del(@PathVariable long id){
        studentService.deleteByPrimaryKey(id);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation("批量删除用户")
//    @DeleteMapping("/delMany")
//    public ResponseEntity deleteSelect(@RequestBody String ids){//,@ RequestBody StudentDTO StudentDTO){
//        System.err.println(ids);
//        //String[] d=ids.split(",");//把数组里的值逗号隔开
//        System.out.println("批量删除成功");
//        studentService.deleteMany(ids);
//        return ResponseEntity.ok().build();
//    }

    @ApiOperation("更新用户")
    @PutMapping("/upd")
    public ResponseEntity update(@Validated @RequestBody StudentDTO StudentDTO){
        //密码加密
        StudentDTO.setSt_pwd(MD5Utils.MD5Encode(StudentDTO.getSt_pwd(),"utf8"));
        studentService.updateByPrimaryKeySelective(StudentDTO);
        return ResponseEntity.ok(StudentDTO);
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/updPwd")
    public ResponseEntity updateStudentPwd(@Validated @RequestBody PwdDTO pwdDTO){
        //密码加密
        pwdDTO.setStpwd(MD5Utils.MD5Encode(pwdDTO.getStpwd(),"utf8"));
        int YesOrNo =  studentService.updateByStudentName(pwdDTO);
        return ResponseEntity.ok(YesOrNo);
    }

    @ApiOperation("测试绝对取消验证访问")
    //@AbsoluteCancelToken    //和passToken一样
    @GetMapping("/AbsoluteCancelToken")
    public String AbsoluteCancelTokenTest() {

        return "绝对取消验证，正常访问...";
    }


    @ApiOperation("测试无需登录验证访问")
    //@PassToken //由于无需登录就可以进行的操作
    @GetMapping("/PassToken")
    public String PassTokenTest() {

        return "无需登录验证，正常访问...";
    }

    /***
     * 这个请求需要验证token才能访问
     */
    @ApiOperation("测试请求是否需要验证token才能访问")
    //@StudentLoginToken
    @GetMapping("/StudentLoginToken")
    public String StudentLoginTokenTest() {
        // 取出token中带的用户id 进行操作
    //    System.out.println(TokenUtil.getTokenStudentId() + "看看输出是什么");
        return "你已通过验证";
    }

//    //第一种接口：数组形式实现
//    @ApiOperation("分页查询用户：第一种")
//    @GetMapping("/findByArray/{currPage}/{pageSize}")
//    public ResponseEntity<List<Student>> getByArray(@PathVariable int currPage,@PathVariable int pageSize   ){
//        List<Student> Student = studentService.queryStudentByArray(currPage, pageSize);
//        return ResponseEntity.ok(Student);
//    }

//    @ApiOperation("获取用户男女占比")
//    @GetMapping("/StudentGenderCount")
//    public ResponseEntity<Double> getGenderCount(){
//        double gendercount = studentService.selectStudentGenderCount();
//        return ResponseEntity.ok(gendercount);
//    }

//    @Autowired
//    private UrpService urpService;

//    @ApiOperation("通过用户名称或账号动态获取菜单栏")
//    @GetMapping("/find/{name}")
//    public ResponseEntity<List<Power>> get(@PathVariable String name){
//        List<Power> powers = urpService.getUrpList(name);
//        return ResponseEntity.ok(powers);
//    }

//    @ApiOperation("第二次测试：通过用户名称或账号动态获取菜单栏")
//    //@StudentLoginToken
//    //@PassToken
//    @GetMapping("/finds/{name}")
//    public ResponseEntity<List<Power>> gets(@PathVariable String name){
//        List<Power> powers = urpService.testQueryMenuList(name);
//        return ResponseEntity.ok(powers);
//    }

    @ApiOperation("excel导出测试:导出全部用户")
    @GetMapping("/export")
    public ResponseEntity<List<Student>> exports(HttpServletResponse response){
        response.setContentType("application/vnd.ms-excel;charset=utf-8");// // 指定文件的保存类型。
        response.setCharacterEncoding("utf-8");
        List<Student> StudentList = studentService.selectAll();
        FileUtils.exportExcel(StudentList,"测试名", "什么名字",
                Student.class , "测试.xls" ,response );
        return ResponseEntity.ok().build();
    }
}
