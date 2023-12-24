package com.magic.config;

import org.apache.servicecomb.config.inject.InjectProperties;
import org.apache.servicecomb.config.inject.InjectProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/***
 * <p>描述：配置变更自动注入生效，field上的prefix会覆盖class上的prefix </p>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/19 19:53
 *
 */

@Configuration()
@ConfigurationProperties()
public class ConfigInfo2 {
    @Value("${magic}")
    private String magic;

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }
}
