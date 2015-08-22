package com.quetzal.wifiglyphs;

import android.os.Handler;
import android.util.Log;

/**
 * Created by chrisbogart on 8/22/15.
 */
public class TestSource implements DataSource {
    // Create the Handler object (on the main thread by default)
    Handler handler = new Handler();
    WifiGlyphs mParent;
    Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            Log.e("Handlers", "Called");
            mParent.registerStrengths(new Strengths(mParent));
            // Repeat this runnable code again every 2 seconds
            handler.postDelayed(runnableCode, 2000);
        }
    };
    public void stopPolling() {
        // Removes pending code execution
        handler.removeCallbacks(runnableCode);
    }
    public void startPolling(final WifiGlyphs parent) {

        // Kick off the first runnable task right away
        handler.post(runnableCode);
        mParent = parent;
        Log.v("Requested scan","x");
    }
}
