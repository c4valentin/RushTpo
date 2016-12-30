package beastandroiddev.rushtpo.views.RushViews;

import java.util.List;

import beastandroiddev.rushtpo.entities.RushEvent;

public class Item {
    public int type;
    public String text;
    public RushEvent rushEvent;
    public List<Item> invisibleChildren;

    public Item(int type, RushEvent rushEvent) {
        this.type = type;
        this.rushEvent = rushEvent;
    }

    public Item(int type, String text) {
        this.type = type;
        this.text = text;
    }
}