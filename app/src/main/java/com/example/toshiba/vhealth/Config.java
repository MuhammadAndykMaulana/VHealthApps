package com.example.toshiba.vhealth;

/**
 * Created by TOSHIBA on 10/07/2017.
 */

public class Config {
    public static final String SERVER_1 = "http://202.182.58.202:3001/atsa";
    public static final String SERVER_2 = "http://202.182.58.202:4002/";

    public static final String EVENT_CONNECT_GPS = "msg:gps-connect";
    public static final String EVENT_DISCONNECT_GPS = "msg:gps-disconnect";
    public static final String EVENT_GPS_DATA = "em:gps-data";

    public static final String EVENT_ID_BROADCAST = "em:id-broadcast";
    public static final String EVENT_JOIN_ROOM = "msg:join-room";
    public static final String EVENT_ROOM_BROADCAST = "em:room-broadcast";
    public static final String EVENT_DATA_MQTT = "mqtt";

    public static final String SERIAL_NUUMBER = "00000000439a2140";

}
