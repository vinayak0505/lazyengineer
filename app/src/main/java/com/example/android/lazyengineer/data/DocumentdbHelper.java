package com.example.android.lazyengineer.data;
import com.example.android.lazyengineer.data.DocumentContract.DocumentEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DocumentdbHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "document.db";
    private static final int DATABASE_VERSION = 1;

    public DocumentdbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + DocumentEntry.TABLE_NAME + " ("
                + DocumentEntry._ID + " INTEGER PRIMARY KEY, "
                + DocumentEntry.COLUMN_URI + " INTEGER);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
