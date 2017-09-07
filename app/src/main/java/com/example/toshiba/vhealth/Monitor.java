package com.example.toshiba.vhealth;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eyro.mesosfer.FindCallback;
import com.eyro.mesosfer.GetCallback;
import com.eyro.mesosfer.MesosferData;
import com.eyro.mesosfer.MesosferException;
import com.eyro.mesosfer.MesosferObject;
import com.eyro.mesosfer.MesosferQuery;

import java.util.List;

public class Monitor extends AppCompatActivity {
    private TextView ph;
    private TextView deo;
    private TextView sal;
    private TextView temp;
    TextView lat,longi;
    private Handler handler = new Handler();
    int a=0;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
      /* do what you need to do */
            retrieve();
      /* and here comes the "trick" */
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        ph = (TextView) findViewById(R.id.nilaiph);
        deo = (TextView) findViewById(R.id.nilaido);
        sal = (TextView) findViewById(R.id.nilaisal);
        temp = (TextView) findViewById(R.id.nilaitemp);
        lat = (TextView) findViewById(R.id.lat);
        longi = (TextView) findViewById(R.id.longi);

        handler.postDelayed(runnable, 1000);
        MesosferQuery<MesosferData> query = MesosferData.getQuery("Log");
        query.findAsync(new FindCallback<MesosferData>() {
            @Override
            public void done(List list, MesosferException e) {
                if (e != null) {
                    // exception happen, handle the message
                    return;
                }
            }
        });
        retrieve();
    }

    public void retrieve(){
        // create data from existing objecId
        MesosferData data = MesosferData.createWithObjectId("t2S6fi3Qy0");
        // fetching the data
        data.fetchAsync(new GetCallback<MesosferData>() {
            @Override
            public void done(MesosferData data, MesosferException e) {
                // check if there is an exception happen
                if (e != null) {
                    // handle the exception
                    return;
                }
                MesosferObject object = data.getData();
                ph.setText(object.optString("pH"));
                deo.setText(object.optString("do"));
                sal.setText(object.optString("sal"));
                temp.setText(object.optString("temp"));
                lat.setText(object.optString("lat"));
                longi.setText(object.optString("long"));
                // fetch data succeeded
            }
        });
    }
}