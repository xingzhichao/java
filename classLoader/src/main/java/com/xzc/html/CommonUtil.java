package com.xzc.html;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

public class CommonUtil {


    /**
     * 发送https请求 授权模块用
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");//设置参数类型是json格式
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("logType", "base");
            conn.setRequestProperty("Cookie", "TYCID=b56c8a10a8ad11eb8b698764e79a5c25; undefined=b56c8a10a8ad11eb8b698764e79a5c25; ssuid=1722550896; sajssdk_2015_cross_new_user=1; jsid=SEO-BING-ALL-SY-000001; _ga=GA1.2.836355481.1619675061; _gid=GA1.2.554727485.1619675061; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2232793717%22%2C%22first_id%22%3A%221791c26cf9a3a7-07949f7b9419ad-d7e1739-2073600-1791c26cf9bcb%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%221791c26cf9a3a7-07949f7b9419ad-d7e1739-2073600-1791c26cf9bcb%22%7D; tyc-user-phone=%255B%252218211083152%2522%255D; searchSessionId=1619688995.36540854; tyc-user-info={%22claimEditPoint%22:%220%22%2C%22explainPoint%22:%220%22%2C%22vipToMonth%22:%22false%22%2C%22personalClaimType%22:%22none%22%2C%22integrity%22:%2220%25%22%2C%22state%22:%225%22%2C%22score%22:%22205%22%2C%22anonymityLogo%22:%22https://static.tianyancha.com/design/anonymity/anonymity4.png%22%2C%22announcementPoint%22:%220%22%2C%22surday%22:%22363%22%2C%22messageShowRedPoint%22:%220%22%2C%22vipManager%22:%220%22%2C%22monitorUnreadCount%22:%22466%22%2C%22discussCommendCount%22:%221%22%2C%22onum%22:%223%22%2C%22showPost%22:null%2C%22showAnonymityName%22:%22%E5%8C%BF%E5%90%8D%E7%94%A8%E6%88%B71f46475%22%2C%22messageBubbleCount%22:%220%22%2C%22claimPoint%22:%220%22%2C%22token%22:%22eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIxMTA4MzE1MiIsImlhdCI6MTYxOTY4OTExOSwiZXhwIjoxNjUxMjI1MTE5fQ.4a5NbH-Li7lWa5750Tu2KoOHMuRK0TRghC1N01QsOsh4nQCJYvGdu4zoz42Gtif7nEYBVHqQUQA1aWQUgtKZVw%22%2C%22schoolAuthStatus%22:%222%22%2C%22userId%22:%2232793717%22%2C%22vipToTime%22:%221651042060743%22%2C%22scoreUnit%22:%22%22%2C%22redPoint%22:%220%22%2C%22myTidings%22:%220%22%2C%22companyAuthStatus%22:%222%22%2C%22originalScore%22:%22205%22%2C%22myAnswerCount%22:%220%22%2C%22myQuestionCount%22:%220%22%2C%22signUp%22:%220%22%2C%22realBossStatus%22:%222%22%2C%22privateMessagePointWeb%22:%220%22%2C%22nickname%22:%22%E6%9D%A8%E9%9C%87%22%2C%22headPicUrl%22:%22https://cdn.tianyancha.com/design/avatar/v3/man6.png%22%2C%22privateMessagePoint%22:%220%22%2C%22bossStatus%22:%222%22%2C%22isClaim%22:%220%22%2C%22yellowDiamondEndTime%22:%220%22%2C%22isExpired%22:%220%22%2C%22yellowDiamondStatus%22:%22-1%22%2C%22pleaseAnswerCount%22:%220%22%2C%22bizCardUnread%22:%220%22%2C%22vnum%22:%220%22%2C%22mobile%22:%2218211083152%22%2C%22riskManagement%22:{%22servicePhone%22:null%2C%22mobile%22:18211083152%2C%22title%22:null%2C%22currentStatus%22:null%2C%22lastStatus%22:null%2C%22quickReturn%22:false%2C%22oldVersionMessage%22:null%2C%22riskMessage%22:null}}; tyc-user-info-save-time=1619689121472; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIxMTA4MzE1MiIsImlhdCI6MTYxOTY4OTExOSwiZXhwIjoxNjUxMjI1MTE5fQ.4a5NbH-Li7lWa5750Tu2KoOHMuRK0TRghC1N01QsOsh4nQCJYvGdu4zoz42Gtif7nEYBVHqQUQA1aWQUgtKZVw; RTYCID=d47a54c5745248a394dc12821f0901af; CT_TYCID=25321d3cd2cf435e8072d900d581b44f; acw_tc=76b20f8516196928149743664e111b8af2ede53b523dc91fc6afc3c73ab86d; aliyungf_tc=875ed6b2fff2fa737c1ca2e235253666bcc21f1fa3910ffaf50860220f6ac875; csrfToken=NOyMiz5FRnoAJSHCDI8W1fFA; bannerFlag=true; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1619675034,1619675061,1619693132; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1619693297");
            conn.setRequestProperty("Host", " www.tianyancha.com");
            conn.setRequestProperty(" Content-Length", "418");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("Referer", "https://www.tianyancha.com/advance/search/result");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");

//            :
//            sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="90", "Google Chrome";v="90"
//            sec-ch-ua-mobile: ?0
//            Upgrade-Insecure-Requests: 1
//            Origin: https://www.tianyancha.com
//            Content-Type: application/x-www-form-urlencoded
//            User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36
//            Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//Sec-Fetch-Site: same-origin
//Sec-Fetch-Mode: navigate
//Sec-Fetch-User: ?1
//Sec-Fetch-Dest: document
//
//Accept-Encoding: gzip, deflate, br
//Accept-Language: zh-CN,zh;q=0.9
//Cookie: TYCID=b56c8a10a8ad11eb8b698764e79a5c25; undefined=b56c8a10a8ad11eb8b698764e79a5c25; ssuid=1722550896; sajssdk_2015_cross_new_user=1; jsid=SEO-BING-ALL-SY-000001; aliyungf_tc=49f95f1bcbc212725175dbda61d9fe25029ea7fcf8be005bccd251169e103575; csrfToken=X-zFaRZxNIfk7xJmA73yl9Cd; bannerFlag=true; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1619675034,1619675061; _ga=GA1.2.836355481.1619675061; _gid=GA1.2.554727485.1619675061; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2232793717%22%2C%22first_id%22%3A%221791c26cf9a3a7-07949f7b9419ad-d7e1739-2073600-1791c26cf9bcb%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%221791c26cf9a3a7-07949f7b9419ad-d7e1739-2073600-1791c26cf9bcb%22%7D; tyc-user-phone=%255B%252218211083152%2522%255D; searchSessionId=1619685051.39511667; tyc-user-info={%22claimEditPoint%22:%220%22%2C%22explainPoint%22:%220%22%2C%22vipToMonth%22:%22false%22%2C%22personalClaimType%22:%22none%22%2C%22integrity%22:%2220%25%22%2C%22state%22:%225%22%2C%22score%22:%22205%22%2C%22anonymityLogo%22:%22https://static.tianyancha.com/design/anonymity/anonymity4.png%22%2C%22announcementPoint%22:%220%22%2C%22surday%22:%22363%22%2C%22messageShowRedPoint%22:%220%22%2C%22vipManager%22:%220%22%2C%22monitorUnreadCount%22:%22466%22%2C%22discussCommendCount%22:%221%22%2C%22onum%22:%223%22%2C%22showPost%22:null%2C%22showAnonymityName%22:%22%E5%8C%BF%E5%90%8D%E7%94%A8%E6%88%B71f46475%22%2C%22messageBubbleCount%22:%220%22%2C%22claimPoint%22:%220%22%2C%22token%22:%22eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIxMTA4MzE1MiIsImlhdCI6MTYxOTY4NTE0NiwiZXhwIjoxNjUxMjIxMTQ2fQ.TnYJqWOsYAU8GKdphjK9fNFqmEbxC-DPTFDWkCQ3QnMLX3OYXVwDG47huci05ofiiBSa52lE0EOlfVJdhs6KOw%22%2C%22schoolAuthStatus%22:%222%22%2C%22userId%22:%2232793717%22%2C%22vipToTime%22:%221651042060743%22%2C%22scoreUnit%22:%22%22%2C%22redPoint%22:%220%22%2C%22myTidings%22:%220%22%2C%22companyAuthStatus%22:%222%22%2C%22originalScore%22:%22205%22%2C%22myAnswerCount%22:%220%22%2C%22myQuestionCount%22:%220%22%2C%22signUp%22:%220%22%2C%22realBossStatus%22:%222%22%2C%22privateMessagePointWeb%22:%220%22%2C%22nickname%22:%22%E6%9D%A8%E9%9C%87%22%2C%22headPicUrl%22:%22https://cdn.tianyancha.com/design/avatar/v3/man6.png%22%2C%22privateMessagePoint%22:%220%22%2C%22bossStatus%22:%222%22%2C%22isClaim%22:%220%22%2C%22yellowDiamondEndTime%22:%220%22%2C%22isExpired%22:%220%22%2C%22yellowDiamondStatus%22:%22-1%22%2C%22pleaseAnswerCount%22:%220%22%2C%22bizCardUnread%22:%220%22%2C%22vnum%22:%220%22%2C%22mobile%22:%2218211083152%22%2C%22riskManagement%22:{%22servicePhone%22:null%2C%22mobile%22:18211083152%2C%22title%22:null%2C%22currentStatus%22:null%2C%22lastStatus%22:null%2C%22quickReturn%22:false%2C%22oldVersionMessage%22:null%2C%22riskMessage%22:null}}; tyc-user-info-save-time=1619685147942; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIxMTA4MzE1MiIsImlhdCI6MTYxOTY4NTE0NiwiZXhwIjoxNjUxMjIxMTQ2fQ.TnYJqWOsYAU8GKdphjK9fNFqmEbxC-DPTFDWkCQ3QnMLX3OYXVwDG47huci05ofiiBSa52lE0EOlfVJdhs6KOw; acw_tc=2f6fc11b16196853408853756e73d3662d6b7fe1420545b09afbe47438ed0a; _gat_gtag_UA_123487620_1=1; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1619685479
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();

            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("连接超时：" + ce);
        } catch (Exception e) {
            System.out.println("https请求异常：" + e);
        }
        return jsonObject;
    }
}