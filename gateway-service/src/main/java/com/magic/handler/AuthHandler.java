package com.magic.handler;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/12 23:14
 *
 */


public class AuthHandler implements Handler {
    @Override
    public void handle(Invocation invocation, AsyncResponse asyncResponse) throws Exception {
        String token = invocation.getContext("CONTEXT_HEADER_AUTHORIZATION");
        // 验证没有携带token，或者token校验失败
        if (token == null) {
            asyncResponse.consumerFail(new InvocationException(403, "forbidden", "not authenticated"));
            return;
        }
        invocation.next(asyncResponse);
    }

}
