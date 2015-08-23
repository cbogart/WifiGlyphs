package com.quetzal.wifiglyphs;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by chrisbogart on 8/22/15.
 */
public class StrokeDrawer {
    int startx, endx;
    int starty, endy;
    double startDirection, endDirection;
    Rect myBbox;
    interface DelayedDrawer {
        void draw(Canvas c);
    }
    DelayedDrawer drawer;
    void draw(Canvas c) { drawer.draw(c); }
}
