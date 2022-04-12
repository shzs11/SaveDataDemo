package com.example.savedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SqLiteSaveActivity extends AppCompatActivity {

    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_save);
        dbHelper = new MydatabaseHelper(this,"BookStore.db",null,1);
        Button createdb = (Button) findViewById(R.id.create_database);
        Button insertBook = (Button) findViewById(R.id.insert_data);
        createdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();

            }
        });

        insertBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
              /*  db.execSQL("insert into Book(name,author,pages,price,category_id) values(?,?,?,?,?)",
                            new String[]{"《半生缘》","张爱玲","352","23.0","2"});*/
                db.execSQL("insert into Category(category_name,category_code) values(?,?)",
                            new String[]{"爱情类文学","#112"});
                db.execSQL("delete ");
            }
        });

    }
}