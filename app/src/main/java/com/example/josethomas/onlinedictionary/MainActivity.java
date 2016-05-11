package com.example.josethomas.onlinedictionary;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TopSectionFragment.TopSectionListener{

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public MainActivity(){
        handler = new Handler();
    }

    @Override
    public void onClickingSubmitButton(final String word) {
        final BottomSectionFragment bottomFragment = (BottomSectionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        bottomFragment.setMeaningonTextField("");

        //hiding the software keyboard
        View view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }



        new Thread() {
            public void run(){
               final String result = RemoteFetchThread.getWordMeaning(word);

                if(result == null || result.isEmpty()){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    MainActivity.this.getString(R.string.no_details_found),
                                    Toast.LENGTH_LONG).show();

                            bottomFragment.setMeaningonTextField("");

                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bottomFragment.setMeaningonTextField(result);
                        }
                    });
                }
            }

        }.start();

        //Get word meaning from internet
        //RemoteFetch remoteFetch = new RemoteFetch();
        //remoteFetch.setWord(word);
        //new RemoteFetch().execute();
        //String result = remoteFetch.getResult();

        //bottomFragment.setMeaningonTextField(result);

    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
