package com.example.politiciianproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class UserPage extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener{
    TextView userSubTitle, userPromises;
    TextInputEditText target, policy, deadline, startYear, billsResults;
    Button submitNewPromise, userLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.mipmap.covericon_ic_launcher);
        actionBar.setLogo(R.mipmap.covericon_ic_launcher);

        userSubTitle=findViewById(R.id.userSubTitle);
        userPromises=findViewById(R.id.userPromises);
        target=findViewById(R.id.inputTarget);
        policy=findViewById(R.id.inputPolicy);
        deadline=findViewById(R.id.inputDeadline);
        startYear=findViewById((R.id.inputStartYear));
        billsResults=findViewById((R.id.inputBillsResults));
        submitNewPromise=findViewById(R.id.submitNewPromise);
        userLogout=findViewById(R.id.userLogout);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("key_name");
        String id = extras.getString("Key_id");

        userSubTitle.setText("Hi,"+ name);


        DbHandler2 dbHandler2 = new DbHandler2((UserPage.this));
        ArrayList<HashMap<String, String>> a = dbHandler2.GetPromiseByPoliticianId(Integer.parseInt(id));
            String t = a.get(0).get("target");
            String p = a.get(0).get("policy");
            String d = a.get(0).get("deadline");
            String s = a.get(0).get("startYear");
            String b = a.get(0).get("billsResults");
        String message = ("On "+s+", you promised a policy of "+p+" to all residents in "+t+" and aimed to complete it by "+d+". So far you have submitted "+b+" bills.");
            userPromises.setText(message);




        submitNewPromise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Target = target.getText().toString();
                String Policy = policy.getText().toString();
                String Deadline = deadline.getText().toString();
                String StartYear = startYear.getText().toString();
                String BillsResults = billsResults.getText().toString();
                String PoliticianId = id;


                DbHandler2 dbHandler2 = new DbHandler2(UserPage.this);
                dbHandler2.insertPromiseDetails(Target, Policy, Deadline, StartYear, BillsResults,PoliticianId);
                Toast.makeText(getApplicationContext(),"Add Promises Successful!", Toast.LENGTH_LONG).show();

            }
        });

        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserPage.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

               finish();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String title=item.getTitle().toString();
        switch (title){
            case "Logout":
                goBackToHome();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void goBackToHome(){
        Intent intent = new Intent(UserPage.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

}