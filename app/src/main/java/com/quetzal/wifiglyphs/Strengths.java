package com.quetzal.wifiglyphs;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bogart-MBP-isri on 12/7/14.
 *
 * TODO: Subclass this to separate wifi from ble from test-random-data versions
 * TODO: write version specific to ble data
 *  https://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#getBluetoothLeScanner()
 *  https://developer.android.com/reference/android/bluetooth/le/ScanRecord.html
 *
 *
 *  Default implementation gives some arbitrary values for testing, e.g. when on an emulator
 *  where wifi is not available
 */

public class Strengths extends BaseAdapter {
    Context context;
    public Strengths(Context c) {
        context = c;
    }
    public int getCount() {
        return 15;
    }
    public String getItem(int position) {
        return "xyzzy" + position;
    }
    public long getItemId(int position) {
        return 4129389 + position;
    }
    public int getStrength(int position) {
        return ((position+6)%15)*7;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.label_slider_view_layout, container, false);
        }
        ((TextView) convertView.findViewById(R.id.textView))
                .setText(getItem(position));
        SeekBar seek = (SeekBar) convertView.findViewById(R.id.seekBar);
        seek.setMax(100);
        seek.setProgress(getStrength(position));
        return convertView;
    }
}


