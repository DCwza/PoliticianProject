package com.example.politiciianproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.Suppress;

public class MainActivity extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener {
    /* TextInputEditText inputSearch;
     Button submitSearch;
     TextView displaySearch;

   /*EditText inputUsername;
     EditText inputPassword;
     Button submitLogin, submitRegistry;
    Button btnCallLogin, btnCallCoverSearch;*/

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.mipmap.covericon_ic_launcher);
        actionBar.setLogo(R.mipmap.covericon_ic_launcher);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        new StartAPPDialogFragment().show(supportFragmentManager, "CoverPage_Dialog");

        getSupportFragmentManager().beginTransaction();
        supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.frameLayout,FragementCoverSheet.class,null)
                .commit();
        }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String title=item.getTitle().toString();
        switch (title){
            case "New Politician":
               goToRegistry();
                return true;
            case "Registered Politician":
                goToLogin();
                return true;
            case "Visitor":
                goToCoverSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String title=item.getTitle().toString();
        switch (title){
            case "New Politician":
                goToRegistry();
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
    private void goToRegistry(){
        Intent intent = new Intent(MainActivity.this, Registration.class);

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


    public void replaceFragmentCoverSearch(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, FragmentCoverSearch.class, null)
                .commit();
    }

    public void replaceFragmentLogin(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, FragmentLogin.class, null)
                .commit();
    }
    public void replaceFragmentCoverSheet(View view){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout,FragementCoverSheet.class,null)
                .commit();

    }
}
       /* btnCallLogin = findViewById(R.id.callLogin);
        btnCallCoverSearch = findViewById(R.id.callCoverSearch);

        btnCallCoverSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frameLayout,FragmentCoverSearch.class,null)
                        .commit();
            }
        });

        btnCallLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frameLayout,FragmentLogin.class,null)
                        .commit();
            }
        });*/

        /*inputSearch=findViewById(R.id.input);
        submitSearch=findViewById(R.id.submit);
        displaySearch=findViewById(R.id.display);

        inputUsername=findViewById(R.id.inputUsername);
        inputPassword=findViewById(R.id.inputPassword);
        submitLogin=findViewById(R.id.submitLogin);
        submitRegistry=findViewById(R.id.submitRegistry);

        /*submitSearch.setOnClickListener(new View.OnClickListener() {
 
                @Override
            public void onClick(View v) {
                String inSearch = inputSearch.getText().toString();
                DbHandler dbHandler = new DbHandler(MainActivity.this);
                ArrayList<HashMap<String,String>> PoliticianId=new ArrayList<>();
                PoliticianId = dbHandler.GetPoliticianIdByName(inSearch);
                    String id = PoliticianId.get(0).get("id");
                    if(Integer.parseInt(id)==0){
                        displaySearch.setText(("I could not find this politician. Please try a new one."));

                    }else{
                    DbHandler2 dbHandler2 = new DbHandler2(MainActivity.this);

                    ArrayList<HashMap<String, String>> resSearch = dbHandler2.GetPromiseByPoliticianId(Integer.parseInt(id));
                    String t = resSearch.get(0).get("target");
                    String p = resSearch.get(0).get("policy");
                    String d = resSearch.get(0).get("deadline");
                    String s = resSearch.get(0).get("startYear");
                    String b = resSearch.get(0).get("billsResults");
                    String message = (inSearch + " promised a policy of " + p + " to all residents in " + t + " and aimed to complete it by " + d + ". Since " + s + ", " + inSearch + " have submitted " + b + " bills.");
                    displaySearch.setText(message);}
                }

             });*/


       /*submitRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=inputUsername.getText().toString();
                String pword=inputPassword.getText().toString();
                String checked = " ";
                DbHandler dbHandler=new DbHandler(MainActivity.this);
                checked=dbHandler.loginCheck(uname, pword);
                ArrayList<HashMap<String, String>> userInfo=dbHandler.GetPoliticianIdNNamebyusername(uname);

                switch(checked){
                    case "checked":
                        String name = userInfo.get(0).get("name");
                        String id = userInfo.get(0).get("id");
                        Intent intent= new Intent(MainActivity.this, UserPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("key_name",name);
                        intent.putExtra("Key_id",id);
                        startActivity(intent);
                        finish();
                        break;
                    case "Login Failed":
                        Toast.makeText(getApplicationContext(),checked,Toast.LENGTH_LONG).show();
                        break;
                    case "cannot find this username":
                        Toast.makeText(getApplicationContext(),checked,Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });*/


//Intent intent = new Intent(MainActivity.this, UserPage.class);

// startActivity(intent);