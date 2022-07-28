package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

import java.util.List;

// TODO Possible refactor: TransactionController as container for msg and id controller or.. handle raw JSON
//
public class TransactionController {
    //private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.msgCtrl = m;
        this.idCtrl = j;
    }
    public MessageController getMsgCtrl() {
        return msgCtrl;
    }

    public void setMsgCtrl(MessageController msgCtrl) {
        this.msgCtrl = msgCtrl;
    }

    public IdController getIdCtrl() {
        return idCtrl;
    }

    public void setIdCtrl(IdController idCtrl) {
        this.idCtrl = idCtrl;
    }

    public List<Id> getIds() {
        return null;
    }


}
