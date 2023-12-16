package com.magic.endpoint;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.magic.common.bo.UserInfo;
import io.swagger.annotations.Api;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/16 11:34
 *
 */

@RestSchema(schemaId = "AuthenticationEndpoint")
@RequestMapping(path = "/auth/")
@Api(tags = {"INTERNAL"})
public class AuthenticationEndpoint {

    Logger logger = LoggerFactory.getLogger(AuthenticationEndpoint.class);

    //选择算法
    private static final Algorithm algorithm = Algorithm.HMAC256("secret");

    @RequestMapping(path = "/generateToken")
    public String generateToken(UserInfo userInfo){
        DateTime currentDateTime = DateTime.now();
        //最长120分钟内有效
        DateTime expireTime = currentDateTime.plusMinutes(120);
        String token = JWT.create()
                //发行人:当前用户
                .withIssuer(userInfo.getUsername())
                //发布时间
                .withIssuedAt(currentDateTime.toDate())
                //到期时间
                .withExpiresAt(expireTime.toDate())
                //TOKEN ID
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
        return token;
    }


    @RequestMapping(path = "/checkToken")
    public Boolean checkToken(String token){
        try {
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            logger.error("${},校验失败",token,e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @RequestMapping(path = "/flushToken")
    public String flushToken(String token){
        return  "magic";
    }

}
