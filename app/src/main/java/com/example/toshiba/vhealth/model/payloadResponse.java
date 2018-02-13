package com.example.toshiba.vhealth.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by TOSHIBA on 13/11/2017.
 */

public class payloadResponse {
    @JSONField(name = "topic")
    private String topic;
    @JSONField(name = "payload")
    private String payload;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
