package com.quetzal.wifiglyphs;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.Log;

/**
 * Created by chrisbogart on 8/19/15.
 *
 * Get more info here: https://developer.android.com/guide/topics/connectivity/bluetooth-le.html
 */
public class BLESource implements DataSource {
    private final static int REQUEST_ENABLE_BT = 1;
    public void startPolling(final WifiGlyphs parent) {
        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) parent.getSystemService(Context.BLUETOOTH_SERVICE);
        final BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
        final BluetoothLeScanner mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();

        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            parent.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

/*
        mBluetoothLeScanner.startScan(new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                result.getName()
            }
        }, SCAN_PERIOD);

        mScanning = true;
        mBluetoothAdapter.startLeScan(mLeScanCallback);
*/

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
