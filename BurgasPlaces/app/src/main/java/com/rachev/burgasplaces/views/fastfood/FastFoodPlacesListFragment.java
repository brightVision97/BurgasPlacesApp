package com.rachev.burgasplaces.views.fastfood;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rachev.burgasplaces.BurgasPlacesApp;
import com.rachev.burgasplaces.R;
import com.rachev.burgasplaces.constants.Constants;
import com.rachev.burgasplaces.views.base.BaseListFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FastFoodPlacesListFragment extends BaseListFragment implements AdapterView.OnItemClickListener
{
    private ListView mFastFoodPlacesListView;
    
    public FastFoodPlacesListFragment()
    {
        // Required empty public constructor
    }
    
    public static FastFoodPlacesListFragment getInstance()
    {
        return new FastFoodPlacesListFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fast_food_places_list, container, false);
        
        mFastFoodPlacesListView = view.findViewById(R.id.lv_fast_food_places);
        setArrayAdapter(new ArrayAdapter<>(getContext(), R.layout.custom_simple_list1));
        
        mFastFoodPlacesListView.setAdapter(getArrayAdapter());
        mFastFoodPlacesListView.setOnItemClickListener(this);
        mFastFoodPlacesListView.setOnItemLongClickListener((parent, v, position, id) ->
        {
            showBlurredImageDialog(mFastFoodPlacesListView, position);
            return true;
        });
        
        setPlacesList(new ArrayList<>());
        loadListData(Constants.FASTFOOD, BurgasPlacesApp.getSuperheroRepository());
        
        return view;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        getNavigator().navigateWith(getPlacesList().get(position));
    }
}
