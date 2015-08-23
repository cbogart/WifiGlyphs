package com.quetzal.wifiglyphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chrisbogart on 8/22/15.
 */
public class Glyph {
    List<Stroke> strokes = new ArrayList<Stroke>();
    List<StrokeDrawer> strokeDrawers = new ArrayList<StrokeDrawer>();
    Glyph(Strengths strengths) {
        for(int i=0; i<strengths.getCount(); i++) {
            strokes.add(new Stroke(strengths.getItemId(i), strengths.getItem(i)));
        }
        precalculate(strengths);
    }

    void precalculate(Strengths strengths) {
        int startx = 0, starty=0;
        double startDirection=0.0f;
        for (Stroke s:  strokes) {
            StrokeDrawer drawer = s.makeStroke(startx, starty, startDirection, strengths.getStrengthId(s.sourceId()));
            startx = drawer.endx;
            starty = drawer.endy;
            startDirection = drawer.endDirection;
            strokeDrawers.add(drawer);
        }
    }

    Rect computeBbox() {
        Rect r = new Rect(0,0,0,0);
        for (StrokeDrawer d: strokeDrawers) {
            r.union(d.myBbox);
        }
        System.out.println("Combined bbox is " + r.left + " " + r.top + " " + r.right + " " + r.bottom);
        return r;
    }

    void draw(Canvas c, Rect bounds) {
        bounds.union(computeBbox());
        float xscale = c.getWidth()*1.0f/bounds.width();
        float yscale = c.getHeight()*1.0f/bounds.height();
        c.save();
        float finalScale = Math.min(xscale,yscale)*.95f;
        c.scale(finalScale, finalScale);
        c.translate(-bounds.left, -bounds.top);
        //c.scale(Math.min(xscale, yscale), Math.min(xscale,yscale));
        for (StrokeDrawer d: strokeDrawers) {
            d.draw(c);
        }
        c.restore();
    }
}
