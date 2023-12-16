package com.magic.authentication.bo;

import com.magic.common.bo.UserInfo;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/16 13:17
 *
 */


public class AuthUserInfo extends UserInfo{

    private String token;

    private String flushToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFlushToken() {
        return flushToken;
    }

    public void setFlushToken(String flushToken) {
        this.flushToken = flushToken;
    }
}
