package com.example.toshiba.vhealth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.eyro.mesosfer.LogOutCallback;
import com.eyro.mesosfer.Mesosfer;
import com.eyro.mesosfer.MesosferException;
import com.eyro.mesosfer.MesosferUser;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog loading;
    private AlertDialog dialog;
    private ImageView imageVD;
    private ImageView imageVN;
//    private ImageView imageES;
//    private ImageView imageI;
    private static final int REQUEST_IMAGE_CAPTURE = 54;
    private static final int REQUEST_OPEN_GALLERY = 30;
    private ImageView camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();

        loading = new ProgressDialog(this);
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
    }

    private void initView() {
        imageVD = (ImageView) findViewById(R.id.imageVD);
        imageVN = (ImageView) findViewById(R.id.imageVN);
        //imageES = (ImageView) findViewById(R.id.imageES);
        //imageI = (ImageView) findViewById(R.id.imageI);
        //camera = (ImageView) findViewById(R.id.camera);
        imageVD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"Vehicle Diagnostic",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,VisualitationActivity.class));
            }
        });

        imageVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Vehicle Navigation",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,TrackActivity.class));
            }
        });
/*
        imageES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Engine Start",Toast.LENGTH_SHORT).show();
            }
        });

        imageI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Interlock",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void handleLogout(View view) {
        loading.setMessage("Logging out...");
        loading.show();
        MesosferUser.logOutAsync(new LogOutCallback() {
            @Override
            public void done(MesosferException e) {
                loading.dismiss();

                if (e != null) {
                    // setup alert dialog builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setNegativeButton(android.R.string.ok, null);
                    builder.setTitle("Log Out Error");
                    builder.setMessage(
                            String.format(Locale.getDefault(), "Error code: %d\nDescription: %s",
                                    e.getCode(), e.getMessage())
                    );
                    dialog = builder.show();
                }

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
        public void handleUserList(View view) {
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);
        }

        public void handleProfile(View view) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }

        public void handleData(View view) {
            Intent intent = new Intent(this, DataActivity.class);
            startActivity(intent);
        }

        public void monitor(View view) {
            Intent intent = new Intent(this, Monitor.class);
            startActivity(intent);
        }

        public void control(View view) {
            Intent intent = new Intent(this, Control.class);
            startActivity(intent);
        }
    */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.user_list:
                Intent intent1 = new Intent(this, UserListActivity.class);
                startActivity(intent1);
                break;

            case R.id.dummy:
                Intent intent2 = new Intent(this, VisualitationActivity.class);
                startActivity(intent2);
                break;

            case R.id.nav_camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.nav_gallery:
                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(1)
                        .singlePhoto(true)
                        .hintOfPick("Pilih gambar")
                        //.filterMimeTypes(new String[]{"image/jpeg"})
                        .build();
                GalleryActivity.openActivity(MainActivity.this,REQUEST_OPEN_GALLERY,config);
                break;
            case R.id.logout:
                loading.setMessage("Logging out....");
                loading.setCancelable(false);
                loading.show();
                MesosferUser.logOutAsync(new LogOutCallback() {
                    @Override
                    public void done(MesosferException e) {
                        loading.dismiss();
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            camera.setImageBitmap(imageBitmap);
        }
        else if(resultCode==RESULT_OK && requestCode==REQUEST_OPEN_GALLERY){
            String photo = ((List<String>)data.getSerializableExtra(GalleryActivity.PHOTOS)).get(0);
            Bitmap bitmap = BitmapFactory.decodeFile(photo);
            camera.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
