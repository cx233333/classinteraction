package com.digimaple.eims.service;

//import com.digimaple.eims.model.Dept;
import com.digimaple.eims.model.CourseFile;
import com.digimaple.eims.model.view.CourseFileView;

import java.util.List;

public interface CourseFileService {

    //用户列表
    public List<CourseFile> getCourseFileList();

    //id 查询
    public CourseFile findById(Integer id);

    public void save(CourseFile courseFile);

    //文件列表显示
   public List<CourseFileView> findFileView(Integer kcid, Integer stid);
//
    public  List<CourseFile> findByKcid(Integer kcid);

    public  List<CourseFile> findByFilenameLike(Integer kcid ,String filename);

}
