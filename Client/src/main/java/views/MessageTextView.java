package views;

import models.Message;

import java.util.List;

public class MessageTextView {
    List<Message> messages;
    public String toString(Message m) {
        return m.toString();
    }

    public String messagesString(List<Message> messages) {
        StringBuilder res = new StringBuilder();
        for (Message m : messages) {
            res.append(m.toString());
        }
        return res.toString();
    }
}