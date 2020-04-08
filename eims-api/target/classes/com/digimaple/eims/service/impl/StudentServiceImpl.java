package com.digimaple.eims.service.impl;

import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PageDTO;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.StudentDTO;
import com.digimaple.eims.service.StudentService;
import com.digimaple.eims.utils.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;

    //实现返回用户列表
    @Override
    public List<Student> selectAll(){
        return studentMapper.selectAll();
    }

    //实现通过id查询用户，返回列表
    @Override
    public  List<Student> selectByPrimaryKey(long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    //实现通过name查询用户，返回列表
    @Override
    public List<Student> selectByName(String name) {
        return studentMapper.selectByName(name);
    }

    //实现通过部门查询用户，返回列表
//    @Override
//    public List<Student> selectByDept(String deptname) {
//        return studentMapper.selectByDept(deptname);
//    }

    //实现精确查询用户
//    @Override
//    public List<Student> selectByOne(StudentDTO StudentDTO) {
//        return studentMapper.selectByOne(StudentDTO);
//    }

    //实现模糊查询用户
    @Override
    public List<Student> selectByAnyone(String anyone) {
        return studentMapper.selectByAnyone(anyone);
    }

    //实现插入用户
    @Override
    public int insert(StudentDTO StudentDTO) {
//        if("".equals(StudentDTO.getName())){
//            throw new NullPointerException("name 不能为空");
//        }

        return studentMapper.insert(StudentDTO);
    }

    //实现通过id删除用户
    @Override
    public int deleteByPrimaryKey(long id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    //实现通过id批量删除用户
//    @Override
//    public Integer deleteMany(String ids) {
//        return StudentMapper.deleteMany(ids.split(","));
//    }

    //实现修改用户
    @Override
    public int updateByPrimaryKeySelective(StudentDTO StudentDTO) {

//        if(studentMapper.findDId(StudentDTO.getDeptId()).getId() != 1){
//            throw new RuntimeException("部门不存在");
//        }
//        StudentDTO.setDeptId(StudentMapper.findDId(StudentDTO.getDeptId()).getDeptname());

            return studentMapper.updateByPrimaryKeySelective(StudentDTO);
    }

    //实现用户数量统计
    @Override
    public int selectStudentListCount() {
        return studentMapper.selectStudentListCount();
    }

    //实现用户男女比列统计
    @Override
    public double selectStudentGenderCount() {
        return studentMapper.selectStudentGenderCount();
    }

    /*
     *实现分页方法：第一种
     *currPage参数表示显示第几页的数据，
     *pageSize表示每页显示的数据条数。
     *
     * 缺点：数据库查询并返回所有的数据，而我们需要的只是极少数符合要求的数据。
     * 当数据量少时，还可以接受。
     * 当数据库数据量过大时，每次查询对数据库和程序的性能都会产生极大的影响。
     */
    @Override
    public List<Student> queryStudentByArray(int currPage, int pageSize) {
        List<Student> Students = studentMapper.selectAll();
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //从第几条数据结束
        int lastIndex = currPage * pageSize;
        //通过subList方法，获取到两个索引间的所有数据。
        return Students.subList(firstIndex,lastIndex);
    }

    /*
     *实现分页方法：第二种
     */
    @Override
    public PageBean<Student> queryStudentBySql(PageDTO pageDTO) {

        //查询数据总数
        int totalCount = studentMapper.selectStudentListCount();
        PageBean pageBean = new PageBean(pageDTO.getCurrPage(),pageDTO.getPageSize(),totalCount);
        Integer curr = (pageDTO.getCurrPage()-1) * pageDTO.getPageSize();
        Integer page = pageDTO.getPageSize();
        List<Student> list = studentMapper.queryStudentBySql(curr,page);
        pageBean.setList(list);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    /*
     * PageHelper分页
     */
    @Override
    public Page<Student> getStudentList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Student> StudentList = studentMapper.getStudentList();
        return StudentList;
    }

    /*
     * 登录
     */
    @Override
    public int tologin(PwdDTO pwdDTO) {
        int flag = 0;
        if ( studentMapper.tologin(pwdDTO) == 1){

            flag = 1;
           studentMapper.updateStudentStatus(pwdDTO.getSname(),flag);  //更新登录状态
            return flag;
        }else return flag;
    }

    /*
     * 退出登录
     */
    public void quit(String name){
        final int flag = 0;
     //   studentMapper.updateStudentStatus(name,flag);
    }

    //实现修改用户密码
    @Override
    public int updateByStudentName(PwdDTO pwdDTO) {
        return studentMapper.updateByStudentName(pwdDTO);
    }

   // @Override
//    public Dept findDId(String deptname) {
//        return StudentMapper.findDId(deptname);
//    }

    //实现查询用户，用于token
    @Override
    public Student findByStudentname(String sname,String st_pwd) {
        return studentMapper.findByStudentname(sname,st_pwd);
    }

    //实现查询用户，用于token
    public Student findStudentById(String st_id) {
        return studentMapper.findStudentById(st_id);
    }
}
