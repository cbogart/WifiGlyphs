package com.quetzal.wifiglyphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by chrisbogart on 2/7/15.
 */
public class RotateTransform implements ImageTransform {
    int max_degrees = 35;
    int center_x;
    int center_y;
    public void initialize(ParamGenerator pg) {
        center_x = pg.next(1000);
        center_y = pg.next(1000);
        //max_degrees = pg.next(360-30)+30;
    }
    public String describe() {
        return "RotateTransform:";
    }
    public void transform(Canvas input, int strength) {
        float strf = strength/100.0f;
        float w_scale = input.getWidth()/1000.0f;
        float h_scale = input.getHeight()/1000.0f;
        //input.drawBitmap(input.)
        input.rotate(strf*max_degrees,center_x, center_y);
    }
}
