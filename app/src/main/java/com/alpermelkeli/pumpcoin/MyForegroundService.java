package com.alpermelkeli.pumpcoin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyForegroundService extends Service {
    private WebSocketClient webSocketClient;
    String interval;
    double limit;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            // Get the extras from the Intent
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("interval")) {
                interval = extras.getString("interval");
                limit = Double.parseDouble(extras.getString("limit"));
            }
        }
        /*
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            Log.e("Service", "Service is running...");
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
        */
        startWebSocketConnection();
        final String CHANNELID = "Foreground Service ID";
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    CHANNELID,
                    CHANNELID,
                    NotificationManager.IMPORTANCE_LOW
            );
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
        Notification.Builder notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this, CHANNELID)
                    .setContentText("Service is running -> " + interval + " " + limit +"%")
                    .setContentTitle("Service enabled")
                    .setSmallIcon(R.drawable.ic_launcher_background);
        }

        startForeground(1001, notification.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




    private void startWebSocketConnection() {
        String[] tradingPairs = {
                "btcusdt","ethusdt","bnbusdt","neousdt","ltcusdt","qtumusdt","adausdt","xrpusdt","eosusdt","tusdusdt","iotausdt","xlmusdt","ontusdt","trxusdt","etcusdt","icxusdt","nulsusdt","vetusdt","usdcusdt","linkusdt","wavesusdt","ongusdt","hotusdt","zilusdt","zrxusdt","fetusdt","batusdt","xmrusdt","zecusdt","iostusdt","celrusdt","dashusdt","omgusdt","thetausdt","enjusdt","maticusdt","atomusdt","tfuelusdt","oneusdt","ftmusdt","algousdt","dogeusdt","duskusdt","ankrusdt","winusdt","cosusdt","mtlusdt","tomousdt","perlusdt","dentusdt","keyusdt","dockusdt","wanusdt","funusdt","cvcusdt","chzusdt","bandusdt","busdusdt","xtzusdt","renusdt","rvnusdt","hbarusdt","nknusdt","stxusdt","kavausdt","arpausdt","iotxusdt","rlcusdt","ctxcusdt","bchusdt","troyusdt","viteusdt","eurusdt","ognusdt","drepusdt","bullusdt","bearusdt","ethbullusdt","ethbearusdt","tctusdt","wrxusdt","btsusdt","lskusdt","bntusdt","ltousdt","stratusdt","aionusdt","mblusdt","cotiusdt","stptusdt","wtcusdt","datausdt","xzcusdt","solusdt","ctsiusdt","hiveusdt","chrusdt","btcupusdt","btcdownusdt","gxsusdt","ardrusdt","lendusdt","mdtusdt","stmxusdt","kncusdt","repusdt","lrcusdt","pntusdt","compusdt","bkrwusdt","scusdt","zenusdt","snxusdt","ethupusdt","ethdownusdt","adaupusdt","adadownusdt","linkupusdt","linkdownusdt","vthousdt","dgbusdt","gbpusdt","sxpusdt","mkrusdt","dcrusdt","storjusdt","manausdt","yfiusdt","balusdt","blzusdt","irisusdt","kmdusdt","jstusdt","antusdt","crvusdt","sandusdt","oceanusdt","nmrusdt","dotusdt","lunausdt","rsrusdt","paxgusdt","wnxmusdt","trbusdt","sushiusdt","umausdt","belusdt","wingusdt","uniusdt","oxtusdt","sunusdt","avaxusdt","flmusdt","ornusdt","utkusdt","xvsusdt","alphausdt","aaveusdt","nearusdt","filusdt","injusdt","audiousdt","ctkusdt","akrousdt","axsusdt","hardusdt","straxusdt","unfiusdt","roseusdt","avausdt","xemusdt","sklusdt","grtusdt","juvusdt","psgusdt","1inchusdt","reefusdt","ogusdt","atmusdt","asrusdt","celousdt","rifusdt","truusdt","ckbusdt","twtusdt","firousdt","litusdt","sfpusdt","dodousdt","cakeusdt","acmusdt","badgerusdt","fisusdt","omusdt","pondusdt","degousdt","aliceusdt","linausdt","perpusdt","superusdt","cfxusdt","tkousdt","pundixusdt","tlmusdt","barusdt","forthusdt","bakeusdt","burgerusdt","slpusdt","shibusdt","icpusdt","arusdt","polsusdt","mdxusdt","maskusdt","lptusdt","xvgusdt","atausdt","gtcusdt","ernusdt","klayusdt","phausdt","bondusdt","mlnusdt","dexeusdt","c98usdt","clvusdt","qntusdt","flowusdt","tvkusdt","minausdt","rayusdt","farmusdt","alpacausdt","quickusdt","mboxusdt","forusdt","requsdt","ghstusdt","waxpusdt","gnousdt","xecusdt","elfusdt","dydxusdt","idexusdt","vidtusdt","usdpusdt","galausdt","ilvusdt","yggusdt","sysusdt","dfusdt","fidausdt","frontusdt","cvpusdt","agldusdt","radusdt","betausdt","rareusdt","laziousdt","chessusdt","adxusdt","auctionusdt","darusdt","bnxusdt","movrusdt","cityusdt","ensusdt","kp3rusdt","qiusdt","portousdt","powrusdt","vgxusdt","jasmyusdt","ampusdt","plausdt","pyrusdt","rndrusdt","alcxusdt","santosusdt","mcusdt","bicousdt","fluxusdt","fxsusdt","voxelusdt","highusdt","cvxusdt","peopleusdt","ookiusdt","spellusdt","ustusdt","joeusdt","achusdt","imxusdt","glmrusdt","lokausdt","scrtusdt","api3usdt","bttcusdt","acausdt","xnousdt","woousdt","alpineusdt","tusdt","astrusdt","gmtusdt","kdausdt","apeusdt","bswusdt","bifiusdt","multiusdt","steemusdt","mobusdt","nexousdt","reiusdt","galusdt","ldousdt","epxusdt","opusdt","leverusdt","stgusdt","luncusdt","gmxusdt","polyxusdt","aptusdt","osmousdt","hftusdt","phbusdt","hookusdt","magicusdt","hifiusdt","rplusdt","prosusdt","agixusdt","gnsusdt","synusdt","vibusdt","ssvusdt","lqtyusdt","ambusdt","bethusdt","gasusdt","glmusdt","promusdt","qkcusdt","uftusdt","idusdt","arbusdt","loomusdt","oaxusdt","rdntusdt","wbtcusdt","eduusdt","suiusdt","aergousdt","pepeusdt","flokiusdt","astusdt","sntusdt","combousdt","mavusdt","pendleusdt","arkmusdt","wbethusdt","wldusdt","fdusdusd"
        };

        Draft[] drafts = {new Draft_6455()};
        try {
            webSocketClient = new WebSocketClient(new URI("wss://stream.binance.com:9443/ws"), drafts[0]) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d("WebSocket", "Opened");
                    subscribeToKlines(tradingPairs);
                }

                @Override
                public void onMessage(String message) {
                    Log.d("WebSocket", "Received: " + message);
                    processKline(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("WebSocket", "Closed");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void subscribeToKlines(String[] tradingPairs) {
        StringBuilder subscribeMessageBuilder = new StringBuilder("{\"method\": \"SUBSCRIBE\",\"params\":[");
        for (int i = 0; i < tradingPairs.length; i++) {
            if (i > 0) {
                subscribeMessageBuilder.append(",");
            }
            String pair = tradingPairs[i];
            subscribeMessageBuilder.append("\"").append(pair).append("@kline_" + interval + "\"");
        }
        subscribeMessageBuilder.append("],\"id\": 1}");
        String subscribeMessage = subscribeMessageBuilder.toString();
        webSocketClient.send(subscribeMessage);
    }

    private void processKline(String message) {
        try {
            JSONObject json = new JSONObject(message);
            if (json.has("s") && json.has("k")) {
                JSONObject klineData = json.getJSONObject("k");
                String symbol = json.getString("s");
                double openPrice = klineData.getDouble("o");
                double closePrice = klineData.getDouble("c");
                double priceChange = closePrice - openPrice;
                double percentChange = (priceChange / openPrice) * 100;
                Log.d("WebSocket", "Symbol: " + symbol + ", Price Change: " + priceChange + ", Percent Change: " + percentChange);

                if ((percentChange) > limit) {
                    showNotification(symbol, percentChange);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private int notificationId = 0;

    private void showNotification(String pair, double percentChange) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "default_channel";
        String channelName = "Default Channel";
        int importance = NotificationManager.IMPORTANCE_HIGH; // Set the importance to high

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("PumpCoin")
                .setContentText("Pair: " + pair + ", Percent Change: " + percentChange + "%")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC); // Set visibility to public

        ; // Also set the priority to high for older Android versions

        // You can also use setDefaults() to add additional behavior like sound, vibration, or lights
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);

        notificationManager.notify(notificationId++, builder.build());
    }








}