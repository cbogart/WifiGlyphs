package com.quetzal.wifiglyphs;
import android.graphics.Canvas;

/**
 * Created by chrisbogart on 2/7/15.
 */
public interface ImageTransform {
    public void initialize(ParamGenerator pg);
    public String describe();
    public void transform(Canvas input, int strength);
}

class ImageTransformFactory {
    public static ImageTransform factory(ParamGenerator pg) {
        ImageTransform it;
        switch (pg.next(2)) {
            case 1: it = new AddSquareTransform(); break;
            case 2: it = new RotateTransform(); break;
            case 3: it = new SquashTransform(); break;
            default: it = new AddCircleTransform();
        }
        it.initialize(pg);
        return it;
    }
}