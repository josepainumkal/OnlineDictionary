package com.example.josethomas.onlinedictionary;

/**
 * Created by JoseThomas on 3/16/2016.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TopSectionFragment extends Fragment{

    private static EditText topTextInput;

    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void onClickingSubmitButton(String word);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            activityCommander = (TopSectionListener)context;
        }catch (Exception e){
            throw new ClassCastException(context.toString());
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_section_fragment,container,false);

        topTextInput = (EditText) view.findViewById(R.id.topTextInput);
        final Button submitButton = (Button)view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(
                new View.OnClickListener(){
                    public  void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

        return  view;
    }

    public void buttonClicked(View view){
       activityCommander.onClickingSubmitButton(topTextInput.getText().toString());
    }
}
