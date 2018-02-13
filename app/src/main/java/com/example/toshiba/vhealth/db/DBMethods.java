package com.example.toshiba.vhealth.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TOSHIBA on 31/01/2018.
 */

public class DBMethods {
    Context c;
    DataHelper dbHelper;
    public void DBMethods (Context c){
        this.c=c;
         dbHelper = new DataHelper(this.c);
    }

    public void deleteIntervalDB(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String nowStr = dateFormat.format(date);

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar interv= Calendar.getInstance();
        interv.setTime(date);
        interv.add(Calendar.DAY_OF_WEEK_IN_MONTH, -7);
        Date intDate = interv.getTime();
        String intStr = sdf.format(intDate);

//        db.execSQL("delete from car where created_at= BETWEEN '2011-11-08 00:00:00' AND '2011-11-10 00:00:00'");
        db.execSQL("delete from car where created_at= BETWEEN '"+intStr+"' AND '1980-12-31 00:00:00'");
    }
    public void deleteDB(String id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("delete from car where id= '"+id+"'");
    }
    public String[] selectAllDB(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM car ",null);
        String[] daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        return daftar;
    }
    public void insertDB(String id, String lat, String lon, String alt, String gps_sp, String load, String c_temp, String maf, String v_sp, String iat, String imp, String tgl){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String []tglStr = tgl.split(", ");
        //SimpleDateFormat newformat = new SimpleDateFormat("dd-MM-yyyy");

//        Date d = Calendar.getInstance().getTime(); // Current time
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Set your date format
        String currentData = sdf.format(tgl); // Get Date String according to date format

        db.execSQL("insert into car(id , " +
                "lat , lon , " +
                "alt , " +
                "gps_speed ," +
                "load ," +
                "coolant ," +
                "maf ," +
                "rpm ," +
                "v_speed ," +
                "iat," +
                "imp, created_at) values('"+id+"', '"+lat+"', '"+lon+"' ,'"+ alt+"', '"+ gps_sp+"', '"+load+"', '"+c_temp+"', '"+maf+"',  '" +v_sp +"',  '"+iat+"',  '"+imp +"', '"+currentData+"' )");
    }
}
