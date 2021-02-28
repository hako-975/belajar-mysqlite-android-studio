package com.example.mylisttoread;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MyListToRead.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_REASON = "reason_to_read";
    private static final String COLUMN_LAST_READ_PAGES = "last_read_pages";



     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_REASON + " TEXT, " +
                COLUMN_LAST_READ_PAGES + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addList(String title, String reason, int last_read_pages) {
        SQLiteDatabase db   = this.getWritableDatabase();
        ContentValues cv    = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_REASON, reason);
        cv.put(COLUMN_LAST_READ_PAGES, last_read_pages);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
        {
            Toast.makeText(context, "Failed to add new list", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully to added new list", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String title, String reason, String last_read_pages)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_REASON, reason);
        cv.put(COLUMN_LAST_READ_PAGES, last_read_pages);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if (result == -1)
        {
            Toast.makeText(context, "Failed to update list", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully to updated list", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context, "Failed to delete data.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Successfully to deleted data.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
