package com.xzc.web;

import com.alibaba.fastjson.JSON;
import com.xzc.web.common.CallbackBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 巡河任务- 设置
 *
 * @author xzc
 * @since 2020-12-13 11:32:30
 */
@RestController
@RequestMapping("/web/body")
public class XunheTaskController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String module = "巡河任务设置";


    /**
     * 巡河 - 配置类查询
     *
     * @return
     */
    @GetMapping(value = "findAll")
    public CallbackBody<LookConfig> findAll() {

        return new CallbackBody<>(CallbackBody.CALLBACK_STATUS_SUCESS, "查询成功!", null);
    }

    /**
     * 巡河 - 配置类更新
     *
     * @return
     */
    @PostMapping(value = "update")
    public CallbackBody<LookConfig> updateByBean(@RequestBody LookConfig config) {
        logger.info("update={}",config.toString());
        List<LookConfig> lookConfigs = JSON.parseArray("", LookConfig.class);
        return new CallbackBody<>(CallbackBody.CALLBACK_STATUS_SUCESS, "修改巡河配置成功!", null);
    }
}