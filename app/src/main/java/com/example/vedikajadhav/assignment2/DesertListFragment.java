package com.example.vedikajadhav.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private static String mSelectedItemIndex;

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
 /*   public DesertListFragment() {
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() fragment ");
       /* if(getActivity().toString() == "DesertListActivity"){
            getActivity().setTitle("Desert List Activity");
        }*/

        // TODO: Change Adapter to display your content

/*        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS))*/;
    /*    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activities_array, android.R.layout.simple_spinner_item);*/
       // setListAdapter(new ArrayAdapter<DesertList.Desert>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, DesertList.ITEMS));
       // setListAdapter(new ArrayAdapter<DesertList.Desert>(getActivity(), android.R.layout.simple_list_item_checked, android.R.id.text1, DesertList.ITEMS));
       setListAdapter(new ArrayAdapter<DesertList.Desert>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, DesertList.ITEMS));
       /* ListView dessertView = getListView();
        dessertView.setItemChecked(getSelectedItemPosition(), true);*/

    }

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //super.onCreat

    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated() fragment");
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach() fragment");
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

/*    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    public void setSelectedItemIndex(String desertListItemSelected){
        mSelectedItemIndex = desertListItemSelected;
        Log.i(TAG, "setSelectedItemIndex() fragment" + mSelectedItemIndex);
        /*ListView desertList = getListView();
        desertList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<DesertList.Desert>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, DesertList.ITEMS));
        desertList.setItemChecked(getSelectedItemPosition(),true);*/

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.i(TAG, "onListItemClicked() fragment called" + position);
        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        l.setItemChecked(position, true);
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
           // mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
          //  l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            mSelectedItemIndex = DesertList.ITEMS.get(position).id;

            mListener.onFragmentInteraction(mSelectedItemIndex);
        }
/*        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        l.setItemChecked(position, true);*/
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
