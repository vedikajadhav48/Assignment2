package com.example.vedikajadhav.assignment2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
    private String mDesertListItemSelected;

/*    @Override
    protected Fragment createFragment() {
        return new DesertListFragment();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
               // go.putExtra("DesertListItemSelected", mDesertListItemSelected);
                startActivity(go);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i(TAG, "Inside onActivityResult");
        if(requestCode != Intent_Date_Index){
            Log.i(TAG, "Inside onActivityResult return");
            return;
        }
        switch(resultCode){
            case RESULT_OK:
                Log.i(TAG, "Inside onActivityResult OK");
                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
               // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

               // SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
               // Date date = format.parse(data.toString());
                month = data.getIntExtra("Month", 1);
                day = data.getIntExtra("Day", 1);
                year = data.getIntExtra("Year", 2015);
                Log.i(TAG, "Vedika back date" + day);
                mMainEditText.setText(month + "/" + day + "/" + year);
                //Date mDate = new Date();
                //String formattedDate = dateFormat.format(date);
                //String formatedDate = sdf.format(new Date(year, month, day));
              //  mMainEditText = format.parseObject()
                break;
            case RESULT_CANCELED:
                Log.i(TAG, "Inside onActivityResult Cncelled");
                break;
        }
    }

    @Override
    public void onFragmentInteraction(String id) {
        FragmentManager fragments = getSupportFragmentManager();
        DesertListFragment desertListFragment = (DesertListFragment)fragments.findFragmentById(R.id.desert_list_fragment);
        mDesertListItemSelected = id;
        //desertListFragment.onListItemClick(ListView l, View v, int position, String id);

    }

    @Override
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
    }
}
