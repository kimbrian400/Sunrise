package com.kimbrian.sunrise.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kimbrian.sunrise.MainActivity;
import com.kimbrian.sunrise.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link reviewfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link reviewfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reviewfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    public reviewfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reviewfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static reviewfragment newInstance(String param1, String param2) {
        reviewfragment fragment = new reviewfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //declaring and initializing our views
        RatingBar mRatingBar = findViewById(R.id.ratingBar);
        TextView mRatingScale = findViewById(R.id.tvRatingScale);
        EditText mFeedback = findViewById(R.id.etFeedback);
        Button mSendFeedback = findViewById(R.id.btnSubmit);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviewfragment, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    /**mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            mRatingScale.setText(String.valueOf(v));
            switch ((int) ratingBar.getRating()) {
                case 1:
                    mRatingScale.setText("Very bad");
                    break;
                case 2:
                    mRatingScale.setText("Need some improvement");
                    break;
                case 3:
                    mRatingScale.setText("Good");
                    break;
                case 4:
                    mRatingScale.setText("Great");
                    break;
                case 5:
                    mRatingScale.setText("Awesome. I love it");
                    break;
                default:
                    mRatingScale.setText("");
            }
        }
    });
    mSendFeedback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mFeedback.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
            } else {
                mFeedback.setText("");
                mRatingBar.setRating(0);
                Toast.makeText(MainActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
            }
        }
    });**/
}
