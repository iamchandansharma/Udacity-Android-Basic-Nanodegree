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
public class HillStationDetailsFragment extends Fragment {


    public HillStationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        // /Arraylist for intializing data

        ArrayList<Word> word = new ArrayList<Word>();

        word.add(new Word(R.string.hill_station_chail,R.string.hill_station_himachal_pradesh,
                R.drawable.chail));
        word.add(new Word(R.string.hill_station_doodhpathri,R.string.hill_station_jammu_and_kashnir,
                R.drawable.doodhpathri));
        word.add(new Word(R.string.hill_station_khajjiar_lake,R.string.hill_station_himachal_pradesh,
                R.drawable.khajjiar_lake));
        word.add(new Word(R.string.hill_station_kotagiri,R.string.city_tamil_nadu,
                R.drawable.kotagiri));
        word.add(new Word(R.string.hill_station_kufri,R.string.hill_station_himachal_pradesh,
                R.drawable.kufri));
        word.add(new Word(R.string.hill_station_paithalmala,R.string.hill_station_kerala,
                R.drawable.paithalmala));
        word.add(new Word(R.string.hill_station_ponmudi,R.string.hill_station_kerala,
                R.drawable.ponmudi));
        word.add(new Word(R.string.hill_station_wilson_hill,R.string.hill_station_dharampur,
                R.drawable.wilson_hills));

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
