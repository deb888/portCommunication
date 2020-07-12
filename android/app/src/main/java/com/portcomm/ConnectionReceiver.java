package com.portcomm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by anupamchugh on 11/04/16.
 */

public class ConnectionReceiver extends BroadcastReceiver {
    private ReactApplicationContext mContext;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("API123", "" + intent.getAction());

        if (intent.getAction().equals("com.journaldev.broadcastreceiver.SOME_ACTION")) {
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
            String message = "SOME_ACTION is received";
            sendEvent("BroadCast_Received", message);
        }
        String action = intent.getAction();
        if (action.equals("marvel.intent.action.external.execute")) {
            Bundle bundle = intent.getExtras();
            Object objn1 = bundle.get("names");
            Object objd1 = bundle.get("datas");
            if (objn1 != null && objd1 != null) {
                String names1 = objn1.toString();
                String datas1 = objd1.toString();
                if (names1.equals("BodyTemp")) {
                    sendEvent("BroadCast_Received", datas1);
                }
            }
        }

    }

    private void sendEvent(String eventName, String eventId) {
        try {
            ReactContext reactContext = RNAndroidBroadcastReceiverEventReminderModule.reactContext;

            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, eventId);
        } catch (Exception e) {
            Log.d("ReactNativeJS", "Exception in sendEvent in EventReminderBroadcastReceiver is:" + e.toString());
        }

    }

}
