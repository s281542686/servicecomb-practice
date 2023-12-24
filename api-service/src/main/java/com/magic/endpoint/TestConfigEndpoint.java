package com.magic.endpoint;

import com.magic.config.ConfigInfo;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * <p>描述： 测试cse在集成Apollo的时候的配置动态注入情况</p>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/24 10:11
 *
 */


@RestSchema(schemaId = "test")
@RequestMapping(path = "/test/")
public class TestConfigEndpoint {
    @Autowired
    ConfigInfo configInfo;

    //不会动态更新
    @Value("${magic}")
    private String magic;

    @GetMapping(path = "testInjectProperties")
    public String testInjectProperties(){
        return configInfo.getMagic();
    }

    @GetMapping(path = "testValue")
    public String testValue() {
        return magic;
    }


}
