package com.digimaple.eims.service.impl;

//import com.digimaple.eims.mapper.StudentMapper;
//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.KaoQin;
import com.digimaple.eims.model.view.KaoQinDateView;
import com.digimaple.eims.model.view.KaoQinView;
import com.digimaple.eims.repository.KaoQinRepository;

import com.digimaple.eims.service.KaoQinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("KaoQinService")
public class KaoQinServiceImpl implements KaoQinService {

    @Autowired(required = false)
    private KaoQinRepository kaoqinRepository;


    //实现返回用户列表
    @Override
    public List<KaoQin> getKaoQinList(){
        return kaoqinRepository.findAll();
    }



    //实现通过id查询用户，返回列表
    @Override
    public  KaoQin  findByKcid(Integer kcid) {
        return kaoqinRepository.findByKcid(kcid);
    }

    //实现通过课程id和学生id查询，返回列表
    @Override
    public  KaoQin  findByKcidAndStid(Integer kcid,Integer stid) {
        return kaoqinRepository.findByKcidAndStid(kcid,stid);
    }

    //实现组合查询返回列表
    @Override
    public List<KaoQinView> findKcidStid(Integer kcid, Integer stid) {

        return kaoqinRepository.findKcidStid(kcid,stid);
    }

    //保存
    @Override
    public void save(KaoQin kaoqin) {
        kaoqinRepository.save(kaoqin);
    }

    //编辑
    @Override
    public void edit(KaoQin kaoqin) {
        kaoqinRepository.save(kaoqin);
    }


    @Override
    public List<KaoQinDateView> countKcidAndKqdate(Integer kcid) {
        return kaoqinRepository.countKcidAndKqdate(kcid);
    }

    //教师按课程id和时间查询
    @Override
    public List<KaoQinView> findKaoQinByKcidAndKqtimeBetween(Integer kcid, Date starttime, Date endtime) {
        return kaoqinRepository.findKaoQinByKcidAndKqtimeBetween(kcid,starttime,endtime);
    }

    @Override
    public List<KaoQinView> findKaoQinByKcidAndKqdate(Integer kcid, Date kqdate) {
        return kaoqinRepository.findKaoQinByKcidAndKqdate(kcid,kqdate);
    }
}
