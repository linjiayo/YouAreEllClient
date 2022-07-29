package views;

import models.Id;

import java.util.ArrayList;
import java.util.List;

public class IdTextView {
    private List<Id> id;

    public IdTextView(List<Id> ids) {
        this.id = ids;
    }

    public IdTextView() {
        this.id = new ArrayList<>();
    }

    public String idsString(List<Id> ids) {
        StringBuilder res = new StringBuilder();
        for (Id i : ids) {
            res.append(i.toString() + "\n");
        }
        return res.toString();
    }
    @Override public String toString() {
        return this.id.toString();
    } 
}