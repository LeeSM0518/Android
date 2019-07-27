package com.example.multimemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.multimemo.BasicInfo;

public class MemoDatabase {

    public static final String TAG = "MemoDatabase";

    // 싱글톤 인스턴스
    private static MemoDatabase database;

    public static String TABLE_MEMO = "MEMO";

    public static String TABLE_PHOTO = "PHOTO";

    public static String TABLE_VIDEO = "VIDEO";

    public static String TABLE_VOICE = "VOICE";

    public static String TABLE_HANDWRITING = "HANDWRITING";

    public static int DATABASE_VERSION = 1;


    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, BasicInfo.DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            println("creating database [" + BasicInfo.DATABASE_NAME + "].");

            println("creating table [" + TABLE_MEMO + "].");

            String DROP_SQL = "drop table if exists " + TABLE_MEMO;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.d(TAG, "Exception in Drop_SQL", ex);
            }

            String CREATE_SQL = "create table " + TABLE_MEMO + "("
                    + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + " INPUT_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + " CONTENT_TEXT TEXT DEFAULT '', "
                    + " ID_PHOTO INTEGER, "
                    + " ID_VIDEO INTEGER, "
                    + " ID_VOICE INTEGER, "
                    + " ID_HANDWRITING INTEGER, "
                    + " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.d(TAG, "Exception in CREATE_SQL", ex);
            }

            println("creating table [" + TABLE_PHOTO + "].");

            DROP_SQL = "drop table if exists " + TABLE_PHOTO;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            CREATE_SQL = "CREATE TABLE " + TABLE_PHOTO + "("
                    + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + " URI TEXT, "
                    + " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            String CREATE_INDEX_SQL = "create index " + TABLE_PHOTO + "_IDX ON " + TABLE_PHOTO + "("
                    + "URI" + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", ex);
            }

            println("creating table [" + TABLE_VIDEO + "].");

            DROP_SQL = "drop table if exists " + TABLE_VIDEO;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            CREATE_SQL = "create table " + TABLE_VIDEO + "("
                    + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + " URI TEXT, "
                    + " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            CREATE_INDEX_SQL = "create index " + TABLE_VIDEO + "_IDX ON " + TABLE_VIDEO + "("
                    + "URI" + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", ex);
            }

            println("creating table [" + TABLE_VOICE + "].");

            DROP_SQL = "drop table if exists " + TABLE_VOICE;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            CREATE_SQL = "create table " + TABLE_VOICE + "("
                    + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + " URI TEXT, "
                    + " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            CREATE_INDEX_SQL = "create index " + TABLE_VOICE + "_IDX ON " + TABLE_VOICE + "("
                    + "URI" + ")";

            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", ex);
            }

            println("creating table [" + TABLE_HANDWRITING + "].");

            DROP_SQL = "drop table if exists " + TABLE_HANDWRITING;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            CREATE_SQL = "create table " + TABLE_HANDWRITING + "("
                    + " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + " URI TEXT, "
                    + " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            CREATE_INDEX_SQL = "create index " + TABLE_HANDWRITING + "_IDX ON " + TABLE_HANDWRITING
                    + "(" + "URI" + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", ex);
            }
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + BasicInfo.DATABASE_NAME + "].");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            println("Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        }
    }

    private void println(String msg) {
        Log.d(TAG, msg);
    }

}