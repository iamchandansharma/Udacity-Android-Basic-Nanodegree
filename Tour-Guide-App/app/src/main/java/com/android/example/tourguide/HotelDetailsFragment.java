package com.android.example.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelDetailsFragment extends Fragment {


    public HotelDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Arraylist for intializing data

        ArrayList<Word> word = new ArrayList<Word>();

        word.add(new Word(R.string.hotel_umaid_bhawan_palace,R.drawable.umaid_bhawan_palace));
        word.add(new Word(R.string.hotel_orange_counrt_resorts, R.drawable.orange_county_resorts_kabini));
        word.add(new Word(R.string.hotel_taj_lake_palace, R.drawable.taj_lake_palace));
        word.add(new Word(R.string.hotel_the_oberoi_rajvilas, R.drawable.the_oberoi_rajvilas));
        word.add(new Word(R.string.hotel_orange_country_coorg, R.drawable.orange_county_coorg));
        word.add(new Word(R.string.hotel_rambha_palace, R.drawable.rambagh_palace));
        word.add(new Word(R.string.hotel_the_oberoi_banglore, R.drawable.the_oberoi_banglore));
        word.add(new Word(R.string.hotel_bella_vista_resort, R.drawable.bella_vista_resort));
        word.add(new Word(R.string.hotel_the_leela_palace, R.drawable.the_leela_palace));


        //create custome adapter of the WordAdapter
        //WordAdapter wordAdapter = new WordAdapter(this,word);
        WordAdapter wordAdapter = new WordAdapter(getActivity(), word);
        //create listView
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //set WordAdapter on listView
        listView.setAdapter(wordAdapter);

        return rootView;
    }
}
