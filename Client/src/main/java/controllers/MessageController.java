package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import models.Id;
import models.Message;

public class MessageController {
    private ServerController serverController;

    public MessageController() {
        serverController = ServerController.getInstance();
    }

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() {return null;
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }


    private String getMsgJson() {
        return serverController.getReq("/messages");
    }

    private String getMsgJsonId(Id id) {
        String path = String.format("/ids/%s/messages", id.getGithub());
        return serverController.getReq(path);
    }

    public String postMsgJsonId(Id id, Message message) {
        try {
            String payload = serverController.getMapper().writeValueAsString(message);
            String urlPath = String.format("/ids/%s/messages", id.getGithub());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String getSeq(Id id, int sequence) {
        String payload = String.format("/ids/%s/messages/%d", id.getGithub(), sequence);
        return serverController.getReq(payload);
    }

    public String getFriendMsg(Id myId, Id friendId) {
        String payload = String.format("/ids/%s/from/%s", myId.getGithub(), friendId.getGithub());
        return serverController.getReq(payload);
    }
}