package com.magic.client;

import java.util.concurrent.CompletableFuture;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/16 21:20
 *
 */


public interface CheckTokenOperation {
    CompletableFuture<Boolean> checkToken(String token);
}
