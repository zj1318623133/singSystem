package com.example.smile.util;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    /**
     * 向HTTPS地址发送POST请求
     *
     * @param reqURL
     *            请求地址
     * @param params
     *            请求参数
     * @return 响应内容
     */
    @SuppressWarnings({ "finally", "deprecation", "resource" })
    public static String sendSSLPostRequest(HttpClient httpClient, String reqURL, Map<String, String> params ,Map<String,String> requestHeaders) {
        long responseLength = 0;
         String responseContent = null; // 响应内容

        try {
            HttpPost httpPost = new HttpPost(reqURL); // 创建HttpPost
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000).build();
            httpPost.setConfig(requestConfig);
//            HttpParams param = httpClient.getParams();
//            param.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);

            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            for (Map.Entry<String,String> entry : requestHeaders.entrySet()) {
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
            HttpResponse response = httpClient.execute(httpPost); // 执行POST请求

            System.out.println("httpclicent" + response.getStatusLine().getStatusCode());

            HttpEntity entity = response.getEntity(); // 获取响应实体

            int statusCode = response.getStatusLine().getStatusCode();
            if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statusCode == HttpStatus.SC_SEE_OTHER) || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header location = response.getFirstHeader("Location");
                if (location != null) {
                    String newuri = location.getValue();
                    if ((newuri == null) || (newuri.equals("")))
                        newuri = "/";
                    if (!newuri.startsWith("https") && !newuri.startsWith("http")) {
                        newuri = "http://www.zanmeishi.com" + newuri;
                    }
                    System.out.println(newuri);
                    httpPost.releaseConnection();
                    HttpGet httpGet = new HttpGet(newuri);
                    response = httpClient.execute(httpGet);
                    entity = response.getEntity();
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                    httpGet.releaseConnection();
                } else
                    System.out.println("Invalid redirect");
            } else {
                entity = response.getEntity();
                responseContent = EntityUtils.toString(entity);
            }

            if (null != entity) {
                responseLength = entity.getContentLength();
                EntityUtils.consume(entity); // Consume response content
            }

            return responseContent;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return responseContent;
        }
    }

    /**
     * 向HTTPS地址发送POST请求
     *
     * @param reqURL
     *            请求地址
     * @param params
     *            请求参数
     * @return 响应内容
     */
    @SuppressWarnings({ "finally", "deprecation", "resource" })
    public static String sendSSLPostRequestJsonParam(HttpClient httpClient, String reqURL, String params ,Map<String,String> requestHeaders) {
        long responseLength = 0;
        String responseContent = null; // 响应内容
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(reqURL); // 创建HttpPost
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000).build();
            httpPost.setConfig(requestConfig);
            HttpParams param = httpClient.getParams();
            param.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);

            // 构建POST请求的表单参数
            httpPost.setEntity(new StringEntity(params));

            for (Map.Entry<String,String> entry : requestHeaders.entrySet()) {
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
            HttpResponse response = httpClient.execute(httpPost); // 执行POST请求

            HttpEntity entity = response.getEntity(); // 获取响应实体

            int statusCode = response.getStatusLine().getStatusCode();
            if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statusCode == HttpStatus.SC_SEE_OTHER) || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header location = response.getFirstHeader("Location");
                if (location != null) {
                    String newuri = location.getValue();
                    if ((newuri == null) || (newuri.equals("")))
                        newuri = "/";
                    if (!newuri.startsWith("https")) {
                        newuri = "https://www.spirit.com" + newuri;
                    }
                    HttpGet httpGet = new HttpGet(newuri);
                    httpPost.releaseConnection();
                    response = httpClient.execute(httpGet);
                    entity = response.getEntity();
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                } else
                    System.out.println("Invalid redirect");
            } else {
                entity = response.getEntity();
                responseContent = EntityUtils.toString(entity);
            }

            if (null != entity) {
                responseLength = entity.getContentLength();
                EntityUtils.consume(entity); // Consume response content
            }

            return responseContent;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
            return responseContent;
        }
    }

    public static String sendGetRequest(HttpClient httpClient, String url ,Map<String,String> requestHeaders) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        String responseContent = null; // 响应内容
        HttpGet getMethod = new HttpGet();
        long responseLength = 0;
        RequestConfig requestConfig1 = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(10000).build();

        getMethod.setConfig(requestConfig1);
        getMethod.setConfig(requestConfig);
        for (Map.Entry<String,String> entry : requestHeaders.entrySet()) {
            getMethod.setHeader(entry.getKey(),entry.getValue());
        }
        try {
            getMethod.setURI(new URI(url));
            HttpResponse response = httpClient.execute(getMethod);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statusCode == HttpStatus.SC_SEE_OTHER) || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header location = response.getFirstHeader("Location");
                if (location != null) {
                    String newuri = location.getValue();
                    if ((newuri == null) || (newuri.equals("")))
                        newuri = "/";
                    if (!newuri.startsWith("https")) {
                        newuri = "http://www.zanmeishi.com" + newuri;
                    }
                    getMethod.releaseConnection();
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                } else
                    System.out.println("Invalid redirect");
            } else {
                entity = response.getEntity();
                if (response.getFirstHeader("Content-Encoding") != null){
                    if ("gzip".equals(response.getFirstHeader("Content-Encoding").getValue())) {
                        responseContent = EntityUtils.toString(new GzipDecompressingEntity(entity), "UTF-8");
                    } else {
                        responseContent = EntityUtils.toString(entity);
                    }
                }else {
                    responseContent = EntityUtils.toString(entity);
                }
            }

            if (null != entity) {
                responseLength = entity.getContentLength();
                EntityUtils.consume(entity); // Consume response content
            }

            return responseContent;
        } catch (Exception e) {
            e.getLocalizedMessage();
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return null;
    }

    /**
     * 获取基本的HttpClient实例
     * @return
     */
    public static HttpClient getHttpClient(){
       HttpClient httpClient = HttpClients.createDefault();
       return  httpClient;
    }

    /**
     * 获取HttpClient实例，SSL
     * @return
     * @throws Exception
     */
    public static HttpClient getHttpClientAndSSL() throws Exception{
        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();

        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();

        return client;
    }

    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
}
