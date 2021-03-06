package com.portcomm;

import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import expo.modules.splashscreen.SplashScreen;
import expo.modules.splashscreen.SplashScreenImageResizeMode;

public class MainActivity extends ReactActivity {
    ConnectionReceiver receiver;
    IntentFilter intentFilter;
    IntentFilter filterRefresh;
    IntentFilter filterUpdate;
    IntentFilter testIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SplashScreen.show(...) has to be called after super.onCreate(...)
        // Below line is handled by '@expo/configure-splash-screen' command and it's
        // discouraged to modify it manually
        SplashScreen.show(this, SplashScreenImageResizeMode.CONTAIN, ReactRootView.class);
        receiver = new ConnectionReceiver();
        filterRefresh = new IntentFilter("marvel.intent.action.external.omcexc");
        filterUpdate = new IntentFilter("marvel.intent.action.external.execute");
        testIntent = new IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION");

        registerReceiver(receiver, filterRefresh);
        registerReceiver(receiver, filterUpdate);
        registerReceiver(receiver, testIntent);

    }

    /**
     * Returns the name of the main component registered from JavaScript. This is
     * used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "main";
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filterRefresh);
        registerReceiver(receiver, filterUpdate);
        registerReceiver(receiver, testIntent);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                return new RNGestureHandlerEnabledRootView(MainActivity.this);
            }
        };
    }
}
