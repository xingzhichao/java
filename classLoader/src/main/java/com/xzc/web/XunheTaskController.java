package com.xzc.web;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzc.cloud.fegin.HdServiceFeign;
import com.xzc.web.common.CallbackBody;
import com.xzc.web.rainfall.HttpClientUtil;
import com.xzc.web.rainfall.RainfallResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 巡河任务- 设置
 * http://localhost:6689/web/body/export/
 *
 * @author xzc
 * @since 2020-12-13 11:32:30
 */
@RestController
@RequestMapping("/web/body")
public class XunheTaskController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String module = "巡河任务设置";

    @Autowired
    private HdServiceFeign hdService;

    private static final CloseableHttpClient httpclient;

    @RequestMapping("redirect")
    public ModelAndView alipayforward(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String contNo = req.getParameter("contNo");
        logger.info("重定向功能演示");
        String url = "redirect:https://www.huaweicloud.com/zhishi/arc-13699570.html";
        return new ModelAndView(url);
    }

    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(60000).build();
        httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    @GetMapping(value = "export")
    public void findAll(HttpServletRequest request,
                        HttpServletResponse response) {
        String url = "http://36.110.193.145:8020//rest/getStationsPerTime";
        List<RainfallResponse.ResultBean> returnList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        String s = "";
        try {
            paramMap.put("stcdList", Arrays.asList("10", "11", "12", "13", "14", "15", "16", "17", "19", "2", "20", "21", "22", "23", "24", "25", "26"
                    , "3", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "4", "5", "6", "7", "8", "88888888", "9"));
            paramMap.put("startDate", "2021/06/01 00:00");
            paramMap.put("endDate", "2021/08/12 16:00");
            paramMap.put("rangeDays", 80);
            s = HttpClientUtil.sendPost(httpclient, url, paramMap);
            RainfallResponse rainfallResponse = JSONObject.parseObject(s, RainfallResponse.class);
            returnList = rainfallResponse.getResult();
//            result.forEach(item -> {
//                String time = item.getTime();
//                if (time.endsWith(":00")) {
//                    returnList.add(item);
//                }
//            });

        } catch (Exception e) {
            System.out.println(e);
        }
//        System.out.println(JSONObject.toJSONString(returnList));

        TemplateExportParams params = new TemplateExportParams("static/question-shenjie.xlsx", true);
        Map<String, Object> data = new HashMap(returnList.size());
        data.put("list", returnList);

        try {
            // 简单模板导出方法
            Workbook book = ExcelExportUtil.exportExcel(params, data);
            //下载方法
            export(response, book, "雨量信息");
        } catch (Exception e) {
            logger.error("导出异常：", e);
        }
    }

    /**
     * export导出请求头设置
     *
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    protected static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/x-msdownload");
        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            outStream.close();
            outStream = null;
        }
    }

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
     * @return
     */
    @GetMapping(value = "test")
    public CallbackBody<LookConfig> test() {
        LookConfig config = new LookConfig();
        config.setId("1");
        config.setLength(new BigDecimal(100));
        CallbackBody update = hdService.updateTest(config);
        logger.info(update.toJson());
        return new CallbackBody<>(CallbackBody.CALLBACK_STATUS_SUCESS, "成功!", null);
    }

    /**
     * 巡河 - 配置类更新
     *
     * @return
     */
    @PostMapping(value = "update")
    public CallbackBody<LookConfig> updateByBean(@RequestBody LookConfig config) {
        logger.info("update={}", config.toString());
        List<LookConfig> lookConfigs = JSON.parseArray("", LookConfig.class);
        return new CallbackBody<>(CallbackBody.CALLBACK_STATUS_SUCESS, "修改巡河配置成功!", null);
    }
}