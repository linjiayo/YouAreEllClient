package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Id;

public class IdController {
    private ServerController serverController;
    private HashMap<String, Id> allIds;

    Id myId;

    public IdController() {
        this.serverController = ServerController.getInstance();
    }

    public ArrayList<Id> getIds() {
        ArrayList<Id> ids = new ArrayList<>();
        for (Map.Entry<String, Id> e : allIds.entrySet()) {
            ids.add(e.getValue());
        }
        return ids;
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
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