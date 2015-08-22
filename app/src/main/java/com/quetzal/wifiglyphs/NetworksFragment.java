package com.quetzal.wifiglyphs;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.quetzal.wifiglyphs.NetworksFragment.OnStrengthsAdjusted} interface
 * to handle interaction events.
 * Use the {@link NetworksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworksFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnStrengthsAdjusted mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NetworksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NetworksFragment newInstance(String param1, String param2) {
        NetworksFragment fragment = new NetworksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NetworksFragment() {
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
        setListAdapter(new WifiStrengths(getActivity(), null));
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_net, container, false);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(4);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStrengthsAdjusted) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStrengthsAdjusted");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnStrengthsAdjusted {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int x);
    }

    public void strengthsChanged(Strengths s) {
        setListAdapter((BaseAdapter)s);
/*        ViewGroup area = (ViewGroup)getView().findViewById(R.id.linearPartOfScroll);
        for(int i=0; i<s.getCount(); i++) {
            View ls = View.inflate(getActivity(), R.layout.label_slider_view_layout, area);
            area.addView(ls);
        }
        */
    }

}
