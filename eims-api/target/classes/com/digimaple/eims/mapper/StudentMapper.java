package com.digimaple.eims.mapper;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.StudentDTO;
import com.digimaple.eims.model.urpModel.Power;
import com.github.pagehelper.Page;

import java.util.List;

public interface StudentMapper {


    //列表，并兼职第一种分页，查询后数组分页
    public List<Student> selectAll();

    //通过用户id 查询，返回列表
    public  List<Student> selectByPrimaryKey(long st_id);



    //name 查询
    public List<Student> selectByName(String sname);

    //通过用户id查询，返回数组
    public  Student findStudentById(String password);
    //name 查询，返回object形式
    public Student findByStudentname(String sname, String st_pwd);


    //单框模糊查询
    public List<Student> selectByAnyone(String anyone);

    //添加
    public int  insert(StudentDTO StudentDTO);

    //删除
    public int deleteByPrimaryKey(long st_id);


    //修改
    public int updateByPrimaryKeySelective(StudentDTO StudentDTO);

    //返回用户数
    public int selectStudentListCount();

    //返回男女占比
    public double selectStudentGenderCount();

    //第二种分页，在查询时分页
    public List<Student> queryStudentBySql(Integer currPage, Integer pageSize);

    //PageHelper引用分页
    Page<Student> getStudentList();

    //设置用户登录状态
    public int updateStudentStatus(String sname, int status);

    //登录验证
    public int tologin(PwdDTO pwdDTO);


    //通过用户名称或编号或账号修改密码
    public int updateByStudentName(PwdDTO pwdDTO);

    //通过部门名称查询部门id
  //  public Dept findDId(String deptname);



    //第二次测试,通过用户名称或账号动态获取菜单栏
    public List<Power> queryMenuList(String name);

}
