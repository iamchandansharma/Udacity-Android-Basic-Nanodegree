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
public class UniversityDetailsFragment extends Fragment {


    public UniversityDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Arraylist for intializing data

        ArrayList<Word> word = new ArrayList<Word>();

        word.add(new Word(R.string.university_bhu, R.drawable.bhu));
        word.add(new Word(R.string.university_bits, R.drawable.bits));
        word.add(new Word(R.string.university_iisc, R.drawable.iisc));
        word.add(new Word(R.string.university_iitb, R.drawable.iitb));
        word.add(new Word(R.string.university_iitd, R.drawable.iitd));
        word.add(new Word(R.string.university_iitk, R.drawable.iitk));
        word.add(new Word(R.string.university_iitm, R.drawable.iitm));
        word.add(new Word(R.string.university_jnu, R.drawable.jnu));
        word.add(new Word(R.string.university_uod, R.drawable.uod));
        word.add(new Word(R.string.university_uoh, R.drawable.uoh));


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

