package com.magic.boot;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/9 21:01
 *
 */


@SpringBootApplication
@EnableServiceComb
public class GatewayApplication {

    public static void main(String[] args) {
        try {
            new SpringApplicationBuilder().web(WebApplicationType.NONE).sources(GatewayApplication.class).run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
