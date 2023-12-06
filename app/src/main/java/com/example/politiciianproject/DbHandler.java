package com.example.politiciianproject;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.PackageManagerCompat;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.text.UStringsKt;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "politiciansdb";
    private static final String TABLE_Politician = "politiciandetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_RIDING_ZONE = "ridingZone";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PHONE ="phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_PASSWORD = "password";


    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Politician + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_RIDING_ZONE + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Politician);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertPoliticianDetails(String name, String ridingZone, String title,String phone, String email, String userName, String password){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_RIDING_ZONE, ridingZone);
        cValues.put(KEY_TITLE, title);
        cValues.put(KEY_PHONE, phone);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_USERNAME, userName);
        cValues.put(KEY_PASSWORD, password);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Politician,null, cValues);
        db.close();
    }
    // Get User Details
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPoliticians(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, ridingZone, title, phone, email, userName, password FROM "+ TABLE_Politician;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("ridingZone",cursor.getString(cursor.getColumnIndex(KEY_RIDING_ZONE)));
            user.put("title",cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            user.put("phone",cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            user.put("email",cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.put("userName",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            user.put("password",cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPoliticianById(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, ridingZone, title, phone, email, userName, password FROM "+ TABLE_Politician;
        Cursor cursor = db.query(TABLE_Politician, new String[]{KEY_NAME, KEY_RIDING_ZONE, KEY_TITLE, KEY_PHONE, KEY_EMAIL, KEY_USERNAME, KEY_PASSWORD}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("ridingZone",cursor.getString(cursor.getColumnIndex(KEY_RIDING_ZONE)));
            user.put("title",cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            user.put("phone",cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            user.put("email",cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.put("userName",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            user.put("password",cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));

            userList.add(user);
        }
        return  userList;
    }
    //Get Politician id based on name
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPoliticianIdByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id FROM "+ TABLE_Politician + " WHERE name =" +name+";";
        Cursor cursor = db.query(TABLE_Politician, new String[]{KEY_ID}, KEY_NAME+ "=?",new String[]{name},null, null, null, null);
        if(cursor ==null || cursor.getCount()==0){
            HashMap<String,String>user=new HashMap<>();
            user.put("id","0");
            userList.add(user);
        };
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            userList.add(user);
        }

        return  userList;
    }

    // Delete User Details
    public void DeletePolitician(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Politician, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdatePoliticianDetails(String ridingZone, String title,String phone, String email, String userName, String password, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_RIDING_ZONE, ridingZone);
        cVals.put(KEY_TITLE, title);
        cVals.put(KEY_PHONE, phone);
        cVals.put(KEY_EMAIL, email);
        cVals.put(KEY_USERNAME, userName);
        cVals.put(KEY_PASSWORD, password);
        int count = db.update(TABLE_Politician, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }

    @SuppressLint("Range")
    public String loginCheck(String username, String password) {
        String check = " ";
        String pword = "initialized";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT password FROM " + TABLE_Politician + " WHERE userName =" + username + ";";
        Cursor cursor = db.query(TABLE_Politician, new String[]{KEY_PASSWORD}, KEY_USERNAME + "=?", new String[]{username}, null, null, null, null);
        if(cursor!=null && cursor.getCount()>=1){
        if (cursor.moveToNext()) {
            pword = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            if (pword.equals(password)) {
                check = "checked";
            }else{
                check = "Login Failed";
            }
        }}else{ check ="cannot find this username";}


        return check;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPoliticianIdNNamebyusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, id FROM "+ TABLE_Politician + " WHERE userName =" + username + ";";
        Cursor cursor = db.query(TABLE_Politician, new String[]{KEY_NAME, KEY_ID}, KEY_USERNAME+ "=?",new String[]{username},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            userList.add(user);
        }
        return  userList;
    }
}



