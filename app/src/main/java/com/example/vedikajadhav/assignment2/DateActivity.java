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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;


public class DateActivity extends ActionBarActivity {
    private static final String TAG = "DateActivity";
    private Button mSelectDateButton;
    private DatePicker mDatePicker;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        mSelectDateButton = (Button)findViewById(R.id.select_date_button);
        mDatePicker = (DatePicker)findViewById(R.id.date_picker);
        mSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log.i(TAG, mSharedPreferences.toString());
        if(mSharedPreferences != null){
            Log.i(TAG, mSharedPreferences.toString());
            mDatePicker.updateDate(mSharedPreferences.getInt("Year", 2015), mSharedPreferences.getInt("Month", 1), mSharedPreferences.getInt("Day", 1));
        }
        //mDatePicker.updateDate();
    }

    public void confirmationDialog(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DateActivity.this);

        alertDialogBuilder.setTitle(R.string.confirmation_dialog_title);
        alertDialogBuilder.setMessage(R.string.confirmation_dialog_message);

        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton(R.string.confirm_positive_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // go to a new activity of the app
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("Month", mDatePicker.getMonth());
                editor.putInt("Day", mDatePicker.getDayOfMonth());
                editor.putInt("Year", mDatePicker.getYear());
               // editor.putString("username", username.getText().toString());
                //editor.putString("password", password.getText().toString());
                //editor.putString("logged", "logged");
                editor.commit();
                //Log.i(TAG, mDatePicker.get);
              /*  Intent positveActivity = new Intent(getApplicationContext(),
                        PositiveActivity.class);
                startActivity(positveActivity);*/
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

    public void back(View button){
        Log.i(TAG, "Back");
        Intent passDateBack = getIntent();
       // passDateBack.putExtra
        passDateBack.putExtra("Month", mDatePicker.getMonth());
        passDateBack.putExtra("Day", mDatePicker.getDayOfMonth());
        passDateBack.putExtra("Year", mDatePicker.getYear());
        finish();
    }

  /*  @Override
    public void onActionModeFinish(ActionMode actionMode){
        super.onActionModeFinished(actionMode);
    }*/
}
