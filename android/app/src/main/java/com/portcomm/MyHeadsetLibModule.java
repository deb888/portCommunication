package com.portcomm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactMethod;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class MyHeadsetLibModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

    private ReactApplicationContext mContext;

    private final BroadcastReceiver mHeadsetPlugReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                boolean plugged = (intent.getIntExtra("state", 0) == 1);
                String message = plugged ? "Headset plugged in" : "Headset plugged out";
                mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("Got_Cha", message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public MyHeadsetLibModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        mContext.registerReceiver(mHeadsetPlugReceiver, filter);
    }

    @Override
    public String getName() {
        return "MyHeadsetLibModule";
    }

    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        mContext.unregisterReceiver(mHeadsetPlugReceiver);
    }
    @ReactMethod
    public void startTrackingAudioJackPlug() {
        registerBroadcastReceiver();
    }
    @ReactMethod
    public void someMethod() {

        Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
        mContext.sendBroadcast(intent);
    }
}
