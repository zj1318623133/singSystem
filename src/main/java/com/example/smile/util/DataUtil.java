package com.example.smile.util;

import com.google.common.io.Files;
import org.apache.http.client.HttpClient;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DataUtil {
   public static String selectSing(String singName){
       try{

           NekoHtmlParser parser = new NekoHtmlParser();

           HttpClient httpClient = HttpUtils.getHttpClient();
           String url = "http://www.zanmeishi.com/search/search?autocomplete=%E7%94%9F%E5%91%BD%E7%9A%84%E6%B1%9F%E6%B2%B3&type=13";

           Map<String,String> headers = new HashMap<String,String>();
           headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
           headers.put("Accept-Charset","GB2312,utf-8;q=0.7,*;q=0.7");
           headers.put("Accept-Encoding","gzip, deflate");
           headers.put("Accept-Language","zh-cn,zh;q=0.5");
           headers.put("Connection","keep-alive");
           headers.put("Host","www.zanmeishi.com");
           headers.put("Referer","http://www.zanmeishi.com/");
           headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");

           Map<String,String> params = new HashMap<String,String>();
           params.put("autocomplete",singName);
           params.put("type","13");

           String html = HttpUtils.sendSSLPostRequest(httpClient, url, params, headers);
           Files.write(html, new File("C://tmp/singSearch.html"), Charset.forName("UTF-8"));

           parser.load(html,"UTF-8");

           String newUrl =  parser.selectSingleNode("//DIV[@class='songs mt5']/TABLE//TD[@class='name']/A").getAttributes().getNamedItem("href").getTextContent().trim();
           newUrl = "http://www.zanmeishi.com" + newUrl;
           html =  HttpUtils.sendGetRequest(httpClient,newUrl,headers);
           Files.write(html, new File("C://tmp/getSign.html"), Charset.forName("UTF-8"));


           return "success";
       }catch (Exception e){
           e.printStackTrace();
           return "failure";
       }
   }

    public static void main(String[] args) {
        selectSing("生命江河");
    }
}
