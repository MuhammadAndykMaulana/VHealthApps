package com.example.toshiba.vhealth;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.toshiba.vhealth.model.payloadResponse;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class TrackActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Socket mSocket;
    private String userID;
    private String roomID;
    private Marker marker;
    private boolean isZooming = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
        initSocket();

//        this.mMap.setMyLocationEnabled(true);
//        this.mMap.getUiSettings().setMapToolbarEnabled(true);
//        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.3447636d, 106.8355373d), 7.0f));
//        this.mMap.setOnMapClickListener(this);
//        this.mMap.setOnMarkerClickListener(this);
//        this.mMap.setTrafficEnabled(true);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onIDBroadcast = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            try {
                userID = jsonObject.getString("id");
                JSONObject jsonEmit = new JSONObject();
                jsonEmit.put("id",userID);
                jsonEmit.put("room",Config.SERIAL_NUUMBER);
                mSocket.emit(Config.EVENT_JOIN_ROOM,jsonEmit);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private Emitter.Listener onGPSData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            try {
                JSONObject jsonMessage = new JSONObject((String) jsonObject.get("message"));
                Double lat = Double.valueOf(jsonMessage.getString("lat"));
                Double lon = Double.valueOf(jsonMessage.getString("lon"));

                final LatLng latLng = new LatLng(lat,lon);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(marker!=null)
                            marker.remove();
                        marker = mMap.addMarker(new MarkerOptions().position(latLng));
                        CameraUpdate cu = null;
                        if(isZooming) {
                            cu = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                            isZooming = false;
                        }
                        else cu = CameraUpdateFactory.newLatLng(latLng);
                        mMap.animateCamera(cu);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    private Emitter.Listener onMQTTData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //JSONObject jsonObject = (JSONObject) args[0];
            //JSONObject jsonObject = new JSONObject(result);
            JSONObject Payload = null;
            JSONObject Gps=null;
            Log.d("TrackActivity","Response payload GPS : "+(JSONObject)args[0]);
            Log.d("MQQT", "call: " + args[0]);
            Log.d("MQTT", "call: " + args[0].toString());
            payloadResponse response = JSON.parseObject(String.valueOf(args[0]), payloadResponse.class);
            String payloadStr = response.getPayload();
            Log.d("Payload", "payloadStr: " + payloadStr);
            try {
                Payload = new JSONObject(((JSONObject) args[0]).getString("payload"));
                Gps = new JSONObject(Payload.getString("gps"));
                //Gps = new JSONObject((Payload.getJSONObject("Gps")));
                Double lat = Double.valueOf(Gps.getString("lat"));
                Double lon = Double.valueOf(Gps.getString("lon"));

                final LatLng latLng = new LatLng(lat,lon);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(marker!=null)
                            marker.remove();
                        marker = mMap.addMarker(new MarkerOptions().position(latLng));
                        CameraUpdate cu = null;
                        if(isZooming) {
                            cu = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                            isZooming = false;
                        }
                        else cu = CameraUpdateFactory.newLatLng(latLng);
                        mMap.animateCamera(cu);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener onConnectTimeout = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onRoomBroadcast = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            try {
                roomID = jsonObject.getString("roomID");
                JSONObject jsonEmit = new JSONObject();
                jsonEmit.put("id",userID);
                jsonEmit.put("room",roomID);
                mSocket.emit(Config.EVENT_CONNECT_GPS,jsonEmit);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void initSocket() {
        try {
            mSocket = IO.socket(Config.SERVER_2);
//            mSocket = IO.socket(Config.SERVER_1);
            mSocket.on(Socket.EVENT_CONNECT,onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT,onConnectTimeout);
            mSocket.on(Socket.EVENT_CONNECT_ERROR,onConnectError);
            mSocket.on(Config.EVENT_ID_BROADCAST,onIDBroadcast);
            mSocket.on(Config.EVENT_ROOM_BROADCAST,onRoomBroadcast);
            mSocket.on(Config.EVENT_GPS_DATA,onGPSData);
            mSocket.on(Config.EVENT_DATA_MQTT,onMQTTData);
            mSocket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        breakSocket();
    }

    private void breakSocket(){
        if(mSocket!=null) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id",userID);
                jsonObject.put("room",roomID);
                mSocket.emit(Config.EVENT_DISCONNECT_GPS,jsonObject);
                mSocket.off(Socket.EVENT_CONNECT, onConnect);
                mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
                mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout);
                mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
                mSocket.off(Config.EVENT_ID_BROADCAST, onIDBroadcast);
                mSocket.off(Config.EVENT_ROOM_BROADCAST, onRoomBroadcast);
                mSocket.off(Config.EVENT_DATA_MQTT, onMQTTData);
                mSocket.off(Config.EVENT_GPS_DATA, onGPSData);
                mSocket.off();
                mSocket.disconnect();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
