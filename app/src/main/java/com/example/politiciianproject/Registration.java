package com.example.politiciianproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class Registration extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener {
    TextInputEditText name, ridingZone, title, phone, email, username, password;

    Button submitReg, submitNewPromise2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.mipmap.covericon_ic_launcher);
        actionBar.setLogo(R.mipmap.covericon_ic_launcher);

        name = findViewById(R.id.inputPolName);
        ridingZone = findViewById(R.id.inputRidingZone);
        title = findViewById(R.id.inputRegRole);
        phone = findViewById(R.id.inputPhone);
        email = findViewById(R.id.inputEmail);
        username = findViewById(R.id.inputRegUsername);
        password = findViewById(R.id.inputRegPassword);
        submitReg = findViewById(R.id.submitFinishedRegistry);


        submitReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String RidingZone = ridingZone.getText().toString();
                String Title = title.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Username = username.getText().toString();
                String Password = password.getText().toString();

                DbHandler dbHandler = new DbHandler(Registration.this);
                dbHandler.insertPoliticianDetails(Name, RidingZone, Title, Phone, Email, Username, Password);
                ArrayList<HashMap<String,String>> PoliticianId=new ArrayList<>();
                PoliticianId = dbHandler.GetPoliticianIdByName(Name);
                String id = PoliticianId.get(0).get("id");
                Intent intent = new Intent(Registration.this, FirstTimePromiseAddition.class);
                intent.putExtra("Key_Id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(),"Registration Successful!", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(Registration.this, MainActivity.class);

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