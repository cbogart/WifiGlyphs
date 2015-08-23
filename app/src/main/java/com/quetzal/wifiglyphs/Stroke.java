package com.quetzal.wifiglyphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by chrisbogart on 8/22/15.
 */
public class Stroke {
    final static int NUMSHAPES=10;
    long sid = 0;
    String sname = "";

    float hue;
    long length;
    double angle;
    long width;
    int shape;
    float controlx, controly;

    Stroke(long sourceId, String sourceName) {
        sid = sourceId;
        sname = sourceName;
        Random r = new Random(sourceId+13024);

        length = r.nextInt(100)+30;
        angle = r.nextFloat() * 2 - 1.0;
        width = r.nextInt(8)+3;
        hue = r.nextFloat() * 360.0f;
        shape = r.nextInt(NUMSHAPES);
        controlx = r.nextFloat();
        controly = r.nextFloat();
    }
    long sourceId() {
        return sid;
    }
    String sourceName() { return sname; }
    StrokeDrawer makeStroke(final int startx, final int starty, double startDirection, final float strength) {
        final StrokeDrawer sd = new StrokeDrawer();
        sd.startx = startx;
        sd.starty = starty;
        sd.startDirection = startDirection;
        sd.endDirection = sd.startDirection + angle;
        sd.endx = (int)(sd.startx + strength * length * Math.cos(sd.endDirection) );
        sd.endy = (int)(sd.starty + strength * length * Math.sin(sd.endDirection) );
        sd.myBbox = new Rect(Math.min(startx, sd.endx), Math.min(starty, sd.endy),
                             Math.max(startx,sd.endx), Math.max(starty, sd.endy));
        //final int k = Color.HSVToColor((int)(strength*255.0/100.0), new float[]{hue, 1.f, 0.8f});
        final int k = Color.HSVToColor(new float[]{hue, 1.f, 0.8f});
        sd.drawer = new StrokeDrawer.DelayedDrawer() { @Override public void draw(Canvas c) {
            Paint p = new Paint();
            p.setColor(k);
            p.setStrokeWidth( c.getClipBounds().width()*width / 200.0f);
            if (shape < 8) {
                c.drawLine(startx, starty, sd.endx, sd.endy, p);
            } else if (shape == -1) {
                Path pp = new Path();
                pp.moveTo(startx,starty);
                pp.quadTo((sd.endx-startx)*controlx+startx,
                        (sd.endy-starty)*controly+starty,sd.endx, sd.endy);
                c.drawPath(pp,p);

            } else  {
                p.setStyle(Paint.Style.STROKE);
                c.drawCircle((startx+sd.endx)/2, (starty+sd.endy)/2,
                        (float)Math.sqrt(Math.pow(startx-sd.endx*1.0,2)+Math.pow(starty-sd.endy*1.0,2)), p);
            }
            //System.out.println("Drawing line " +  startx + " " + starty+ " " + sd.endx+ " " + sd.endy);
        } };
         // calculate end stuff
        return sd;
    }
}
