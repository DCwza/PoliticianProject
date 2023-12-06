package com.example.politiciianproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCoverSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCoverSearch extends Fragment {

    TextInputEditText inputSearch;
    Button submitSearch, btnBack;
    TextView displaySearch;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCoverSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoverSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCoverSearch newInstance(String param1, String param2) {
        FragmentCoverSearch fragment = new FragmentCoverSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cover_search, container, false);
        inputSearch=view.findViewById(R.id.input);
        submitSearch=view.findViewById(R.id.submit);
        displaySearch=view.findViewById(R.id.display);
        submitSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String inSearch = inputSearch.getText().toString();
                DbHandler dbHandler = new DbHandler(getContext());
                ArrayList<HashMap<String,String>> PoliticianId=new ArrayList<>();
                PoliticianId = dbHandler.GetPoliticianIdByName(inSearch);
                String id = PoliticianId.get(0).get("id");
                if(Integer.parseInt(id)==0){
                    displaySearch.setText(("I could not find this politician. Please try a new one."));

                }else{

                    DbHandler2 dbHandler2 = new DbHandler2(getContext());

                    ArrayList<HashMap<String, String>> resSearch = dbHandler2.GetPromiseByPoliticianId(Integer.parseInt(id));
                    String t = resSearch.get(0).get("target");
                    String p = resSearch.get(0).get("policy");
                    String d = resSearch.get(0).get("deadline");
                    String s = resSearch.get(0).get("startYear");
                    String b = resSearch.get(0).get("billsResults");
                    String message = (inSearch + " promised a policy of " + p + " to all residents in " + t + " and aimed to complete it by " + d + ". Since " + s + ", " + inSearch + " have submitted " + b + " bills.");
                    displaySearch.setText(message);}
            }

        });



        return view;
    }
}