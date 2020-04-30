package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.KaoQin;
import com.digimaple.eims.model.view.KaoQinDateView;
import com.digimaple.eims.model.view.KaoQinView;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.Date;
import java.util.List;

public interface KaoQinService {

    //用户列表
    public List<KaoQin> getKaoQinList();



    //课程id查询
    public KaoQin findByKcid(Integer kcid);

    //课程id和学生id查询
    public KaoQin findByKcidAndStid(Integer kcid,Integer stid);

    //组合查询
    public List<KaoQinView> findKcidStid(Integer kcid, Integer stid);

    public void save(KaoQin kaoqin);

    public void edit(KaoQin kaoqin);


    public  List<KaoQinDateView> countKcidAndKqdate(Integer kcid);


    //教师按课程id和时间段查询
    public  List<KaoQinView> findKaoQinByKcidAndKqtimeBetween(Integer kcid, Date starttime , Date endtime);

    //教师按课程id和时间查询
    public  List<KaoQinView> findKaoQinByKcidAndKqdate(Integer kcid, Date kqdate);

}
