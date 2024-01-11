package com.alpermelkeli.pumpcoin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private WebSocketClient webSocketClient;
    TextView percentText;
    TextView timeText;

    private AppCompatButton minute1;
    private AppCompatButton minute3;
    private AppCompatButton minute5;
    private AppCompatButton minute15;
    private AppCompatButton minute30;

    private AppCompatButton hour1;
    private AppCompatButton hour2;
    private AppCompatButton hour4;
    private AppCompatButton hour6;
    private AppCompatButton hour12;

    private  AppCompatButton stopButton;

    private EditText percentEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initializeViews();

        minute1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("1m mumlarda");
                startForegroundService("1m");
            }
        });
        minute3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("3m mumlarda");
                startForegroundService("3m");

            }
        });
        minute5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("5m mumlarda");
                startForegroundService("5m");
            }
        });
        minute15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("15m mumlarda");
                startForegroundService("15m");
            }
        });
        minute30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("30m mumlarda");
                startForegroundService("30m");

            }
        });
        hour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("1h mumlarda");
                startForegroundService("1h");
            }
        });
        hour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("2h mumlarda");
                startForegroundService("2h");

            }
        });
        hour4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("4h mumlarda");
                startForegroundService("4h");

            }
        });
        hour6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("6h mumlarda");
                startForegroundService("6h");

            }
        });
        hour12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("12h mumlarda");
                startForegroundService("12h");
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopForegroundService();
            }
        });
    }

    private void startForegroundService(String interval){

        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        serviceIntent.putExtra("limit", percentEditText.getText().toString());
        if (percentEditText.getText().toString()==null){
            Toast.makeText(this,"Yüzdelik boş geçilemez",Toast.LENGTH_LONG).show();
            return;
        }
        percentText.setText(percentEditText.getText().toString());
        serviceIntent.putExtra("interval", interval);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        }


    }
    private void stopForegroundService() {
        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        stopService(serviceIntent);
        finish();
    }


    protected void onDestroy() {
        super.onDestroy();
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    private void initializeViews(){
        minute1 = findViewById(R.id.minute1);
        minute3 = findViewById(R.id.minute3);
        minute5 = findViewById(R.id.minute5);
        minute15 = findViewById(R.id.minute15);
        minute30 = findViewById(R.id.minute30);

        hour1 = findViewById(R.id.hour1);
        hour2 = findViewById(R.id.hour2);
        hour4 = findViewById(R.id.hour4);
        hour6 = findViewById(R.id.hour6);
        hour12 = findViewById(R.id.hour12);

        percentText = findViewById(R.id.percentText);
        timeText = findViewById(R.id.timeText);
        stopButton = findViewById(R.id.stopButton);
        percentEditText = findViewById(R.id.percentEditText);

    }









}






