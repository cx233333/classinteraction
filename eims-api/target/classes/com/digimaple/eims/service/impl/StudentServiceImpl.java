package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.dto.PageDTO;
import com.digimaple.eims.model.dto.PwdDTO;
import com.digimaple.eims.model.dto.StudentDTO;
import com.digimaple.eims.repository.StudentRepository;
import com.digimaple.eims.service.StudentService;
import com.digimaple.eims.utils.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentRepository studentRepository;

    //实现返回用户列表
    @Override
    public List<Student> getStudentList(){
        return studentRepository.findAll();
    }

    //实现通过id查询用户，返回列表
    @Override
    public  Student  findById(Integer id) {
        return studentRepository.findById(id);
    }

    //新增
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    //按学号查询
    @Override
    public List<Student> findBySno(Integer sno) {
        return studentRepository.findStudentBySno(sno);
    }

    @Override
    public List<Student> findNottestStudent(Integer kcid, Integer testid) {
        return studentRepository.findNottestStudent(kcid, testid);
    }

    //缺勤

    @Override
    public List<Student> findNotKaoqinStudent(Integer kcid, Date kqdate) {
        return studentRepository.findNotKaoqinStudent(kcid, kqdate);
    }

    //编辑
    @Override
    public void edit(Student student) {
        studentRepository.save(student);
    }

    // 删除
    @Override
    public void delete(Integer stid) {
        studentRepository.deleteById(stid);
    }

     //修改名字

    @Override
    public int updateSname(String sname, Integer id) {
          return   studentRepository.updateSname(sname, id);
    }

    //修改性别
    @Override
    public int updateGender(String gender, Integer id) {
        return studentRepository.updateGender(gender,id);
    }

    //修改电话
    @Override
    public int updatePhone(String phone, Integer id) {
        return studentRepository.updatePhone(phone, id);
    }

    /*
     * 登录
     */
    @Override
    public int tologin(PwdDTO pwdDTO) {
        int flag = 0;
        if ( studentRepository.findBySnoAndPassword(pwdDTO.getSno(),pwdDTO.getPassword()) == 1){

            flag = 1;
            return flag;
        }else return flag;
    }

    @Override
    public Student findloginList(Integer sno,String password) {

        return studentRepository.findloginList(sno,password);
    }

    @Override
    public int updatePassword(String newpwd,String oldpwd,Integer sno) {
        return studentRepository.updatePassword(newpwd,oldpwd,sno);

    }

}
