package com.magic.endpoint;

import org.apache.commons.lang.StringUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/12 23:08
 *
 */


@RestSchema(schemaId = "auth")
@RequestMapping(path = "/auth/")
public class AuthenticationEndpoint {

    @RequestMapping(path = "/login")
    public Boolean login(String username, String password){
        if(StringUtils.equals(username,"admin")&&StringUtils.equals(password,"admin")){
            return true;
        }else {
            return false;
        }
    }

}
