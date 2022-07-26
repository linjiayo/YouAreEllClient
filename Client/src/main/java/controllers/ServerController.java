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


     private String getReq(String urlPath) {
         try {
             CloseableHttpClient httpClient = HttpClients.createDefault();
             HttpGet httpGetMsgs = new HttpGet(rootURL + urlPath);

             CloseableHttpResponse res = httpClient.execute(httpGetMsgs);

             assert(res.getStatusLine().getStatusCode() == 200);

             String jsonRes = EntityUtils.toString(res.getEntity());
             httpClient.close();
             return jsonRes;
        } catch (IOException e) {
        System.out.println("Invalid request");
        return "";
         }
     }

//     private String postMsg(String gitHubId) {
//         try {
//             CloseableHttpClient httpclient = HttpClients.createDefault();
//             String json = this.mapper.writeValueAsString(id);
//             HttpPost httpPost = new HttpPost(rootURL + "id");
//             httpPost.setEntity(new StringEntity(json));
//
//             CloseableHttpResponse res = httpclient.execute(httpPost);
//             assert(res.getStatusLine().getStatusCode() == 200);
//
//             String jsonRes = EntityUtils.toString(res.getEntity());
//             httpclient.close();
//             return jsonRes;
//
//         } catch (JsonProcessingException e) {
//         } catch(IOException e) {
//         }
//         return "";
//     }

    public String idGet() {
        // url -> /ids/
        // send the server a get with url
        // return json from server
        return getReq("/ids");
    }
    public String idPost(Id id) {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String json = this.mapper.writeValueAsString(id);
            HttpPost httpPost = new HttpPost(rootURL + "id");
            httpPost.setEntity(new StringEntity(json));

            CloseableHttpResponse res = httpclient.execute(httpPost);
            assert(res.getStatusLine().getStatusCode() == 200);

            String jsonRes = EntityUtils.toString(res.getEntity());
            httpclient.close();
            return jsonRes;

        } catch (JsonProcessingException e) {
        } catch(IOException e) {
        }
        return "";
    }
    public String idPut(Id id) {
        // url -> /ids/

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String json = this.mapper.writeValueAsString(id);
            HttpPut put = new HttpPut(rootURL + "id");
            put.setEntity(new StringEntity(json));

            CloseableHttpResponse res = httpClient.execute(put);

            assert(res.getStatusLine().getStatusCode() == 200);

            String jsonRes = EntityUtils.toString(res.getEntity());
            httpClient.close();
            return jsonRes;

        } catch (IOException e) {
            return "";
        }
    }

    /** Gets from /messages/ **/
    public String getMessages() {
        return getReq("/messages");
    }

    // /ids/:mygithubid/messages/
    public String getMessageFromId(String githubId) {
        return getReq(String.format("/ids/%s/messages", githubId));
    }

}

// ServerController.shared.doGet()