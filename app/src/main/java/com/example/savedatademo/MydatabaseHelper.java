package com.example.savedatademo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/*
    创建数据库
 */
public class MydatabaseHelper extends SQLiteOpenHelper {

    private static final String Create_Book = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text," +
            "category_id integer)";

    private static final String Create_Category =" create table Category ("+
            "id integer primary key autoincrement,"+
            "category_name text,"+
            "category_code integer)";


    private Context mContext;
    public MydatabaseHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Book);
        sqLiteDatabase.execSQL(Create_Category);
        Toast.makeText(mContext,"Create sucessded",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
