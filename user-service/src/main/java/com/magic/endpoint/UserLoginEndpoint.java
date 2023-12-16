package com.magic.endpoint;

import com.magic.common.bo.UserInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * <p>user服务的登录接口，只能通过authentication-server服务对外发布： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/13 22:25
 *
 */

@RestSchema(schemaId = "UserLoginEndpoint")
@RequestMapping(path = "/user/auth/")
@Api(tags = {"INTERNAL"})
public class UserLoginEndpoint {
    @RequestMapping(path = "/login")
    public UserInfo login(String username, String password){
        if(StringUtils.equals(username,"admin")&&StringUtils.equals(password,"admin")){
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(18);
            userInfo.setSex(1);
            userInfo.setUsername("大漂亮");
            return userInfo;
        }else {
            return null;
        }
    }
}
