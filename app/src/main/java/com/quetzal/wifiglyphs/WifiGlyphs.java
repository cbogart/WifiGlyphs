package com.quetzal.wifiglyphs;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


public class WifiGlyphs extends Activity implements NetworksFragment.OnStrengthsAdjusted {

    DataSource ds = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ds = new WifiSource();
        setContentView(R.layout.activity_wifi_glyphs);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new NetworksFragment(), "networks")
                    .add(R.id.container, new ImageFragment(), "image")
                    .commit();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // ActivityResult something something
    }

    //public void
    public void onFragmentInteraction(int x) {

    }

    @Override
    public void onResume() {
        super.onResume();
        ds.startPolling(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wifi_glyphs, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.v("onOptions","item selected");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void registerStrengths(Strengths latest) {
        FragmentManager fm = getFragmentManager();
        ImageFragment ifr = (ImageFragment)fm.findFragmentByTag("image");
        //NetworksFragment nfr = (NetworksFragment)fm.findFragmentByTag("networks");
        if (ifr != null) {
            ifr.strengthsChanged(latest);
        }
        //if (nfr != null) {
        //    nfr.strengthsChanged(latest);
        //}
    }

    public void seekBarClicked(View v) {
        // really we want the changed strengths; but this'll prove it worked.
        registerStrengths(new WifiStrengths(this, null));
    }
    public interface StrengthsReceiver {
        public void strengthsChanged(Strengths s);
    }

}
