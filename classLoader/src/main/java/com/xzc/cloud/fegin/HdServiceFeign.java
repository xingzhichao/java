package com.xzc.cloud.fegin;

import com.xzc.web.LookConfig;
import com.xzc.web.common.CallbackBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @return com.xzc.web.common.CallbackBody
 * @Description 更新测试
 * @Author xzc
 * @Date 14:23 2021/8/22
 **/
@FeignClient(value = "testMethod", url = "192.168.0.91:8761", path = "/")
public interface HdServiceFeign {

    /**
     * @return com.xzc.web.common.CallbackBody
     * @Description 更新测试
     * @Author xzc
     * @Date 14:23 2021/8/22
     **/
    @RequestMapping(value = "/web/body/update", method = RequestMethod.GET)
    public CallbackBody updateTest(@RequestBody LookConfig config);
}
