package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PageDTO;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.StudentDTO;
import com.digimaple.eims.utils.PageBean;
import com.github.pagehelper.Page;

import java.util.List;

public interface StudentService {

    //用户列表
    public List<Student> selectAll();

    //id 查询
    public List<Student> selectByPrimaryKey(long id);

    //name 查询
    public  List<Student> selectByName(String sname);

    //用于token
    public Student findByStudentname(String sname, String st_pwd);

    //用于token
    public Student findStudentById(String sNo);

    //通过部门名称查询用户
   // public List<Student> selectByDept(String deptname);

    //多框精确查询
  //  public List<Student> selectByOne(StudentDTO StudentDTO);

    //单框模糊查询
    public List<Student> selectByAnyone(String anyone);

    //用户添加
    public int  insert(StudentDTO StudentDTO);

    //用户删除
    public int deleteByPrimaryKey(long id);

    //用户批量删除
   // public Integer deleteMany(String ids);

    //用户修改
    public int updateByPrimaryKeySelective(StudentDTO StudentDTO);

    //返回用户数
    public int selectStudentListCount();

    //返回用户男女占比
    public double selectStudentGenderCount();

    //返回数组分页方法：第一种
    public List<Student> queryStudentByArray(int currPage, int pageSize);

    //第二种分页，在查询时分页
    public PageBean<Student> queryStudentBySql(PageDTO pageDTO);

    //PageHelper分页
    public Page<Student> getStudentList(Integer pageNum, Integer pageSize);

    //登录验证
    public int tologin(PwdDTO pwdDTO);

    //退出登录
    public void quit(String name);

    //通过用户名称或编号或账号修改密码
    public int updateByStudentName(PwdDTO pwdDTO);

    //通过部门名称查询部门id
  //  public Dept findDId(String deptname);

}
