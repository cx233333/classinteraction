package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Teacher;
import com.digimaple.eims.model.dto.TeacherPwdDTO;

import java.util.List;

public interface TeacherService {

    //用户列表
    public List<Teacher> getTeacherList();

    //id 查询
    public Teacher findById(Integer id);



    public List<Teacher> findByJsno(Integer jsno);

    public void save(Teacher teacher);

    public void edit(Teacher teacher);

    public void delete(Integer id);

    // 登录验证
    public int tologin(TeacherPwdDTO teacherPwdDTO);

    //   用于token
    public Teacher findloginList(Integer jsno, String password);

    //通过用户名称或编号或账号修改密码
    public int updatePassword(String newpwd,String oldpwd,Integer jsno);

    //修改姓名
    public int updateJsname(String jsname,Integer id);

    //修改性别
    public int updateGender(String gender,Integer id);

    //修改电话
    public int updatePhone(String phone,Integer id);





    //登录验证
  //  public int tologin(PwdDTO pwdDTO);

    //退出登录
  //  public void quit(String name);

    //通过用户名称或编号或账号修改密码
 //   public int updateByTeacherName(PwdDTO pwdDTO);

    //通过部门名称查询部门id
  //  public Dept findDId(String deptname);

}
