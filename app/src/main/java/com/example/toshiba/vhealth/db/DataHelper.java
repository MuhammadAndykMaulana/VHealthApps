package com.example.toshiba.vhealth.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table car(" +
                        "id text primary key, " +
                        "lat text null, lon text null, " +
                        "alt text null, " +
                        "gps_speed text null," +
                        "load text null," +
                        "coolant text null," +
                "maf text null," +
                "rpm text null," +
                "v_speed text null," +
                "iat text null," +
                "imp text null, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }

}