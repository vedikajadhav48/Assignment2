package com.example.vedikajadhav.assignment2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class DesertListActivity extends ActionBarActivity implements DesertListFragment.OnFragmentInteractionListener {
    public static final String TAG = "DesertListActivity";
    //private String desertListItemSelected;
    private int desertListItemSelected;
    private Bundle bundle;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Log.i(TAG, "DesertListActivity onCreate()");


       bundle = getIntent().getExtras();
       desertListItemSelected = bundle.getInt("DesertListItemSelected", 0);
       //DesertListFragment desertListFragment = DesertListFragment.newInstance(desertListItemSelected);
       DesertListFragment abc = new DesertListFragment();
       Bundle x = new Bundle();
       x.putInt("DesertListItemSelected", bundle.getInt("DesertListItemSelected"));
       abc.setArguments(x);
       Log.i(TAG, "desertListActivity in DesertListActivity" + bundle);
      /* desertListFragment.setArguments(bundle);*/

       setContentView(R.layout.activity_desert_list);

       getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setLogo(R.drawable.ic_launcher);
       getSupportActionBar().setDisplayUseLogoEnabled(true);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void backButtonMethod(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(int position) {
        Log.i(TAG, "DesertListActivity-->onFragmentInteraction");
/*        FragmentManager fragments = getSupportFragmentManager();
        DesertListFragment desertListFragment = (DesertListFragment)fragments.findFragmentById(R.id.desert_list_fragment);
        desertListFragment.setArguments(bundle);*/
        //desertListFragment.setSelectedItemIndex(desertListItemSelected);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_desert_list, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
