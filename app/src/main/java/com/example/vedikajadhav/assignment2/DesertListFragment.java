package com.example.vedikajadhav.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.vedikajadhav.assignment2.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class DesertListFragment extends ListFragment {
    private static final String TAG = "DesertListFragment";
    private String mSelectedItemIndex;
    private int mCurCheckPosition = 0;

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DesertListFragment() {
    }

    public static DesertListFragment newInstance(int index) {
        DesertListFragment f = new DesertListFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DesertListFragment onCreate()");
        Bundle args = this.getArguments();
        if(args !=null) {
            mCurCheckPosition = args.getInt("index", 0);
            Log.i(TAG,"getArguments in DesertListFragment" + mCurCheckPosition);
        }

       setListAdapter(new ArrayAdapter<DesertList.Desert>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, DesertList.ITEMS));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "DesertListFragment onActivityCreated()");
        registerForContextMenu(getListView());
        // Make sure our UI is in the correct state.
        showItemClicked(mCurCheckPosition);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "DesertListFragment onAttach()");
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "DesertListFragment onStart()");
    }

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
     //   Bundle bundle = this.getArguments();
//        mCurCheckPosition = bundle.getInt("DesertListItemSelected");
        View view = getActivity().findViewById(R.id.desert_list_fragment);
        Log.i(TAG, "DesertListFragment onCreateView" + mCurCheckPosition);
        return view;
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "DesertListFragment onSaveInstanceState()");
        /*outState.putInt("curChoice", mCurCheckPosition);*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
           showItemClicked(position);
        /*super.onListItemClick(l, v, position, id);
        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        l.setItemChecked(position, true);
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mSelectedItemIndex = DesertList.ITEMS.get(position).id;
            mListener.onFragmentInteraction(mSelectedItemIndex);
        }*/
    }

    public void showItemClicked(int index){
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(index, true);
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mSelectedItemIndex = DesertList.ITEMS.get(index).id;
           // mListener.onFragmentInteraction(mSelectedItemIndex);
            mListener.onFragmentInteraction(index);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
       // public void onFragmentInteraction(String id);
        public void onFragmentInteraction(int position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, R.string.contextMenuItem1, Menu.NONE, "Details");
        menu.add(Menu.NONE, R.string.contextMenuItem2, Menu.NONE, "Delete");
    }

}
