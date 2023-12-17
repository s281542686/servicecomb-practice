package com.magic.handler;

import com.magic.client.CheckTokenOperation;
import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.provider.pojo.Invoker;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/12 23:14
 *
 */


public class AuthHandler implements Handler {
    private static Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    private static CheckTokenOperation checkTokenService;
    static {
        checkTokenService = Invoker.createProxy("authentication-server", "AuthenticationEndpoint", CheckTokenOperation.class);
    }

    @Override
    public void handle(Invocation invocation, AsyncResponse asyncResponse) throws Exception {
        String token = invocation.getRequestEx().getHeader("Authorization");
        // 验证没有携带token，或者token校验失败
        if (token == null) {
            asyncResponse.consumerFail(new InvocationException(403, "forbidden", "not authenticated"));
            return;
        }

        //edge service的定制逻辑，包括Dispatcher,Handler,HttpServerFilter等，均在事件派发线程event-loop中执行，
        //任何定制逻辑必须不能够存在阻塞逻辑，否则会导致edge service出现死锁。
        //死锁问题会在并发数大于event-loop线程数量的情况下出现。（event-loop线程数量默认是CPU核数的两倍， 可以通过 jstack 命令查看线程。）
        CompletableFuture<Boolean> result = checkTokenService.checkToken(token);
        result.whenComplete((v,e)->{
            if(result.isCompletedExceptionally()){
                logger.error(e.getMessage(),e);
                asyncResponse.complete(Response.failResp(new InvocationException(403, "forbidden", "not authenticated")));
                return;
            }
            if(v==null || !v){
                asyncResponse.complete(Response.failResp(new InvocationException(403, "forbidden", "not authenticated")));
                return;
            }
            try {
                invocation.next(asyncResponse);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
