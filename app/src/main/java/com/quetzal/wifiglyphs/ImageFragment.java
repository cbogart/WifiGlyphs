package com.quetzal.wifiglyphs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Bitmap baseimage;
    //Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseimage = BitmapFactory.decodeResource(getResources(), R.drawable.baseimage);

    }


    public void strengthsChanged(Strengths s) {
        ImageView img = (ImageView)getView().findViewById(R.id.imageView);
        // TODO: Need to deal with the original bitmap, not the stretched, centered one.

        Log.v("strengthsChanged", "redrawing bitmap");
        Bitmap bmp = baseimage.copy(baseimage.getConfig(), true);
        Canvas c = new Canvas(bmp);
        //img.draw(c);

        for (int sigid=0; sigid<s.getCount(); sigid++) {
            // TODO: don't need to regenerate these objects every time.
            // TODO: in fact, keep old strength, and only baby-step it toward the new strength, so you get a smooth animation.
            // TODO: attend to only the strongest signal per distinct name; ignore duplicate "xfinitywifi"s and just take the max
            ParamGenerator pg = new ParamGenerator();
            pg.initialize(s.getItemId(sigid));
            ImageTransform it = ImageTransformFactory.factory(pg);
            it.transform(c, s.getStrength(sigid));

        }
        //Random r = new Random();
        //Paint p = new Paint();
        //p.setColor(Color.BLUE);
        //c.drawLine(r.nextInt(200), r.nextInt(200), img.getWidth()-1, img.getHeight()-1, p);
        img.setImageBitmap(bmp);
    }
}
