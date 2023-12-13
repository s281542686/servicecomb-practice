package com.magic.endpoint;

import io.swagger.annotations.Api;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * <p>描述： <>
 * @author jianmin.long [longjianmin@sina.com]
 * @version 1.0.0
 * 2023/12/13 22:25
 *
 */

@RestSchema(schemaId = "InternalAccessUserEndpoint")
@RequestMapping(path = "/user/InternalAccess/")
@Api(tags = {"INTERNAL"})
public class InternalAccessEndpoint {
    @GetMapping(path = "localAccess")
    public String localAccess(String name) {
        return "INTERNAL";
    }
}
