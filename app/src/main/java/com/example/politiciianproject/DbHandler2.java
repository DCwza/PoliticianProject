package com.example.politiciianproject;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler2 extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "promisesdb";
    private static final String TABLE_Promise = "promisedetails";
    private static final String KEY_ID = "id";
    private static final String KEY_TARGET = "target";
    private static final String KEY_POLICY = "policy";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_START_YEAR = "startYear";
    private static final String KEY_POLITICIANID = "politicianId";
    private static final String KEY_BILLS_RESULTS = "billsResults";


    public DbHandler2(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE = "CREATE TABLE " + TABLE_Promise + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TARGET + " TEXT,"
                + KEY_POLICY + " TEXT,"
                + KEY_DEADLINE + " TEXT,"
                + KEY_START_YEAR + " TEXT,"
                + KEY_BILLS_RESULTS + " TEXT,"
                + KEY_POLITICIANID + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Promise);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details

    void insertPromiseDetails(String target, String policy,String deadLine, String startYear, String billsResults, String politicianId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_TARGET, target);
        cVals.put(KEY_POLICY, policy);
        cVals.put(KEY_DEADLINE, deadLine);
        cVals.put(KEY_START_YEAR, startYear);
        cVals.put(KEY_BILLS_RESULTS, billsResults);
        cVals.put(KEY_POLITICIANID, politicianId);
        // Adding new User Details
        long newRowId = db.insert(TABLE_Promise,null, cVals);
        db.close();}

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPromises(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT target, policy, deadline, startYear, billsResults, politicianId FROM "+ TABLE_Promise;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("target",cursor.getString(cursor.getColumnIndex(KEY_TARGET)));
            user.put("policy",cursor.getString(cursor.getColumnIndex(KEY_POLICY)));
            user.put("deadline",cursor.getString(cursor.getColumnIndex(KEY_DEADLINE)));
            user.put("startYear",cursor.getString(cursor.getColumnIndex(KEY_START_YEAR)));
            user.put("billsResults",cursor.getString(cursor.getColumnIndex(KEY_BILLS_RESULTS)));
            user.put("politicianId",cursor.getString(cursor.getColumnIndex(KEY_POLITICIANID)));
            userList.add(user);
        }
        return  userList;
    }
    // Get promise Details based id
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPromiseById(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT target, policy, deadline, startYear, billsResults, politicianID FROM "+ TABLE_Promise;
        Cursor cursor = db.query(TABLE_Promise, new String[]{KEY_TARGET, KEY_POLICY, KEY_DEADLINE, KEY_START_YEAR, KEY_BILLS_RESULTS, KEY_POLITICIANID}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("target",cursor.getString(cursor.getColumnIndex(KEY_TARGET)));
            user.put("policy",cursor.getString(cursor.getColumnIndex(KEY_POLICY)));
            user.put("deadline",cursor.getString(cursor.getColumnIndex(KEY_DEADLINE)));
            user.put("startYear",cursor.getString(cursor.getColumnIndex(KEY_START_YEAR)));
            user.put("billsResults",cursor.getString(cursor.getColumnIndex(KEY_BILLS_RESULTS)));
            user.put("politicianId",cursor.getString(cursor.getColumnIndex(KEY_POLITICIANID)));
            userList.add(user);
        }
        return  userList;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetPromiseByPoliticianId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, target, policy, deadline, startYear, billsResults FROM "+ TABLE_Promise;
        Cursor cursor = db.query(TABLE_Promise, new String[]{KEY_ID, KEY_TARGET, KEY_POLICY, KEY_DEADLINE, KEY_START_YEAR, KEY_BILLS_RESULTS}, KEY_POLITICIANID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("target",cursor.getString(cursor.getColumnIndex(KEY_TARGET)));
            user.put("policy",cursor.getString(cursor.getColumnIndex(KEY_POLICY)));
            user.put("deadline",cursor.getString(cursor.getColumnIndex(KEY_DEADLINE)));
            user.put("startYear",cursor.getString(cursor.getColumnIndex(KEY_START_YEAR)));
            user.put("billsResults",cursor.getString(cursor.getColumnIndex(KEY_BILLS_RESULTS)));

            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeletePromise(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Promise, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdatePromiseDetails(String target, String policy,String deadLine, String startYear, String billsResults, String politicianId, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_TARGET, target);
        cVals.put(KEY_POLICY, policy);
        cVals.put(KEY_DEADLINE, deadLine);
        cVals.put(KEY_START_YEAR, startYear);
        cVals.put(KEY_BILLS_RESULTS, billsResults);
        cVals.put(KEY_POLITICIANID, politicianId);
        int count = db.update(TABLE_Promise, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}



