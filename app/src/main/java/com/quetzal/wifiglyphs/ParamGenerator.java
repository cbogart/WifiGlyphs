package com.quetzal.wifiglyphs;

import java.util.Random;

/**
 * Created by chrisbogart on 2/7/15.
 *
 * For now, just a wrapper around Random.
 *
 * As a future variant, consider a less random algorithm: make the
 * sequence of parameters sensible according to a the seed string;
 * e.g. deriving from the first letter, second letter, etc.
 *
 */
public class ParamGenerator {
    Random r;
    void initialize(long seed) {
        r = new Random(seed);
    }
    void initialize2(Object seed) {
        r = new Random(seed.hashCode()) ;
    }
    int next(int outOf) {
        return r.nextInt(outOf);
    }
}
