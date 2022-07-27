package controllers;

import models.Id;
import models.Message;
import models.MessageWrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessageControllerTest {

    @Test
    void getMessages() {
        MessageController msgControl = new MessageController();
        ArrayList<Message> messages = msgControl.getMessages();

        Integer expectedLen = 21;
        Assertions.assertEquals(expectedLen, messages.size());
        System.out.println(messages.get(0).toString());
    }

    // Functional - comment out to prevent spamming of server
//    @Test
//    void postMessage() {
//        MessageController msgControl = new MessageController();
//        Id id = new Id("Jiayong", "linjiayo");
//        MessageWrap msg = new MessageWrap("Professor Rankings: Fitru, Fitru, Fitru, ... Linda || Ryan", id.getGithub(),
//                id.getGithub());
//
//        String res = msgControl.postMsgJsonId(id, msg);
//        System.out.println(res);
//    }
}