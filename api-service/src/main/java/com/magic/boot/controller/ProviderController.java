package com.magic.boot.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/9 23:17
 *
 */

@RestSchema(schemaId = "apiController")
@RequestMapping(path = "/")
public class ProviderController {
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return "Hello " + name;
    }
}
