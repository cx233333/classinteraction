package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.TeacherMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.Teacher;
import com.digimaple.eims.model.dto.TeacherPwdDTO;
import com.digimaple.eims.repository.TeacherRepository;
import com.digimaple.eims.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired(required = false)
    private TeacherRepository teacherRepository;




    @Override
    public List<Teacher> findByJsno(Integer jsno) {
        return teacherRepository.findByJsno(jsno);
    }

    //实现返回用户列表
    @Override
    public List<Teacher> getTeacherList(){
        return teacherRepository.findAll();
    }

    //实现通过id查询用户，返回列表
    @Override
    public  Teacher  findById(Integer id) {
        return teacherRepository.findById(id);
    }
    //保存
    @Override
    public void save(Teacher Teacher) {
        teacherRepository.save(Teacher);
    }

    //编辑
    @Override
    public void edit(Teacher Teacher) {
        teacherRepository.save(Teacher);
    }

    // 删除
    @Override
    public void delete(Integer stid) {
        teacherRepository.deleteById(stid);
    }

    @Override
    public int tologin(TeacherPwdDTO teacherPwdDTO) {
        int flag = 0;
        if ( teacherRepository.findBySnoAndPassword(teacherPwdDTO.getJsno(),teacherPwdDTO.getPassword()) == 1){

            flag = 1;
            //  studentRepository.updateStudentStatus(pwdDTO.getSname(),flag);  //更新登录状态
            return flag;
        }else return flag;
    }

    @Override
    public Teacher findloginList(Integer jsno, String password) {

        return teacherRepository.findloginList(jsno,password);
    }

    @Override
    public int updatePassword(String newpwd, String oldpwd, Integer jsno) {
        return teacherRepository.updatePassword(newpwd,oldpwd,jsno);
    }

    @Override
    public int updateJsname(String jsname, Integer id) {
        return teacherRepository.updateSname(jsname, id);
    }

    @Override
    public int updateGender(String gender, Integer id) {
        return teacherRepository.updateGender(gender, id);
    }

    @Override
    public int updatePhone(String phone, Integer id) {
        return teacherRepository.updatePhone(phone, id);
    }

//    /*
//     * 退出登录
//     */
//    public void quit(String name){
//        final int flag = 0;
//     //   TeacherMapper.updateTeacherStatus(name,flag);
//    }

}
