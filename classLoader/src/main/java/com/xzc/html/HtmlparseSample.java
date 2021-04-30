package com.xzc.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;

/**
 * @ClassName HtmlparseSample
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/4/29 14:06
 * @Version 1.0
 **/
public class HtmlparseSample {
    public static void main(String[] args) throws Exception {
        /**
         * 下面是Jsoup展现自我的平台
         */
        //6.Jsoup解析html
        Document document = Jsoup.parse(new File("F://1.html"), "utf-8");
//        Document document = Jsoup.parse(html);
        //像js一样，通过标签获取title
//        System.out.println(document.getElementsByTag("title").first());
        //像js一样，通过id 获取文章列表元素对象
//        Element postList = document.getElementById("post_list");
        //像js一样，通过class 获取列表下的所有博客
        Elements postItems = document.getElementsByClass("search-item sv-search-company");
        //循环处理每篇博客
        for (Element postItem : postItems) {
            Elements content = postItem.getElementsByClass("content");

            Elements cols = content.first().getElementsByClass("col");
            Elements span = cols.select("span");
            String phone="", email="";
            if (span != null && span.size() == 4) {
                List<String> list = span.eachText();
                phone=list.get(1);
                email=list.get(3);
            }

            Elements header = content.first().getElementsByClass("header");
            Elements address = content.last().getElementsByClass("contact row");
            Elements person = content.first().getElementsByClass("legalPersonName link-click");
            Elements holds = content.first().getElementsByClass("title -narrow text-ellipsis");
            Elements tag = header.first().getElementsByClass("tag-common -normal-bg");
            Elements name = header.first().getElementsByClass("name");
            String comName = name.get(0).text();
            String staus = tag.get(0).text();

            Elements taglists = content.first().getElementsByClass("tag-list");
            Elements taglists2 = taglists.first().getElementsByClass("tag-common -primary -new");
            String text = taglists2.first().text();
            String text1 = taglists2.next().text();
            String person2 = person.first().text();
            String hold = holds.first().text();
            String createdate = holds.next().text();
            String address2 = address.text();
            System.out.println(comName + "," + staus + "," + text + "," + text1 + "," + person2 + ","
                    + hold.replaceAll("注册资本：", "")
                    + "," + createdate.replaceAll("成立日期：", "") + "," + phone + "," + email+","+address2.replaceAll("地址：",""));

        }
    }
}
