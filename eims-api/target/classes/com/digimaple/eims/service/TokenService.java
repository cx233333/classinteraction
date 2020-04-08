package com.digimaple.eims.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

//import com.digimaple.eims.model.User;
import com.digimaple.eims.model.Student;
import org.springframework.stereotype.Service;

/***
 * token 下发啊啦啦啦
 * @Title: TokenService.java
 */
@Service("TokenService")
public class TokenService {
//
//    public String getToken(User user) {
//        Date start = new Date();
//        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
//        Date end = new Date(currentTime);
//        String token = "";
//        System.out.println("生成token中的user记录："+user.toString());
//        token = JWT.create().withAudience(String.valueOf(user.getId())).withIssuedAt(start).withExpiresAt(end)
//                .sign(Algorithm.HMAC256(user.getPassword()));
//        return token;
//    }
    public String studentGetToken(Student student) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        System.out.println("生成token中的student记录："+student.toString());
        token = JWT.create().withAudience(String.valueOf(student.getStid())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(student.getStpwd()));
        return token;
    }
}
