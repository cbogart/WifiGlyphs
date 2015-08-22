package com.quetzal.wifiglyphs;

import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * Created by chrisbogart on 8/19/15.
 */
/*
public class BLEStrengths extends BaseAdapter implements Strengths {
    Context context;
    List<ScanResult> m_lsr = null;
    WifiManager m_w = null;
    public BLEStrengths(Context c, ScanResult sr) {
        context = c;
        if (w != null) {
            m_lsr = w.getScanResults();
            m_w = w;
        }
    }

    public addScanResult(ScanResult sr) {

    }
    @Override
    public int getCount() { return m_lsr==null?0:m_lsr.size(); }
    @Override
    public String getItem(int position) { return m_lsr.get(position).SSID; }
    @Override
    public long getItemId(int position) { return getItem(position).hashCode(); }
    @Override
    public int getStrength(int position) { return m_w.calculateSignalLevel(m_lsr.get(position).level, 100); }

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
*/