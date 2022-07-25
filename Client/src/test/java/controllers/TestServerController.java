package controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestServerController {

    @Test
    void getInstance() {
        ServerController instance = ServerController.getInstance();

        Assertions.assertNotNull(instance);
    }

    @Test
    void idGet() {
        ServerController instance = ServerController.getInstance();
        String idString = instance.idGet();
        System.out.println(idString);
    }
}