package com.example.toshiba.vhealth.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by TOSHIBA on 13/11/2017.
 */

public class VisualitationResponse {
    @JSONField(name="OBDSupported")
    private String oBDSupported;

    @JSONField(name="CheckEngine")
    private String checkEngine;

    @JSONField(name="Time")
    private String time;

    @JSONField(name="gps")
    private String gps;

    @JSONField(name="id")
    private String id;

    public void setOBDSupported(String oBDSupported){
        this.oBDSupported = oBDSupported;
    }

    public String getOBDSupported(){
        return oBDSupported;
    }

    public void setCheckEngine(String checkEngine){
        this.checkEngine = checkEngine;
    }

    public String getCheckEngine(){
        return checkEngine;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public void setGps(String gps){
        this.gps = gps;
    }

    public String getGps(){
        return gps;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString(){
        return
                "VisualitationResponse{" +
                        "oBDSupported = '" + oBDSupported + '\'' +
                        ",checkEngine = '" + checkEngine + '\'' +
                        ",time = '" + time + '\'' +
                        ",gps = '" + gps + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
