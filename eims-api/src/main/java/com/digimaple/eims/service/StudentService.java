package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PageDTO;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.StudentDTO;
import com.digimaple.eims.utils.PageBean;
import com.github.pagehelper.Page;

import java.util.Date;
import java.util.List;

public interface StudentService {

    //用户列表
    public List<Student> getStudentList();

    //id 查询
    public Student findById(Integer id);

    public void save(Student student);

    public void edit(Student student);

    public void delete(Integer id);

    //学号查询
    public  List<Student> findBySno(Integer sno);

 //   用于token
    public Student findloginList(Integer sno, String stpwd);

    //通过账号修改密码
    public int updatePassword(String newpwd,String oldpwd,Integer sno);

    //修改姓名
     public int updateSname(String sname,Integer id);

    //修改性别
    public int updateGender(String gender,Integer id);

    //修改电话
    public int updatePhone(String phone,Integer id);



   // 登录验证
    public int tologin(PwdDTO pwdDTO);

    //查询未交名单
    public  List<Student> findNottestStudent(Integer kcid, Integer testid);

    //缺勤名单
    public  List<Student> findNotKaoqinStudent(Integer kcid, Date kqdate);
}
