package com.digimaple.eims.repository;

import com.digimaple.eims.model.CourseFile;
import com.digimaple.eims.model.view.CourseFileView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseFileRepository extends JpaRepository<CourseFile, Long> {

    CourseFile findById(Integer id);

    void deleteById(Integer id);

    List<CourseFile> findCourseFilesByKcid(Integer kcid);

   List<CourseFile> findByKcidAndAndFilenameLike(Integer kcid ,String filename);

    @Transactional
    @Query(value = "select f.id as id,c.id as kcid,s.id as stid,c.kcname as kcname," +
            "f.filename as filename,f.filepath as filepath from Course c,Course_File f,Student s  " +
            "where if(?1 !='',f.kcid=?1,1=1) and if(?2 !='',s.id=?2,1=1) and " +
            " if(?1 !='',c.id=?1,1=1)"
            ,nativeQuery = true
    )
    List<CourseFileView> findFileView(Integer kcid, Integer stid);
}