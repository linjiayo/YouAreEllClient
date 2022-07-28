package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import models.Id;
import models.Message;

public class IdController {
    private ServerController serverController;
    private HashMap<String, Id> idCache;

    Id myId;

    public IdController() {
        this.serverController = ServerController.getInstance();
    }

    // TODO refactor separation of controllers
    public List<Id> parseJsonIdArr(String jsonArr) {
        try {
            List<Id> list = serverController.getMapper().readValue(jsonArr, new TypeReference<ArrayList<Id>>(){});
            return list;
        } catch(JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String idGet() {
        return serverController.getReq("/ids");
    }

    public String idPost(Id id) {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
        try {
            String payload = serverController.getMapper().writeValueAsString(id);
            return serverController.post("/ids", payload);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public String putId(Id id) {
        try {
            String payload = serverController.getMapper().writeValueAsString(id);
            return serverController.putReq("/ids", payload);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}