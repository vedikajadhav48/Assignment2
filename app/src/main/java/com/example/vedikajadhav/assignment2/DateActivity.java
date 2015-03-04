package com.example.vedikajadhav.assignment2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class DateActivity extends ActionBarActivity {
    private static final String TAG = "DateActivity";
    private Button mSelectDateButton;
    private DatePicker mDatePicker;
    private Date mDate;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences mSharedPreferences;
    private Boolean isDateSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSelectDateButton = (Button)findViewById(R.id.select_date_button);
        mDatePicker = (DatePicker)findViewById(R.id.date_picker);
        mSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log.i(TAG, mSharedPreferences.toString());
        if(mSharedPreferences != null){
            Log.i(TAG, mSharedPreferences.toString());
            mDatePicker.updateDate(mSharedPreferences.getInt("Year", 2015), mSharedPreferences.getInt("Month", 1), mSharedPreferences.getInt("Day", 1));
        }
    }

    public void confirmationDialog(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DateActivity.this);

        alertDialogBuilder.setTitle(R.string.confirmation_dialog_title);
        alertDialogBuilder.setMessage(R.string.confirmation_dialog_message);

        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton(R.string.confirm_positive_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                isDateSet = true;

                // go to a new activity of the app
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("Month", mDatePicker.getMonth());
                editor.putInt("Day", mDatePicker.getDayOfMonth());
                editor.putInt("Year", mDatePicker.getYear());
                long dateTime = mDatePicker.getCalendarView().getDate();
                Date date = new Date(dateTime);
                editor.commit();
            }
        });
        // set negative button: No message
        alertDialogBuilder.setNegativeButton(R.string.confirm_negative_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // cancel the alert box and put a Toast to the user
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "You chose a negative answer",
                        Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    @Override
    public void onBackPressed(){
        Log.i(TAG, "Back");
            Intent passDateBack = getIntent();
            // passDateBack.putExtra
            passDateBack.putExtra("Month", (mDatePicker.getMonth()) + 1);
            passDateBack.putExtra("Day", mDatePicker.getDayOfMonth());
            passDateBack.putExtra("Year", mDatePicker.getYear());
            Log.i(TAG, "Vedika Month" + mDatePicker.getMonth());
            setResult(RESULT_OK, passDateBack);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_date, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
