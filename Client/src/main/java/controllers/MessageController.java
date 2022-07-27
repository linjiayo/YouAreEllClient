package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import models.Id;
import models.Message;
import models.MessageWrap;

public class MessageController {
    private ServerController serverController;

    public MessageController() {
        serverController = ServerController.getInstance();
    }

    // K: sequence V: message
    private HashMap<String, Message> msgCache;
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        String msgJson = getMsgJson();
        try {
            ArrayList<Message> list = serverController.getMapper().readValue(msgJson, new TypeReference<ArrayList<Message>>(){});
            return list;
        } catch(JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Message> filterMsgs(List<Message> allMessages) {
        List<Message> filteredMsg = new ArrayList<>();

        for (Message m : allMessages) {
            if (!msgCache.containsKey(m.getSeqId())) {
                msgCache.put(m.getSeqId(), m);
                filteredMsg.add(m);
            }
        }
        return filteredMsg;
    }

    public List<Message> getNewMessages() {
        return filterMsgs(getMessages());
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

    public String postMsgJsonId(Id id, MessageWrap message) {
        try {
            String payload = serverController.getMapper().writeValueAsString(message);
            System.out.println("JSON Payload:\t" + payload);
            String urlPath = String.format("/ids/%s/messages", id.getGithub());
            return serverController.post(urlPath, payload);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String getMessageBySeq(Id id, int sequence) {
        String payload = String.format("/ids/%s/messages/%d", id.getGithub(), sequence);
        return serverController.getReq(payload);
    }

    public String getFriendMsg(Id myId, Id friendId) {
        String payload = String.format("/ids/%s/from/%s", myId.getGithub(), friendId.getGithub());
        return serverController.getReq(payload);
    }


}