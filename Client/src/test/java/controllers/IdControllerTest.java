package controllers;

import models.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdControllerTest {
    @Test
    void getIdsList() {
        IdController idController = new IdController();
        List<Id> ids = idController.getIdsList();

        Boolean actual = (ids.size() > 10);
        Assertions.assertTrue(actual);
    }

//    @Test
//    void idPost() {
//        IdController controller = new IdController();
//        Id id = new Id("Jiayong", "linjiayo");
//
//        String res = controller.idPost(id);
//        System.out.println(res);
//
//    }
}