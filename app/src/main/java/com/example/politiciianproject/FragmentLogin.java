package com.example.politiciianproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogin extends Fragment {
    private EditText inputUsername, inputPassword;
    Button submitLogin, submitRegistry;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
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

        View view=inflater.inflate(R.layout.fragment_login, container, false);
        inputUsername=view.findViewById(R.id.inputUsername);
        inputPassword=view.findViewById(R.id.inputPassword);
        submitLogin=view.findViewById(R.id.submitLogin);
        submitRegistry=view.findViewById(R.id.submitRegistry);

        submitRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Registration.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String uname=inputUsername.getText().toString();
        String pword=inputPassword.getText().toString();
        String checked = " ";

        DbHandler dbHandler=new DbHandler(getContext());
        checked=dbHandler.loginCheck(uname, pword);
        ArrayList<HashMap<String, String>> userInfo=dbHandler.GetPoliticianIdNNamebyusername(uname);

        switch(checked) {
            case "checked":
                String name = userInfo.get(0).get("name");
                String id = userInfo.get(0).get("id");
                Intent intent = new Intent(getContext(), UserPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key_name", name);
                intent.putExtra("Key_id", id);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getContext(), checked, Toast.LENGTH_LONG).show();
                break;

        }
            }

        });
        return view;
    }
}