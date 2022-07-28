package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import models.Id;
import models.Message;

public class MessageController {
    private ServerController serverController;

    public MessageController() {
        serverController = ServerController.getInstance();
    }

    // K: sequence V: message
    private HashMap<String, Message> msgCache;
    // why a HashSet??

    public List<Message> getMessages() {
        String msgJson = getMsgJson();
        return parseJsonToList(msgJson);
    }

    public List<Message> parseJsonToList(String jsonArray) {
        try {
            ArrayList<Message> list = serverController.getMapper().readValue(jsonArray, new TypeReference<ArrayList<Message>>(){});
            return list;
        } catch(JsonProcessingException e) {
            e.printStackTrace();
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

    public List<Message> getMessagesForId(Id id) {
        String url = String.format("/ids/%s/messages", id.getGithub());
        String res = serverController.getReq(url);
        return parseJsonToList(res);
    }
    public Message getMessageBySequence(String seq, Id id) {
        String url = String.format("/ids/%s/messages/$d", id.getGithub(), seq);
        String res = serverController.getReq(url);
        List<Message> resList = parseJsonToList(res);
        return (resList.size() > 0) ? resList.get(0) : null;
    }

    private String getMsgJson() {
        return serverController.getReq("/messages");
    }

    private String getMsgJsonById(Id id) {
        String path = String.format("/ids/%s/messages", id.getGithub());
        return serverController.getReq(path);
    }

    public String postMsgJsonId(Id id, Message message) {
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


    public List<Message> getMsgsFromFriend(Id myId, Id friendId) {
        String payload = String.format("/ids/%s/from/%s", myId.getGithub(), friendId.getGithub());
        String jsonRes = serverController.getReq(payload);
        return parseJsonToList(jsonRes);
    }


}