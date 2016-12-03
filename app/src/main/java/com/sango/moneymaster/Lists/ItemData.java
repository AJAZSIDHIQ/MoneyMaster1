package com.sango.moneymaster.Lists;

/**
 * Created by sango on 9/11/2016.
 */
public class ItemData {
    String text;
    String imageId;
    int Id;

    public ItemData(String text, String imageId, int Id) {
        this.text = text;
        this.imageId = imageId;
        this.Id = Id;
    }

    public String getText() {
        return text;
    }

    public String getImageId() {
        return imageId;
    }

    public int getId() { return Id; }
}
