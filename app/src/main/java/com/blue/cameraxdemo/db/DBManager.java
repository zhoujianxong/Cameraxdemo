package com.blue.cameraxdemo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import com.blue.cameraxdemo.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Android Studio.
 *
 * @author zjx
 * @date 2020/4/9
 */
public class DBManager {
    private final int BUFFER_SIZE = 400000;
//    public static final String DB_NAME = "countries.db";
public static final String DB_NAME = "cpcz.db";
    public static final String PACKAGE_NAME = "com.blue.cameraxdemo";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;

    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
    }

    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            if (!(new File(dbfile).exists())) {
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.cpcz);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }


            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                    null);
            Cursor cursor=db.query("kincai_address_province",null,null,null,null,null,null);
            int i=0;
            while (cursor.moveToNext()){
                Log.v("TAG","列表名： "+ Arrays.toString(cursor.getColumnNames())+cursor.getString(cursor.getColumnIndex("province_name")));
                i++;
            }

            return db;
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }
    //do something else here
    public void closeDatabase() {
        this.database.close();
    }
}
