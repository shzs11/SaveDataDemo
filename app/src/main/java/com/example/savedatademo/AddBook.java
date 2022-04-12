package com.example.savedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddBook extends AppCompatActivity {

    private MydatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //连接数据库
        dbHelper = new MydatabaseHelper(this, "BookStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //获取编辑框
        EditText book_name = (EditText) findViewById(R.id.book_name);
        EditText author = (EditText) findViewById(R.id.author);
        EditText price = (EditText) findViewById(R.id.price);
        EditText pages = (EditText) findViewById(R.id.pages);
        Spinner category = (Spinner) findViewById(R.id.category);

        Button addBook = (Button) findViewById(R.id.addBook);
        //添加增加按钮的点击事件
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转换文本框的数据
                String bookName = book_name.getText().toString();
                String bookAuthor = author.getText().toString();
                String bookPrice = price.getText().toString();
                String bookPages = pages.getText().toString();
                //获取书本的类别
                String a = (String)category.getSelectedItem();
                int id = 0;
                //查询书本类别的编号
                Cursor cursor = db.rawQuery("select id from Category where category_name=?",new String[]{a});
                if(cursor.moveToNext()){
                    id = cursor.getInt(0);
                }
                //插入数据
                db.execSQL("insert into Book(author,price,pages,name,category_id) values(?,?,?,?,?)",
                        new String[]{bookAuthor,bookPrice,bookPages,bookName,String.valueOf(id)});

                //跳转到首页
                Intent intent = new Intent(AddBook.this,BookListView.class);
                startActivity(intent);

            }
        });

    }
}