package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class ServerController {
    private ObjectMapper mapper;
    private String rootURL = "http://zipcode.rocks:8085";

    private static ServerController instance = new ServerController();
    private ServerController() {
        mapper = new ObjectMapper();
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
     }
     public ObjectMapper getMapper() {
         return this.mapper;
     }

     public String getReq(String urlPath) {
         try {
             CloseableHttpClient httpClient = HttpClients.createDefault();
             HttpGet httpGetMsgs = new HttpGet(rootURL + urlPath);

             CloseableHttpResponse res = httpClient.execute(httpGetMsgs);

             assert(res.getStatusLine().getStatusCode() == 200);

             String jsonRes = EntityUtils.toString(res.getEntity());
             httpClient.close();
             return jsonRes;
        } catch (IOException e) {
        System.out.println(e.getMessage());
        return "";
         }
     }

     public String post(String urlPath, String JsonPayload) {
         try {
             CloseableHttpClient httpclient = HttpClients.createDefault();
             HttpPost httpPost = new HttpPost(rootURL + urlPath);
             httpPost.setEntity(new StringEntity(JsonPayload, ContentType.APPLICATION_JSON));
             CloseableHttpResponse res = httpclient.execute(httpPost);
             assert(res.getStatusLine().getStatusCode() == 200);

             String jsonRes = EntityUtils.toString(res.getEntity());
             httpclient.close();
             return jsonRes;
         } catch(IOException e) {
             System.out.println(e.getMessage());
             return "";
         }
     }

    public String putReq(String urlPath, String JsonPayload) {
        // url -> /ids/

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPut put = new HttpPut(rootURL + urlPath);
            put.setEntity(new StringEntity(JsonPayload, ContentType.APPLICATION_JSON));
            CloseableHttpResponse res = httpClient.execute(put);

            assert(res.getStatusLine().getStatusCode() == 200);

            String jsonRes = EntityUtils.toString(res.getEntity());
            httpClient.close();
            return jsonRes;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }


}

// ServerController.shared.doGet()