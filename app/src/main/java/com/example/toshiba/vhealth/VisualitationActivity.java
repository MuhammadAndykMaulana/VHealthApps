package com.example.toshiba.vhealth;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by TOSHIBA on 05/08/2017.
 */

public class VisualitationActivity extends MainActivity {
    private VisualitationActivity mVisualitation;
    private Socket mSocket;
    private String userID;
    private String roomID;
    private Map<String,LineChart> lineChartMap;

    int colors[] = {Color.RED,Color.YELLOW,Color.WHITE,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.LTGRAY,Color.GRAY,Color.DKGRAY
    , Color.MAGENTA};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualitation);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        initSocket();

        initChart();

    }

    private void initChart() {
        lineChartMap = new HashMap<>();
        LineChart chart_CLV = (LineChart) findViewById(R.id.chart_clv);
        LineChart chart_CT = (LineChart) findViewById(R.id.chart_ct);
        LineChart chart_IMP  = (LineChart) findViewById(R.id.chart_imp);
        LineChart chart_ERPM = (LineChart) findViewById(R.id.chart_eRPM);
        LineChart chart_VS = (LineChart) findViewById(R.id.chart_vs);
        LineChart chart_TA = (LineChart) findViewById(R.id.chart_ta);
        LineChart chart_IAT = (LineChart) findViewById(R.id.chart_iat);
        LineChart chart_MAF = (LineChart) findViewById(R.id.chart_MAF);
        LineChart chart_TP = (LineChart) findViewById(R.id.chart_TP);
        LineChart chart_TSES = (LineChart) findViewById(R.id.chart_TSES);
        LineChart chart_ERMON = (LineChart) findViewById(R.id.chart_ERMON);

        lineChartMap.put("CLV",chart_CLV);
        lineChartMap.put("CT",chart_CT);
        lineChartMap.put("IMP",chart_IMP);
        lineChartMap.put("ERPM",chart_ERPM);
        lineChartMap.put("VS",chart_VS);
        lineChartMap.put("TA",chart_TA);
        lineChartMap.put("IAT",chart_IAT);
        lineChartMap.put("MAF",chart_MAF);
        lineChartMap.put("TP",chart_TP);
        lineChartMap.put("TSES",chart_TSES);
        lineChartMap.put("ERMON",chart_ERMON);

        /*
        lineChartMap.put("CalcLoadValue",chart_CLV);
        lineChartMap.put("CoolantTemp",chart_CT);
        lineChartMap.put("IntakeManifoldPressure",chart_IMP);
        lineChartMap.put("EngineRPM",chart_ERPM);
        lineChartMap.put("VehicleSpeed",chart_VS);
        lineChartMap.put("TimingAdvance",chart_TA);
        lineChartMap.put("IntakeAirTemp",chart_IAT);
        lineChartMap.put("AirFlowRate(MAF)",chart_MAF);
        lineChartMap.put("ThrottlePosition",chart_TP);
        lineChartMap.put("TimeSinceEngineStart",chart_TSES);
        lineChartMap.put("EngineRunwithMillON",chart_ERMON);
        */
    }

    private void updateChartDate(JSONArray jsonArray){
        Log.d("VHealth","size : "+jsonArray.length());
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String key = jsonObject.getString("name");
                final String value = jsonObject.getString("value");
                Log.d("VHealth","Response name : "+key);
                final LineChart chart = lineChartMap.get(key);
                if(chart.getLineData()==null){
                    List<Entry> entries = new ArrayList<>();
                    entries.add(new Entry((float)(i+1),Float.valueOf(value)));
                    LineDataSet dataSet = new LineDataSet(entries, key);
                    dataSet.setColor(colors[i]);
                    dataSet.setValueTextColor(colors[i]);

                    final LineData lineData = new LineData(dataSet);
                    final LineChart finalChart = chart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finalChart.setData(lineData);
                            finalChart.invalidate();
                        }
                    });
                }
                else {
                    final int finalI = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            float x = chart.getData().getDataSetByIndex(0).getEntryCount();
                            float y = Float.valueOf(value);
                            addEntry(chart,x,y);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void addEntry(LineChart mChart, float x, float y) {

        LineData data = mChart.getData();

        if (data != null) {

            data.addEntry(new Entry(x, y),0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            mChart.notifyDataSetChanged();

            // limit the number of visible entries
            mChart.setVisibleXRangeMaximum(120);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // mChart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
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

    private Emitter.Listener onMQTTData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

//            Toast.makeText(VisualitationActivity.this,"Response : "+jsonObject,Toast.LENGTH_SHORT).show();
            JSONObject payload = null;
            JSONArray sensorArray = null;
            //Log.d("TrackActivity","Response payload GPS : "+(JSONObject)args[0]);
            try{
            payload = new JSONObject(((JSONObject)args[0]).getString("payload"));
                sensorArray = new JSONArray(payload.getString("OBDSupported"));
                updateChartDate(sensorArray);
                Log.d("Vhealth","Response : "+sensorArray.getJSONObject(0));
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void initSocket() {
        try {
            mSocket = IO.socket(Config.SERVER_2);
            mSocket.on(Socket.EVENT_CONNECT,onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT,onConnectTimeout);
            mSocket.on(Socket.EVENT_CONNECT_ERROR,onConnectError);
            mSocket.on(Config.EVENT_ID_BROADCAST,onIDBroadcast);
            mSocket.on(Config.EVENT_ROOM_BROADCAST,onRoomBroadcast);
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
                mSocket.off(Socket.EVENT_CONNECT, onConnect);
                mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
                mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout);
                mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
                mSocket.off(Config.EVENT_ID_BROADCAST, onIDBroadcast);
                mSocket.off(Config.EVENT_ROOM_BROADCAST, onRoomBroadcast);
                mSocket.off(Config.EVENT_DATA_MQTT, onMQTTData);
                mSocket.off();
                mSocket.disconnect();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
