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
 * Created by chrisbogart on 8/19/15.
 */
public class WifiStrengths extends Strengths {
    List<ScanResult> m_lsr = null;
    WifiManager m_w = null;
    public WifiStrengths(Context c, WifiManager w) {
        super(c);
        if (w != null) {
            m_lsr = w.getScanResults();
            m_w = w;
        }
    }

    @Override
    public int getCount() { return m_lsr==null?0:m_lsr.size(); }
    @Override
    public String getItem(int position) { return m_lsr.get(position).SSID; }
    @Override
    public long getItemId(int position) { return getItem(position).hashCode(); }
    @Override
    public int getStrength(int position) { return m_w.calculateSignalLevel(m_lsr.get(position).level, 100); }
}