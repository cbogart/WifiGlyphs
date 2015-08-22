package com.quetzal.wifiglyphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by chrisbogart on 2/7/15.
 */
public class AddSquareTransform implements ImageTransform {
    int center_x, center_y;
    int color;
    int offset_x, offset_y;
    float hue;
    public void initialize(ParamGenerator pg) {
        center_x = pg.next(1000);
        center_y = pg.next(1000);
        offset_x = pg.next(1000)-center_x;
        offset_y = pg.next(1000)-center_y;
        hue = pg.next(255)* (360.0f/256.0f);
    }
    public String describe() {
        return "AddCircleTransform:";
    }
    public void transform(Canvas input, int strength) {
        float strf = strength/100.0f;
        float w_scale = input.getWidth()/1000.0f;
        float h_scale = input.getHeight()/1000.0f;
        Paint p = new Paint();
        p.setColor(Color.HSVToColor((int)(strf*255), new float[]{hue, 1.f, 0.8f}));
        float cx = (center_x+offset_x*strf)*w_scale;
        float cy = (center_y+offset_y*strf)*h_scale;
        float d =        strf*50.0f;
        input.drawRect(cx-d,cy-d,cx+d,cy+d,p);

        //Bitmap dst = Bitmap.createBitmap(width, height, input.getConfig()); //output pic

    }
}
