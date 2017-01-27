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
public class CityDetailsFragment extends Fragment {


    public CityDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Arraylist for intializing data

        ArrayList<Word> word = new ArrayList<Word>();

        word.add(new Word(R.string.city_agra, R.string.city_utter_pradesh, R.drawable.agra));
        word.add(new Word(R.string.city_ahmedabad, R.string.city_gujarat, R.drawable.ahmedabad));
        word.add(new Word(R.string.city_amritsar, R.string.city_punjab, R.drawable.amritsar));
        word.add(new Word(R.string.city_bangalore, R.string.city_karnataka, R.drawable.bangalore));
        word.add(new Word(R.string.city_chennai, R.string.city_tamil_nadu, R.drawable.chennai));
        word.add(new Word(R.string.city_hyderabad, R.string.city_telangana, R.drawable.hyderabad));
        word.add(new Word(R.string.city_jaipur, R.string.city_rajasthan, R.drawable.jaipur));
        word.add(new Word(R.string.city_lucknow, R.string.city_utter_pradesh, R.drawable.lucknow));
        word.add(new Word(R.string.city_mumbai, R.string.city_maharashtra, R.drawable.mumbai));
        word.add(new Word(R.string.city_noida, R.string.city_utter_pradesh, R.drawable.noida));
        word.add(new Word(R.string.city_pune, R.string.city_maharashtra, R.drawable.pune));
        word.add(new Word(R.string.city_trichy, R.string.city_tamil_nadu, R.drawable.trichy));

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
