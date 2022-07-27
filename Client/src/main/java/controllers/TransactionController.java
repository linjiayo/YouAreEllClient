package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {}

    public List<Id> getIds() {
        return null;
    }
    public String postId(String idToRegister, String githubName) {
        Id tid = new Id(idToRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }
}
