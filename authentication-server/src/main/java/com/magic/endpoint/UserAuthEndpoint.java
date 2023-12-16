package com.magic.endpoint;

import com.magic.authentication.bo.AuthUserInfo;
import com.magic.common.bo.UserInfo;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/16 12:25
 *
 */


@RestSchema(schemaId = "UserAuthEndpoint")
@RequestMapping(path = "auth")
public class UserAuthEndpoint {

    @Autowired
    AuthenticationEndpoint authenticationEndpoint;

    private static final RestTemplate restTemplate = RestTemplateBuilder.create();


    @RequestMapping(path = "/login")
    public AuthUserInfo login(String username, String password){
        StringBuilder url = new StringBuilder();
        url.append("cse://user-service/user/auth/login?username=").append(username);
        url.append("&password=").append(password);
        UserInfo userInfo = restTemplate.getForObject(url.toString(),UserInfo.class);
        if(userInfo!=null){
            AuthUserInfo authUserInfo = new AuthUserInfo();
            BeanUtils.copyProperties(userInfo,authUserInfo);
            String token = authenticationEndpoint.generateToken(authUserInfo);
            authUserInfo.setToken(token);
            return authUserInfo;
        }
        return null;
    }
}
