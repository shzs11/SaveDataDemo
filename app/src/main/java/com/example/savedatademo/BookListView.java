package com.example.savedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BookListView extends AppCompatActivity {

    private MydatabaseHelper dbHelper;
    private ArrayList data=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_view);
        ListView listView =(ListView) findViewById(R.id.list_view);
        Button select =(Button) findViewById(R.id.add_book);

        //添加点击事件
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookListView.this,AddBook.class);
                startActivity(intent);
            }
        });

        //获取数据库的数据
        dbHelper = new MydatabaseHelper(this, "BookStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        int i =0;
        cursor = db.rawQuery("select name,price,Category_name " +
                "from Book b,Category c " +
                "where b.Category_id = c.id", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            Double price = cursor.getDouble(1);
            String  cate_name = cursor.getString(2);
            String a = name+"\t"+String.valueOf(price)+"\t"+cate_name;
            data.add(a);
        }
        cursor.close();
        //将数据传递到listview里去
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookListView.this,
                android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }
}