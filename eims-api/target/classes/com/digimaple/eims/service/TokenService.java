package com.digimaple.eims.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.digimaple.eims.model.Student;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发啊啦啦啦
 * @Title: TokenService.java
 */
@Service("TokenService")
public class TokenService {

    public String getToken(Student student) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        System.out.println("生成token中的Student记录："+student.toString());
        token = JWT.create().withAudience(String.valueOf(student.getId())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(student.getPassword()));
        return token;
    }
}
