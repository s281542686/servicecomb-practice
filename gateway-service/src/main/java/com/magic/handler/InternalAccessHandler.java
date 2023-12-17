package com.magic.handler;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

import java.util.List;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/13 22:18
 *
 */


public class InternalAccessHandler implements Handler {
    /***
     * 网关实现Hanlder，检查访问的接口是否具备定义的tags，如果具备，认为是内部接口，不允许通过网关访问。
     * 检查接口是否定义了名称为”INTERNAL”的tags，如果包含，则不允许访问
     * @param invocation
     * @param asyncResp
     * @throws Exception
     */
    @Override
    public void handle(Invocation invocation, AsyncResponse asyncResp) throws Exception {
        List<String> tags = invocation.getOperationMeta().getSwaggerOperation().getTags();
        if (tags != null && tags.contains("INTERNAL")) {
            asyncResp.consumerFail(new InvocationException(403, "not allowed", "内部服务，禁止访问"));
            return;
        }
        invocation.next(asyncResp);
    }
}
