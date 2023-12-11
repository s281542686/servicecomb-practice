package com.magic.endpoint;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/11 16:17
 *
 */

@RestSchema(schemaId = "userEndpoint")
@RequestMapping(path = "/user/")
public class UserEndpoint {

    private RestTemplate restTemplate = RestTemplateBuilder.create();

    @GetMapping(path = "hello")
    public String helloUser(@RequestParam("name") String name){
        return restTemplate.getForObject("cse://api-service/sayHello?name="+name,String.class);
    }
}
