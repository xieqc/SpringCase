package com.xie.javacase.net.httpclient;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/13.
 */
public class HttpClientTest {
    @Test
    public void getTest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            /* 1.CloseableHttpResponse */
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            /* 2.Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpGet, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody); */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void postTest() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://targethost/login");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadTest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "http://apk.wsdl.vivo.com.cn/appstore/upload2/soft/201603/175/201603151021034858.apk";
        String dir = "e://";

        //设置代理
//        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("192.168.0.101", 3128));
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpget);
        Header[] header = response.getAllHeaders();
        HttpEntity entity = response.getEntity();
        InputStream input = null;
        try {
            input = entity.getContent();
            File file = new File(dir+"temp.apk");
            FileOutputStream output = FileUtils.openOutputStream(file);
            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(output);
            }
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
}
