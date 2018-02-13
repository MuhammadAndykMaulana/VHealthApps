package com.example.toshiba.vhealth.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by TOSHIBA on 13/11/2017.
 */

public class GPSResponse {
    @JSONField(name="ept")
    private double ept;

    @JSONField(name="alt")
    private double alt;

    @JSONField(name="eps")
    private double eps;

    @JSONField(name="lon")
    private double lon;

    @JSONField(name="epv")
    private double epv;

    @JSONField(name="epx")
    private double epx;

    @JSONField(name="speed")
    private double speed;

    @JSONField(name="epy")
    private double epy;

    @JSONField(name="mode")
    private int mode;

    @JSONField(name="climb")
    private int climb;

    @JSONField(name="epc")
    private double epc;

    @JSONField(name="time")
    private String time;

    @JSONField(name="track")
    private double track;

    @JSONField(name="class")
    private String jsonMemberClass;

    @JSONField(name="device")
    private String device;

    @JSONField(name="lat")
    private double lat;

    public void setEpt(double ept){
        this.ept = ept;
    }

    public double getEpt(){
        return ept;
    }

    public void setAlt(double alt){
        this.alt = alt;
    }

    public double getAlt(){
        return alt;
    }

    public void setEps(double eps){
        this.eps = eps;
    }

    public double getEps(){
        return eps;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public double getLon(){
        return lon;
    }

    public void setEpv(double epv){
        this.epv = epv;
    }

    public double getEpv(){
        return epv;
    }

    public void setEpx(double epx){
        this.epx = epx;
    }

    public double getEpx(){
        return epx;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getSpeed(){
        return speed;
    }

    public void setEpy(double epy){
        this.epy = epy;
    }

    public double getEpy(){
        return epy;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    public int getMode(){
        return mode;
    }

    public void setClimb(int climb){
        this.climb = climb;
    }

    public int getClimb(){
        return climb;
    }

    public void setEpc(double epc){
        this.epc = epc;
    }

    public double getEpc(){
        return epc;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public void setTrack(double track){
        this.track = track;
    }

    public double getTrack(){
        return track;
    }

    public void setJsonMemberClass(String jsonMemberClass){
        this.jsonMemberClass = jsonMemberClass;
    }

    public String getJsonMemberClass(){
        return jsonMemberClass;
    }

    public void setDevice(String device){
        this.device = device;
    }

    public String getDevice(){
        return device;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return lat;
    }

    @Override
    public String toString(){
        return
                "GPSResponse{" +
                        "ept = '" + ept + '\'' +
                        ",alt = '" + alt + '\'' +
                        ",eps = '" + eps + '\'' +
                        ",lon = '" + lon + '\'' +
                        ",epv = '" + epv + '\'' +
                        ",epx = '" + epx + '\'' +
                        ",speed = '" + speed + '\'' +
                        ",epy = '" + epy + '\'' +
                        ",mode = '" + mode + '\'' +
                        ",climb = '" + climb + '\'' +
                        ",epc = '" + epc + '\'' +
                        ",time = '" + time + '\'' +
                        ",track = '" + track + '\'' +
                        ",class = '" + jsonMemberClass + '\'' +
                        ",device = '" + device + '\'' +
                        ",lat = '" + lat + '\'' +
                        "}";
    }
}
