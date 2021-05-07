package com.xzc.html;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName HtmlparseSample
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/29 14:06
 * @Version 1.0
 **/
public class CreateHtmlparseSample {
    @Test
    public void test() {
        String url = "https://www.tianyancha.com/advance/search/result?eventPrefix=pc_box";
        ValidRequestEntity validRequestEntity = new ValidRequestEntity();
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", JSON.toJSONString(validRequestEntity));
        System.out.println(jsonObject);
    }
}

//6.Jsoup解析html
//        Document document = Jsoup.parse(new File("F://1.html"), "utf-8");
//        String url = "https://www.tianyancha.com/advance/search/result";
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager).build();
////        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
//        ValidRequestEntity validRequestEntity = new ValidRequestEntity();
//        RequestBody rb = RequestBody.create(mediaType, JSON.toJSONBytes(validRequestEntity));
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .post(rb).build();
//        okhttp3.Response response = null;
//        try {
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException("远程调用异常" + e.toString());
//        }
//        if (response.isSuccessful()) {
//            ResponseBody body = response.body();
//        } else {
//
//        }
