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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class FirstTimePromiseAddition extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener{
    TextInputEditText target2, policy2, deadline2, startYear2, billsResults2;
    Button submitRegNewPromise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_promise_addition);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.mipmap.covericon_ic_launcher);
        actionBar.setLogo(R.mipmap.covericon_ic_launcher);

        target2=findViewById(R.id.inputTarget2);
        policy2=findViewById(R.id.inputPolicy2);
        deadline2=findViewById(R.id.inputDeadline2);
        startYear2=findViewById((R.id.inputStartYear2));
        billsResults2=findViewById((R.id.inputBillsResults2));
        submitRegNewPromise=findViewById(R.id.submitRegNewPromise);
        submitRegNewPromise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Target = target2.getText().toString();
                String Policy = policy2.getText().toString();
                String Deadline = deadline2.getText().toString();
                String StartYear = startYear2.getText().toString();
                String BillsResults = billsResults2.getText().toString();
                Bundle extras = getIntent().getExtras();
                String PoliticianId = extras.getString("Key_Id");


                DbHandler2 dbHandler2 = new DbHandler2(FirstTimePromiseAddition.this);
                dbHandler2.insertPromiseDetails(Target, Policy, Deadline, StartYear, BillsResults,PoliticianId);
                Toast.makeText(getApplicationContext(),"Add Promises Successful!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FirstTimePromiseAddition.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reg_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String title=item.getTitle().toString();
        switch (title){
            case "Go Back to Home":
                goBackToHome();
                return true;
            case "Registered Politician":
                goToLogin();
                return true;
            case "Visitor":
                goToCoverSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void goBackToHome(){
        Intent intent = new Intent(FirstTimePromiseAddition.this, MainActivity.class);

        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    private void goToCoverSearch(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, FragmentCoverSearch.class, null)
                .commit();
    }
    private void goToLogin(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, FragmentLogin.class, null)
                .commit();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}