package com.example.vedikajadhav.assignment2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class KeyboardActivity extends ActionBarActivity {

    private EditText mKeyboardEditText1;
    private Button mBackButton;
    private Button mHideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mKeyboardEditText1 = (EditText)findViewById(R.id.keyboard_edit_text_1);
        mBackButton = (Button)findViewById(R.id.keyboard_back_button);
        mHideButton = (Button)findViewById(R.id.keyboard_hide_button);

        Bundle date = getIntent().getExtras();
        int month = date.getInt("Month", 1);
        int day = date.getInt("Day", 1);
        int year = date.getInt("Year", 1);
        if(year != 0)
        {
            mKeyboardEditText1.setText(month + "/" + day + "/" + year);
        }
    }

    public void backButtonMethod(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }

    public void hideKeyboard(View v){
        InputMethodManager manager;
        manager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mKeyboardEditText1.getWindowToken(), 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_keyboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
