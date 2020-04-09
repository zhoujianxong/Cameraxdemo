package com.blue.cameraxdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blue.cameraxdemo.db.DBManager;

public class ImportDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_d_b);
        importDb();
    }

    /**
     * 导入外部数据库
     */
    private void importDb() {
        DBManager dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
    }
}
