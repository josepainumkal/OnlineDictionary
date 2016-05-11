package com.example.josethomas.onlinedictionary;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JoseThomas on 3/16/2016.
 */
public class BottomSectionFragment extends Fragment {

    private static TextView showMeaning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_section_fragment,container,false);

        showMeaning = (TextView)view.findViewById(R.id.showMeaning);

        return  view;
    }

    public void setMeaningonTextField( String wordMeaning){
        showMeaning.setText(Html.fromHtml(wordMeaning));
        showMeaning.setMovementMethod(new ScrollingMovementMethod());
    }
}
