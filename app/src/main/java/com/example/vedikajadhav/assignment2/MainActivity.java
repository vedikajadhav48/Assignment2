package com.example.vedikajadhav.assignment2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener, DesertListFragment.OnFragmentInteractionListener{

    private static final String TAG = "MainActivity";
    private Spinner mSpinner;
    private Button mMainActivitySelectButton;
    private EditText mMainEditText;
    private String mActivitySelected;
    private static final int Intent_Date_Index = 123;
    private int month, day, year;
   // private String mDesertListItemSelected;
    private int mDesertListItemSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "MainActivity onCreate() called");
        setContentView(R.layout.activity_main);

        mSpinner = (Spinner) findViewById(R.id.activities_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        mMainActivitySelectButton = (Button)findViewById(R.id.select_button);
        mMainEditText = (EditText)findViewById((R.id.main_edit_text));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        /*DesertListFragment desertListFragment = new DesertListFragment();
        desertListFragment.setArguments(savedInstanceState);*/

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        mActivitySelected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void selectActivity(View v){
        openActivity();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode != Intent_Date_Index){
            return;
        }
        switch(resultCode){
            case RESULT_OK:
                month = data.getIntExtra("Month", 1);
                day = data.getIntExtra("Day", 1);
                year = data.getIntExtra("Year", 2015);
                mMainEditText.setText(month + "/" + day + "/" + year);
                break;
            case RESULT_CANCELED:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(int position) {
        /*FragmentManager fragments = getSupportFragmentManager();
        DesertListFragment desertListFragment = (DesertListFragment)fragments.findFragmentById(R.id.desert_list_fragment);*/
        mDesertListItemSelected = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_date:
                mActivitySelected = "Date";
                openActivity();
                return true;
            case R.id.action_keyboard:
                mActivitySelected = "Keyboard";
                openActivity();
                return true;
            case R.id.action_desert:
                mActivitySelected = "Desert List";
                openActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openActivity(){
        final Intent go;
        switch(mActivitySelected){
            case "Date":
                go = new Intent(this, DateActivity.class);
                startActivityForResult(go, Intent_Date_Index);
                break;
            case "Keyboard":
                go = new Intent(this, KeyboardActivity.class);
                go.putExtra("Month", month);
                go.putExtra("Day", day);
                go.putExtra("Year", year);
                startActivity(go);
                break;
            case "Desert List":
                go = new Intent(this, DesertListActivity.class);
                go.putExtra("DesertListItemSelected", mDesertListItemSelected);
                startActivity(go);
                break;
        }
    }
}
