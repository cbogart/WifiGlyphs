package com.quetzal.wifiglyphs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by chrisbogart on 8/19/15.
 */
public class WifiSource implements DataSource {
    public void startPolling(final WifiGlyphs parent) {
        WifiManager wm = (WifiManager) parent.getSystemService(Context.WIFI_SERVICE);
        WifiManager.WifiLock wifiLock = wm.createWifiLock(WifiManager.WIFI_MODE_SCAN_ONLY , "WifiGlyphsLock");
        if(!wifiLock.isHeld()){
            wifiLock.acquire();
        }
        if(wm.isWifiEnabled() == false) {
            wm.setWifiEnabled(true);
        }


        IntentFilter intent =new IntentFilter();
        intent.addAction (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        BroadcastReceiver rcv = new BroadcastReceiver(){
            @Override
            public void onReceive(Context c,Intent i){
                Log.v("Got scan result", "y");
                WifiManager w =(WifiManager) c.getSystemService(Context.WIFI_SERVICE);
                parent.registerStrengths(new WifiStrengths(c, w));
                w.startScan();
            }
        };
        parent.registerReceiver(rcv, intent);
        Log.v("Requested scan","x");
    }
}
