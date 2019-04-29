package com.mecodroid.notelite;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_noteformating extends Fragment {
    SeekBar seekBar_textsize;
    Edit_Note activity;
    ImageView vhide, Tcent, Tright, Tleft, Scent, Sright, Sleft;
    int gravitycentre1, gravitycentre2;
    SharedPreferences gr;

    public Fragment_noteformating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_blank, container, false);
        vhide = v.findViewById(R.id.fraghide);
        Tleft = v.findViewById(R.id.imleft1);
        Tcent = v.findViewById(R.id.imcenter1);
        Tright = v.findViewById(R.id.imright1);
        Sleft = v.findViewById(R.id.imleft2);
        Scent = v.findViewById(R.id.imcenter2);
        Sright = v.findViewById(R.id.imright2);

        seekBar_textsize = v.findViewById(R.id.seekBar_textsize);
        activity = (Edit_Note) getActivity();

        vhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().hide(Fragment_noteformating.this).commit();
            }
        });
        Tcent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.tex1.setGravity(Gravity.CENTER);

            }
        });
        Tleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.tex1.setGravity(Gravity.LEFT);
            }
        });
        Tright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.tex1.setGravity(Gravity.RIGHT);
            }
        });
      Scent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.tex2.setGravity(Gravity.CENTER);

            }
        });
      Sleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.tex2.setGravity(Gravity.LEFT);
            }
        });
      Sright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.tex2.setGravity(Gravity.RIGHT);

            }
        });
        seekBar_textsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                activity = (Edit_Note) getActivity();
                activity.tex2.setTextSize(pval);
            }
        });
        return v;
    }

}
