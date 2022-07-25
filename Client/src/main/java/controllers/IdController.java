package controllers;

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

    public Id putId(Id id) {
        return null;
    }
 
}