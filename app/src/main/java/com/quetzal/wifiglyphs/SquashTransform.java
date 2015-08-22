package com.quetzal.wifiglyphs;

import android.graphics.Canvas;

/**
 * Created by chrisbogart on 2/7/15.
 */
public class SquashTransform implements ImageTransform {
    int max_degrees = 350;
    int skew_x;
    int skew_y;
    public void initialize(ParamGenerator pg) {
        skew_x = pg.next(70);
        skew_y = pg.next(70);
        //max_degrees = pg.next(360-30)+30;
    }
    public String describe() {
        return "RotateTransform:";
    }
    public void transform(Canvas input, int strength) {
        float strf = strength/100.0f;
        float w_scale = input.getWidth()/1000.0f;
        float h_scale = input.getHeight()/1000.0f;
        input.skew(skew_x*strf, skew_y*strf);
    }
}
