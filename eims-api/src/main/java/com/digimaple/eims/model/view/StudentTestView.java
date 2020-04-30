package com.digimaple.eims.model.view;


import java.util.Date;

public interface StudentTestView {

    int getId();
    int getKcid();

    int getStid();
    int getTestid();
    int getSno();

    String getScore();
    String getAnswer();
    Date getTesttime();
    String getSname();
    String getTestname();
    String getKcname();


}
