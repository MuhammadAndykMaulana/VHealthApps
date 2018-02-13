package com.example.toshiba.vhealth;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.toshiba.vhealth.model.ChartViewModel;
import com.example.toshiba.vhealth.model.GPSResponse;
import com.example.toshiba.vhealth.model.OBDSupported;
//import com.example.toshiba.vhealth.model.ObdResponse;
import com.example.toshiba.vhealth.model.VisualitationResponse;
import com.example.toshiba.vhealth.model.payloadResponse;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static android.R.attr.data;
import static android.R.attr.key;

/**
 * Created by TOSHIBA on 05/08/2017.
 */

public class VisualitationActivity extends MainActivity {
    @BindView(R.id.clv)
    TextView clv;
    @BindView(R.id.chart_clv)
    LineChart chartClv;
    @BindView(R.id.ct)
    TextView ct;
    @BindView(R.id.chart_ct)
    LineChart chartCt;
    @BindView(R.id.imp)
    TextView imp;
    @BindView(R.id.chart_imp)
    LineChart chartImp;
    @BindView(R.id.rpm)
    TextView rpm;
    @BindView(R.id.chart_eRPM)
    LineChart chartERPM;
    @BindView(R.id.speed)
    TextView speed;
    @BindView(R.id.chart_vs)
    LineChart chartVs;
    //@BindView(R.id.ta)
    //TextView ta;
//    @BindView(R.id.chart_ta)
//    LineChart chartTa;
    @BindView(R.id.iat)
    TextView iat;
    @BindView(R.id.chart_iat)
    LineChart chartIat;
    @BindView(R.id.maf)
    TextView maf;
    @BindView(R.id.chart_MAF)
    LineChart chartMAF;
    //@BindView(R.id.tp)
    //TextView tp;
//    @BindView(R.id.chart_TP)
//    LineChart chartTP;

    @BindView(R.id.txt_name1)
    TextView txtName1;
    @BindView(R.id.txt_colon1)
    TextView txtColon1;
    @BindView(R.id.txt_value1)
    TextView txtValue1;
    @BindView(R.id.txt_name2)
    TextView txtName2;
    @BindView(R.id.txt_colon2)
    TextView txtColon2;
    @BindView(R.id.txt_value2)
    TextView txtValue2;
    @BindView(R.id.txt_name3)
    TextView txtName3;
    @BindView(R.id.txt_colon3)
    TextView txtColon3;
    @BindView(R.id.txt_value3)
    TextView txtValue3;
    @BindView(R.id.txt_name4)
    TextView txtName4;
    @BindView(R.id.txt_colon4)
    TextView txtColon4;
    @BindView(R.id.txt_value4)
    TextView txtValue4;
    @BindView(R.id.txt_name5)
    TextView txtName5;
    @BindView(R.id.txt_colon5)
    TextView txtColon5;
    @BindView(R.id.txt_value5)
    TextView txtValue5;
    @BindView(R.id.txt_name6)
    TextView txtName6;
    @BindView(R.id.txt_colon6)
    TextView txtColon6;
    @BindView(R.id.txt_value6)
    TextView txtValue6;
    @BindView(R.id.txt_name7)
    TextView txtName7;
    @BindView(R.id.txt_colon7)
    TextView txtColon7;
    @BindView(R.id.txt_value7)
    TextView txtValue7;
    @BindView(R.id.txt_name8)
    TextView txtName8;
    @BindView(R.id.txt_colon8)
    TextView txtColon8;
    @BindView(R.id.txt_value8)
    TextView txtValue8;
    @BindView(R.id.txt_name9)
    TextView txtName9;
    @BindView(R.id.txt_colon9)
    TextView txtColon9;
    @BindView(R.id.txt_value9)
    TextView txtValue9;
    @BindView(R.id.txt_name10)
    TextView txtName10;
    @BindView(R.id.txt_colon10)
    TextView txtColon10;
    @BindView(R.id.txt_value10)
    TextView txtValue10;
    @BindView(R.id.txt_name11)
    TextView txtName11;
    @BindView(R.id.txt_colon11)
    TextView txtColon11;
    @BindView(R.id.txt_value11)
    TextView txtValue11;


    private VisualitationActivity mVisualitation;
    private Socket mSocket;
    private String userID;
    private String roomID;
    private Map<String,LineChart> lineChartMap;
    private List<ChartViewModel> viewModels;
    private List<OBDSupported> OBDSupportedList;

    int colors[] = {Color.RED,Color.YELLOW,Color.WHITE,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.LTGRAY,Color.GRAY,Color.DKGRAY
    , Color.MAGENTA};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualitation);
        ButterKnife.bind(this);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        initSocket();
////        Initialize();
        initChart();
    }
//    private void Initialize(){
//        viewModels = new ArrayList<>();
//    }
//
    private void initChart() {
        lineChartMap = new HashMap<>();
        LineChart chart_CLV = (LineChart) findViewById(R.id.chart_clv);
        LineChart chart_CT = (LineChart) findViewById(R.id.chart_ct);
        LineChart chart_IMP  = (LineChart) findViewById(R.id.chart_imp);
        LineChart chart_ERPM = (LineChart) findViewById(R.id.chart_eRPM);
        LineChart chart_VS = (LineChart) findViewById(R.id.chart_vs);
//        LineChart chart_TA = (LineChart) findViewById(R.id.chart_ta);
        LineChart chart_IAT = (LineChart) findViewById(R.id.chart_iat);
        LineChart chart_MAF = (LineChart) findViewById(R.id.chart_MAF);
//        LineChart chart_TP = (LineChart) findViewById(R.id.chart_TP);
        //LineChart chart_TSES = (LineChart) findViewById(R.id.chart_TSES);
        //LineChart chart_ERMON = (LineChart) findViewById(R.id.chart_ERMON);

        lineChartMap.put("CLV",chart_CLV);
        lineChartMap.put("CT",chart_CT);
        lineChartMap.put("IMP",chart_IMP);
        lineChartMap.put("ERPM",chart_ERPM);
        lineChartMap.put("VS",chart_VS);
        lineChartMap.put("IAT",chart_IAT);
        lineChartMap.put("MAF",chart_MAF);

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
                Log.d("VHealth","Response jsonObject : "+jsonObject);
                String key = jsonObject.getString("name");
                Log.d("VHealth","Response name : "+key);
                final String value = jsonObject.getString("value");
                Log.d("VHealth","Response value : "+value);
                switch (key) {
                    case "Calc Load Value":
//                        Log.d("VHealth","VS: "+jsonObject.getString("value"));
                        if (chartClv.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartClv;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartClv.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartClv, x, y);
                                }
                            });
                        }
                        break;
                    case "Air Flow Rate (MAF)":
                        if (chartMAF.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartMAF;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartMAF.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartMAF, x, y);
                                }
                            });
                        }
                        break;
                    case "Coolant Temp":
                        if (chartCt.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartCt;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartCt.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartCt, x, y);
                                }
                            });
                        }
                        break;

                    case "Vehicle Speed":
                        if (chartVs.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartVs;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartVs.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartVs, x, y);
                                }
                            });
                        }
                        break;
                    case "Engine RPM":
                        if (chartERPM.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartERPM;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartERPM.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartERPM, x, y);
                                }
                            });
                        }
                        break;
                    case "Intake Air Temp":
                        if (chartIat.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartIat;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartIat.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartIat, x, y);
                                }
                            });
                        }
                        break;

                    case "Intake Manifold Pressure":
                        if (chartImp.getLineData() == null) {
                            List<Entry> entries = new ArrayList<>();
                            LineDataSet dataSet = new LineDataSet(entries, key);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                            entries.add(new Entry((float) (i + 1), Float.valueOf(value)));
//                        dataSet.setValueTextColors(ColorTemplate.JOYFUL_COLORS);
                            final LineData lineData = new LineData(dataSet);
                            final LineChart finalChart = chartImp;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finalChart.setData(lineData);
                                    finalChart.invalidate();
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float x = chartImp.getData().getDataSetByIndex(0).getEntryCount();
                                    float y = Float.valueOf(value);
                                    addEntry(chartImp, x, y);
                                }
                            });
                        }
                        break;
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

    public static Map<String,String> parse(JSONObject json , Map<String,String> out) throws JSONException{
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            String val = null;
            try{
                JSONObject value = json.getJSONObject(key);
                parse(value,out);
            }catch(Exception e){
                val = json.getString(key);
            }

            if(val != null){
                out.put(key,val);
            }
        }
        return out;
    }

    private Emitter.Listener onMQTTData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

//            Toast.makeText(VisualitationActivity.this,"Response : "+jsonObject,Toast.LENGTH_SHORT).show();
            //JSONObject payload = null;
            JSONArray sensorArray = null;
            Log.d("VisualitationActivity","Response payload Visualitation : "+(JSONObject)args[0]);
            Log.d("MQQT", "call: " + args[0]);
            Log.d("MQTT", "call: " + args[0].toString());
            payloadResponse response = JSON.parseObject(String.valueOf(args[0]), payloadResponse.class);
            String payloadStr = response.getPayload();
            Log.d("Payload", "payloadStr: " + payloadStr);
            VisualitationResponse visualitationResponse = JSON.parseObject(payloadStr, VisualitationResponse.class);
            //List<OBDSupported> obds = new Gson().fromJson(visualitationResponse.getOBDSupported(), OBDSupported.class);
            final OBDSupported ObdSupported = new  Gson().fromJson(visualitationResponse.getOBDSupported(), OBDSupported.class);
            Log.d("Air Flow Rate", "Air Flow Rate: " + ObdSupported.getAirFlowRateMAF());
            Log.d("Intake Air Temp", "Intake Air Temp: " + ObdSupported.getIntakeAirTemp());
//            Log.d("Payload", "payload: " + ObdSupported.toString().replace("\n","").replace("OBDSupported","").replace("=",":"));
            String gpsStr = visualitationResponse.getGps();
            Log.d("GPS STR", "call: " + gpsStr);
//            final GPSResponse gps = JSON.parseObject(gpsStr, GPSResponse.class);
//            Log.d("LAT LON", "call: " + gps.getLat() + "," + gps.getLon());

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i("RUNNING ON UI THREAD", "run: ");
//                    Log.d ("Ready", "READY gan");
//                    txtName1.setText("GPS Latitude");
//                    txtValue1.setText(String.valueOf(gps.getLat()));
//                    txtName2.setText("GPS Longitude");
//                    txtValue2.setText(String.valueOf(gps.getLon()));
//                    txtName3.setText("GPS Altitude");
//                    txtValue3.setText(String.valueOf(gps.getAlt())+" m");
//                    txtName4.setText("GPS Speed");
//                    txtValue4.setText(String.valueOf(gps.getSpeed()) + " km/h");
//                    txtName5.setText("Engine Load");
//                    txtValue5.setText(String.valueOf(ObdSupported.getCalcLoadValue()) + " %");
//                    txtName6.setText("Coolant Temp");
//                    txtValue6.setText(String.valueOf(ObdSupported.getCoolantTemp()) + " C");
//                    txtName7.setText("Air Flow Rate (MAF)");
//                    txtValue7.setText(String.valueOf(ObdSupported.getAirFlowRateMAF()) + " g/s");
//                    txtName8.setText("Engine RPM");
//                    txtValue8.setText(String.valueOf(ObdSupported.getEngineRPM()) + " rpm");
//                    txtName9.setText("Vehicle Speed");
//                    txtValue9.setText(String.valueOf(ObdSupported.getVehicleSpeed()) + " km/h");
//                    txtName10.setText("Intake Air Temp");
//                    txtValue10.setText(String.valueOf(ObdSupported.getIntakeAirTemp()) + " C");
//                    txtName11.setText("Intake Manifold Pressure");
//                    txtValue11.setText(String.valueOf(ObdSupported.getIntakeManifoldPressure()) + " kPa");
//                }
//            });

            if (payloadStr.length() != 0) {
                //final OBDSupported ObdSupported = new Gson().fromJson(visualitationResponse.getOBDSupported(), OBDSupported.class);
                Log.d("Payload", "payload: " + ObdSupported.toString());
                Log.d("Payload", "Air Flow Rate: " + ObdSupported.getAirFlowRateMAF());
                Log.d("Payload", "Intake Air Temp: " + ObdSupported.getIntakeAirTemp());
                Log.d("Payload", "Load: "+ObdSupported.getCalcLoadValue());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("RUNNING ON UI THREAD", "run: ");
//                        Log.d ("Masuk", "Masuk gan");
//                        txtName1.setText("GPS Latitude");
//                        txtValue1.setText("null");
//                        txtName2.setText("GPS Longitude");
//                        txtValue2.setText("null");
//                        txtName3.setText("GPS Altitude");
//                        txtValue3.setText("null");
//                        txtName4.setText("GPS Speed");
//                        txtValue4.setText("null");
//                    }
//                });

                if (gpsStr.length() != 0) {
                    Log.d ("Masuk", "Masuk gan");
                    final GPSResponse gps = JSON.parseObject(gpsStr, GPSResponse.class);
                    Log.d("LAT LON", "call: " + gps.getLat() + "," + gps.getLon());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("RUNNING ON UI THREAD", "run: ");
                            Log.d ("Ready", "READY gan");
                            txtName1.setText("GPS Latitude");
                            txtValue1.setText(String.valueOf(gps.getLat()));
                            txtName2.setText("GPS Longitude");
                            txtValue2.setText(String.valueOf(gps.getLon()));
                            txtName3.setText("GPS Altitude");
                            txtValue3.setText(String.valueOf(gps.getAlt())+" m");
                            txtName4.setText("GPS Speed");
                            txtValue4.setText(String.valueOf(gps.getSpeed()) + " km/h");
                            txtName5.setText("Engine Load");
                            txtValue5.setText(String.valueOf(ObdSupported.getCalcLoadValue()) + " %");
                            txtName6.setText("Coolant Temp");
                            txtValue6.setText(String.valueOf(ObdSupported.getCoolantTemp()) + " C");
                            txtName7.setText("Air Flow Rate (MAF)");
                            txtValue7.setText(String.valueOf(ObdSupported.getAirFlowRateMAF()) + " g/s");
                            txtName8.setText("Engine RPM");
                            txtValue8.setText(String.valueOf(ObdSupported.getEngineRPM()) + " rpm");
                            txtName9.setText("Vehicle Speed");
                            txtValue9.setText(String.valueOf(ObdSupported.getVehicleSpeed()) + " km/h");
                            txtName10.setText("Intake Air Temp");
                            txtValue10.setText(String.valueOf(ObdSupported.getIntakeAirTemp()) + " C");
                            txtName11.setText("Intake Manifold Pressure");
                            txtValue11.setText(String.valueOf(ObdSupported.getIntakeManifoldPressure()) + " kPa");
                        }
                    });
                }
                try {
//                    JSONObject  oBDSupported= new JSONObject(ObdSupported.toString().replace("\n","").replace("OBDSupported","").replace("=",":"));
//                    Log.d("Yosh",oBDSupported.toString());
                    JSONObject jsonObject = new JSONObject(response.getPayload());
                    String OBD = jsonObject.getString("OBDSupported");
                    JSONObject jsonOBD = new JSONObject(OBD);
                    JSONArray jsonArray = new JSONArray();
                    Iterator<String> keys = jsonOBD.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        String val = null;
                        try {
                            String name = key;
                            String value = jsonOBD.getString(key);
                            JSONObject jsonObject1 = new JSONObject("{name:'"+name+"',value:'"+value+"'}");
                            jsonArray.put(jsonObject1);//List<OBDSupported> obds = JSON.parseArray(String.valueOf(jsonObject1), OBDSupported.class);
                            Log.d("HASILNYA GAN",jsonObject1.toString()); //Log.d("obds",obds.toString());
                            Log.d("name",jsonObject1.getString("name"));
                            Log.d("value",jsonObject1.getString("value"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    updateChartDate(jsonArray);

//                    sensorArray = new JSONArray(jsonObject.getString("OBDSupported"));
//                    updateChartDate(sensorArray);
                    Log.d("HOI",jsonOBD.toString());
                } catch (JSONException e) {
                    //                Log.d("Ciamik","error ndik");
                    //                e.printStackTrace();
                    Log.e("ERROR JSON NDYK",e.toString());
                }
            }
        }
    };
//    private void updateChartValue(List<OBDSupported> obds) {
//        int i = 0;
//        for (OBDSupported obd : obds) {
//            if (!obd.toString().equalsIgnoreCase("NODATA") && !obd.toString().equalsIgnoreCase("#")){
//                float value = Float.parseFloat(obd.toString());
//                viewModels.get(i).getValues().add(value);
//                i++;
//            }
//        }
//    }
//    private void updateChart(List<OBDSupported> obdSupportedList) {
//        for (OBDSupported obd : obdSupportedList) {
//            ChartViewModel viewModel = new ChartViewModel(obd.toString());
//            if (!obd.toString().equalsIgnoreCase("NODATA") && !obd.toString().equalsIgnoreCase("#")) {
//                float value = Float.parseFloat(obd.toString());
//                List<Float> values = new ArrayList<>();
//                values.add(value);
//                viewModel.setValues(values);
//                viewModels.add(viewModel);
//            }
//        }
//    }

    /*
    private Emitter.Listener onMQTTData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            //Toast.makeText(VisualitationActivity.this,"Response : "+jsonObject,Toast.LENGTH_SHORT).show();
            JSONObject payload = null;
            JSONArray sensorArray = null;
            Log.d("MQQT", "call: " + args[0]);
            Log.d("MQTT", "call: " + args[0].toString());
            payloadResponse response = JSON.parseObject(String.valueOf(args[0]), payloadResponse.class);
            String payloadStr = response.getPayload();
            Log.d("Payload", "payloadStr: " + payloadStr);
            VisualitationResponse visualitationResponse = JSON.parseObject(payloadStr, VisualitationResponse.class);


            //VisualitationResponse visualitationResponse1 = new Gson().fromJson(payloadStr, VisualitationResponse.class);
            OBDSupported ObdSupported = new  Gson().fromJson(visualitationResponse.getOBDSupported(), OBDSupported.class);
            Log.d("Payload", "Air Flow Rate: " + ObdSupported.getAirFlowRateMAF());
            Log.d("Payload", "payload: " + ObdSupported.toString());
            Log.d("Payload", "Intake Air Temp: " + ObdSupported.getIntakeAirTemp());

            //TypeToken<List<OBDSupported>> token = new TypeToken<List<OBDSupported>>() {};
            //List<OBDSupported> OBDSupporteds = gson.fromJson(data, token.getType());

            //OBDSupported obdSupported = new Gson().fromJson("string json", OBDSupported.class);
            //List<OBDSupported> obds = JSON.parseArray(visualitationResponse.getOBDSupported(), OBDSupported.class);
            String gpsStr = visualitationResponse.getGps();
            Log.d("GPS STR", "call: " + gpsStr);

            if (payloadStr.length() != 0) {
                final OBDSupported ObdSupported = new Gson().fromJson(visualitationResponse.getOBDSupported(), OBDSupported.class);
                Log.d("Payload", "payload: " + ObdSupported.toString());
                Log.d("Payload", "Air Flow Rate: " + ObdSupported.getAirFlowRateMAF());
                Log.d("Payload", "Intake Air Temp: " + ObdSupported.getIntakeAirTemp());
                //TypeToken <List<OBDSupported>> token = new TypeToken<List<OBDSupported>>() {};
                //List<OBDSupported> obdSupportedList = new Gson().fromJson(getOBDSupported(), OBDSupportedList);

                //updateChartDate(ObdSupported);
                VisualitationActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("RUNNING ON UI THREAD", "run: ");
                        txtName5.setText("Engine Load");
                        txtValue5.setText(ObdSupported.getCalcLoadValue() + " %");
                        txtName6.setText("Coolant Temp");
                        txtValue6.setText(ObdSupported.getCoolantTemp() + " C");
                        txtName7.setText("Air Flow Rate (MAF)");
                        txtValue7.setText(ObdSupported.getAirFlowRateMAF() + " g/s");
                        txtName8.setText("Engine RPM");
                        txtValue8.setText(ObdSupported.getEngineRPM() + " rpm");
                        txtName9.setText("Vehicle Speed");
                        txtValue9.setText(ObdSupported.getVehicleSpeed() + " km/h");
                        txtName10.setText("Intake Air Temp");
                        txtValue10.setText(ObdSupported.getIntakeAirTemp() + " C");
                        txtName11.setText("Intake Manifold Pressure");
                        txtValue11.setText(ObdSupported.getIntakeManifoldPressure() + " kPa");

                    }
                });
                if (gpsStr.length() != 0) {
                    final GPSResponse gps = JSON.parseObject(gpsStr, GPSResponse.class);
                    Log.d("LAT LON", "call: " + gps.getLat() + "," + gps.getLon());
                    VisualitationActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("RUNNING ON UI THREAD", "run: ");
                            txtName1.setText("GPS Latitude");
                            txtValue1.setText(String.valueOf(gps.getLat()));
                            txtName2.setText("GPS Longitude");
                            txtValue2.setText(String.valueOf(gps.getLon()));
                            txtName3.setText("GPS Altitude");
                            txtValue3.setText(String.valueOf(gps.getAlt()));
                            txtName4.setText("GPS Speed");
                            txtValue4.setText(String.valueOf(gps.getSpeed()) + " km/h");
                        }
                    });
                }
            }

            //Log.d("OBD Supported", "call:"+ obds);
            //Log.d("TrackActivity","Response payload GPS : "+(JSONObject)args[0]);

            try{
                //ObdResponse obdResponse = new Gson().fromJson("string json", ObdResponse.class);

            payload = new JSONObject(((JSONObject)args[0]).getString("payload"));
                ObdResponse sensorArray = new Gson().fromJson("string json", ObdResponse.class);
                updateChartDate(sensorArray);
                Log.d("Vhealth","Response : "+sensorArray.getJSONObject(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };*/

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

