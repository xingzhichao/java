package com.xzc.web.rainfall;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName GetRainfall
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/8/12 15:37
 * @Version 1.0
 **/
public class GetRainfall {
//    private static final CloseableHttpClient httpclient;
//
//    static {
//        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(15000).build();
//        httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
//    }
//
//    public static void main(String[] args) {
//        String url = "http://36.110.193.145:8020//rest/getStationsPerTime";
//        List<RainfallResponse.ResultBean> returnList = new ArrayList<>();
//        Map<String, Object> paramMap = new HashMap<>();
//        String s = "";
//        try {
//            List list = new ArrayList<>();
//            list.add("11");
//            paramMap.put("stcdList", list);
//            paramMap.put("startDate", "2021/07/12 00:00");
//            paramMap.put("endDate", "2021/07/12 19:00");
//            paramMap.put("rangeDays", 80);
//            s = HttpClientUtil.sendPost(httpclient, url, paramMap);
//            RainfallResponse rainfallResponse = JSONObject.parseObject(s, RainfallResponse.class);
//            List<RainfallResponse.ResultBean> result = rainfallResponse.getResult();
//            result.forEach(item -> {
//                String time = item.getTime();
//                if (time.endsWith(":00")) {
//                    returnList.add(item);
//                }
//            });
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println(JSONObject.toJSONString(returnList));
//
//        TemplateExportParams params = new TemplateExportParams("static/exceltemplate/question-shenjie.xlsx", true);
//        Map<String, Object> data = new HashMap(returnList.size());
//        data.put("list", returnList);
//
//        try {
//            // 简单模板导出方法
//            Workbook book = ExcelExportUtil.exportExcel(params, data);
//            //下载方法
//            export(response, book, "事件信息");
//        } catch (Exception e) {
//            log.error("导出异常：", e);
//        }
//    }
//
//    /**
//     * export导出请求头设置
//     *
//     * @param response
//     * @param workbook
//     * @param fileName
//     * @throws Exception
//     */
//    protected static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
//        response.reset();
//        response.setContentType("application/x-msdownload");
//        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
//        ServletOutputStream outStream = null;
//        try {
//            outStream = response.getOutputStream();
//            workbook.write(outStream);
//        } finally {
//            outStream.close();
//            outStream = null;
//        }
//    }
}
