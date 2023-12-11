package com.magic.endpoint;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/9 23:17
 *
 */

@RestSchema(schemaId = "api")
@RequestMapping(path = "/")
public class ApiEndpoint {

    /*
    使用REST通信的服务一般采用json进行编解码。
    String 类型的数据,编码为json的时候,存在双引号。比如abc编码以后为"abc"
    接收不带双引号的参数,使用 MediaType.TEXT_PLAIN_VALUE, 不使用 MediaType.APPLICATION_JSON_VALUE
    如果响应不期望带双引号,可以使用 produces = MediaType.TEXT_PLAIN_VALUE
    */
    @GetMapping(path = "/{path}/sayHello",produces = MediaType.TEXT_PLAIN_VALUE,consumes = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello(@PathVariable("path")String path, @RequestParam("name") String name) {
        return "Hello " + path +name;
    }
}
