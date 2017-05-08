package com.example.karthikvarma.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by Karthik Varma on 4/15/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    //Database details for user table

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CalorieTracker";
    private static final String TABLE_USER_DETAIL = "userDetails";
    private static final String NAME = "name";
    private static final String UNAME = "username";
    private static final String AGE = "age";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String BMI = "bmi";

    //Database details for food list table
    private static final String TABLE_FOOD_LIST = "foodList";
    private static final String FNO = "foodNumber";
    private static final String FNAME = "foodName";
    private static final String CALORIES = "calories";
    private static final String FTYPE = "foodType";

    //Database for exercise list
    private static final String TABLE_EXERCISE_LIST = "exerciseList";
    private static final String ENO = "exerciseNumber";
    private static final String ENAME = "exerciseName";
    private static final String CALORIES_EXERCISE = "calories";
    private static final String ETYPE = "exerciseType";

    //DATABASE FOR USER LIST
    private static final String TABLE_USER_LIST = "userList";
    private static final String USERNAME = "username";
    private static final String PASSWORD_USER = "password";
    String username;

    //Daily food progress
    private static final String TABLE_USER_DAILY_FOOD = "dailyFood";
    private static final String DATE = "date";

    //Daily Exercise progress
    private static final String TABLE_USER_DAILY_EXERCISE = "dailyExercise";

    //Daily Water table
    private static final String TABLE_USER_DAILY_WATER = "dailyWater";
    private static final String WATER_SIZE = "waterSize";

    //Daily challenge
    private static final String TABLE_USER_CHALLENGE = "dailyChallenge";
    private static final String CHALLENGE = "challenge";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //EDIT PROFILE
    public long editProfile(String username,String name, int age, int height, int weight, double bmi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("name",name);
        values.put("age",age);
        values.put("height",height);
        values.put("weight",weight);
        values.put("bmi",bmi);

        long result = db.insertWithOnConflict(TABLE_USER_DETAIL,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        return result;
    }

    //Insert Food
    public long addFood(String ftype,String fname,int calories)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentvalues = new ContentValues();
        contentvalues.put("foodType",ftype);
        contentvalues.put("foodName",fname);
        contentvalues.put("calories",calories);
        long result = db.insert(TABLE_FOOD_LIST,null,contentvalues);
        db.close();
        return result;
    }



    //Insert Exercise
    public long addExercise(String etype, String ename, int calories,int bmi1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("exerciseType",etype);
        content.put("exerciseName",ename);
        content.put("calories",calories);
        content.put("bmi1",bmi1);
        long result = db.insert(TABLE_EXERCISE_LIST,null,content);
        db.close();
        return result;
    }

    //insert daily food data
    public long addDailyFood(String username, String foodname, int calories,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("foodName",foodname);
        content.put("calories",calories);
        content.put("date",date);
        long result = db.insert(TABLE_USER_DAILY_FOOD,null,content);
        db.close();
        return result;
    }

    //insert daily water intake
    //insert daily food data
    public long addDailywater(String username, int water,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("waterSize",water);
        content.put("date",date);
        long result = db.insertWithOnConflict(TABLE_USER_DAILY_WATER,null,content,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        return result;
    }

    //insert to daily exercise
    public long addDailyExercise(String username, String exercisename, int calories, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("exerciseName", exercisename);
        content.put("calories",calories);
        content.put("date",date);
        long result = db.insert(TABLE_USER_DAILY_EXERCISE,null,content);
        db.close();
        return result;
    }

    //insert to daily challenge
    public long addDailyChallenge(String username, int calories, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("challenge", calories);
        content.put("date",date);
        db.delete(TABLE_USER_CHALLENGE,"username = ?",new String[] {username});
        long result = db.insertWithOnConflict(TABLE_USER_CHALLENGE,null,content,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        return result;
    }


    //insert contact objects to database
    public void insertUser(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",password);
        db.insert(TABLE_USER_LIST,null,values);
        db.close();

    }

    //get all food data
    public Cursor getAllFoodData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM foodList",null);
        return res;
    }

    //get all individual customer food
    public Cursor getAllCustomerFood()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM dailyFood",null);
        return res;
    }

    //get food data
    public Cursor getFoodCalories()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT foodName,calories FROM foodList",null);
        return res;

    }

    //get all individual customer exerciser
    public Cursor getAllCustomerExercise()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM dailyExercise",null);
        return res;
    }


    //get all users
    public Cursor getAllUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM userList",null);
        return res;
    }


    //get all exercise data
    public Cursor getAllExerciseData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM exerciseList",null);
        return res;
    }


    //searching for bmi
    public double searchBMI(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, bmi FROM " + TABLE_USER_DETAIL;
        Cursor cursor = db.rawQuery(query,null);
        double b;
        String a;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b = cursor.getDouble(1);
                if(a.equals(name))
                {
                    return b;
                }

            }while(cursor.moveToNext());
        }
        return -1;
    }


    //get all spinner data
    public ArrayList<String> getAllFood()
    {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT foodName FROM " + TABLE_FOOD_LIST;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String foodname = cursor.getString(cursor.getColumnIndex("foodName"));
                    list.add(foodname);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            db.endTransaction();
            db.close();
        }
        return list;
    }

    //to check food calories
    public int checkCalories(String foodName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT foodName, calories FROM " + TABLE_FOOD_LIST;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(foodName))
                {
                    b = cursor.getString(1);
                    int c =Integer.parseInt(b);
                    return c;

                }
            }
            while(cursor.moveToNext());
        }
        return -1;

    }

    //to check food calories
    public int checkCaloriesExercise(String exerciseName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT exerciseName, calories FROM " + TABLE_EXERCISE_LIST;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(exerciseName))
                {
                    b = cursor.getString(1);
                    int c =Integer.parseInt(b);
                    return c;

                }
            }
            while(cursor.moveToNext());
        }
        return -1;

    }

    //My daily progress
    //to check food calories
    public int checkDailyFood(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, calories FROM " + TABLE_USER_DAILY_FOOD;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        int c,all = 0;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    c =Integer.parseInt(b);
                    all = all +  c;
                }
            }
            while(cursor.moveToNext());
        }
        return all;

    }

    public int checkDailyWater(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, waterSize FROM " + TABLE_USER_DAILY_WATER;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        int c,all = 0;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    c =Integer.parseInt(b);
                    all = all +  c;
                }
            }
            while(cursor.moveToNext());
        }
        return all;

    }

    //to get bmi
    public double getbmi(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, bmi FROM " + TABLE_USER_DETAIL;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        double c;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    c = Double.parseDouble(b);
                    return c;
                }
            }
            while(cursor.moveToNext());
        }
        return -1;

    }


    //to check exercise calories
    public int checkDailyExercise(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, calories FROM " + TABLE_USER_DAILY_EXERCISE;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        int c,all = 0;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    c =Integer.parseInt(b);
                    all = all +  c;
                }
            }
            while(cursor.moveToNext());
        }
        return all;

    }

    //to check exercise calories
    public int checkDailyChallenge(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, challenge FROM " + TABLE_USER_CHALLENGE;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        int c,all = 0;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    c =Integer.parseInt(b);
                    all = all +  c;
                }
            }
            while(cursor.moveToNext());
        }
        return all;

    }


    public String searchPass(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, password FROM " + TABLE_USER_LIST;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                if(a.equals(name))
                {
                    b = cursor.getString(1);
                    username = name;
                    return b;

                }
            }
            while(cursor.moveToNext());
        }
        return "xxx";
    }

    public String getUsername(){
        String name = username;
        return name;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //create user table
        String CREATE_USER_DETAILS_TABLE = "CREATE TABLE " + TABLE_USER_DETAIL + "("
                +NAME + " TEXT,"
                +AGE + " INT,"
                +UNAME + " TEXT PRIMARY KEY,"
                +HEIGHT + " INT,"
                +WEIGHT + " INT,"
                +BMI + " DOUBLE"
                + ")";

        db.execSQL(CREATE_USER_DETAILS_TABLE);


        //create User Table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER_LIST + "("
                + USERNAME + " TEXT,"
                + PASSWORD_USER + " TEXT"
                + ")";

        db.execSQL(CREATE_USER_TABLE);

        //create food list table
        String CREATE_FOOD_LIST = "CREATE TABLE " + TABLE_FOOD_LIST + "("
                + FNAME + " TEXT,"
                + FTYPE + " TEXT,"
                + CALORIES + " INT"
                + ")";

        db.execSQL(CREATE_FOOD_LIST);

        //Create Exercise List Table
        String CREATE_EXERCISE_LIST = "CREATE TABLE " + TABLE_EXERCISE_LIST + "("
                + ENAME + " TEXT,"
                + ETYPE + " TEXT,"
                + CALORIES_EXERCISE + " INT,"
                + "bmi1 INT"
                + ")";

        db.execSQL(CREATE_EXERCISE_LIST);

        //Create User Daily food intake
        String CREATE_DAILY_FOOD_LIST = "CREATE TABLE " + TABLE_USER_DAILY_FOOD + "("
                + USERNAME + " TEXT,"
                + FNAME + " TEXT,"
                + CALORIES + " INT,"
                + DATE + " STRING"
                + ")";

        db.execSQL(CREATE_DAILY_FOOD_LIST);

        //Create User daily exercise
        String CREATE_DAILY_EXERCISE_LIST = "CREATE TABLE " + TABLE_USER_DAILY_EXERCISE + "("
                + USERNAME + " TEXT PRIMARY KEY,"
                + ENAME + " TEXT,"
                + CALORIES_EXERCISE + " INT,"
                + DATE + " STRING"
                + ")";

        db.execSQL(CREATE_DAILY_EXERCISE_LIST);

        //Create user daily water
        String CREATE_DAILY_WATER = "CREATE TABLE " + TABLE_USER_DAILY_WATER + "("
                + USERNAME + " TEXT PRIMARY KEY,"
                + WATER_SIZE + " INT,"
                + DATE + " STRING"
                + ")";

        db.execSQL(CREATE_DAILY_WATER);

        //Create Daily challenge table
        String CREATE_DAILY_CHALLENGE = "CREATE TABLE " + TABLE_USER_CHALLENGE + "("
                + USERNAME + " TEXT PRIMARY KEY,"
                + CHALLENGE + " INT,"
                + DATE + " STRING"
                + ")";

        db.execSQL(CREATE_DAILY_CHALLENGE);
    }

    //delete food from list
    public Integer deleteFoodData(String foodN)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_FOOD_LIST,"foodName = ?",new String[] {foodN});
    }

    public Integer deleteCustomerFood(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER_DAILY_FOOD,"username = ?",new String[] {date});
    }

    public Integer deleteCustomerExercise(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER_DAILY_EXERCISE,"username = ?",new String[] {date});
    }


    //delete exercise data
    public Integer deleteExerciseData(String Exercise)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_EXERCISE_LIST,"exerciseName = ?", new String[] {Exercise});
    }

    //delete user from user list
    public Integer deleteUserData(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER_LIST,"username = ?", new String[] {username});
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER_DETAIL);
        onCreate(db);
    }
}
