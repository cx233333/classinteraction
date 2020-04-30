package com.digimaple.eims.repository;
import com.digimaple.eims.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {

  List<Test> findByKcid(Integer kcid);

    List<Test> findByTestname(String testname);


}