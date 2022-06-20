
package com.moringaschool.get_busy.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("participants")
    @Expose
    private Integer participants;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("accessibility")
    @Expose
    private Float accessibility;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param activity
     * @param accessibility
     * @param price
     * @param link
     * @param type
     * @param key
     * @param participants
     */
    public Result(String activity, String type, Integer participants, Float price, String link, String key, Float accessibility) {
        super();
        this.activity = activity;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.key = key;
        this.accessibility = accessibility;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility;
    }

}
