package com.example.myapplicationtablet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ViewArticles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_articles);
        final WebView browser = findViewById(R.id.webBrowser);
        Bundle arguments = getIntent().getExtras();
        final ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        SQLiteDatabase db;
        SimpleCursorAdapter userAdapter;

          Article article = null;
        if (arguments != null) {
            article = (Article) arguments.getSerializable(Article.class.getSimpleName());

            browser.loadUrl(article.getUrl());

        }
        final String articleId = article.getId().toString();
        final String articleTitle = article.getTitle();
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ValueCallback<String> backNameRes = null;
                    String backName = null;
                    getDir(articleId,  MODE_PRIVATE);
                    File file = getFileStreamPath(articleId+".MHT");
                    String articlePath = "file:"+file.getAbsolutePath();
                    FileOutputStream fos = null;
                    try {

                        fos = openFileOutput("page.MHT", MODE_PRIVATE);

                        browser.saveWebArchive(file.getAbsolutePath(),true,null);
                        progressBar.setVisibility(View.VISIBLE);
                        //SystemClock.currentThreadTimeMillis();
                        SystemClock.sleep(60000);

                       //do {
                        //    backNameRes.onReceiveValue(null);

                      //  }while(backNameRes=null);

                    } catch (Exception e) {

                      //  Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                    } finally {
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (Exception e) {

                        }
                    }

                    DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                    // открываем подключение
                    SQLiteDatabase db = databaseHelper.getReadableDatabase();
                    Cursor userCursor;
                    userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
                    ContentValues cv = new ContentValues();
                    Integer i = userCursor.getCount();
                    cv.put(databaseHelper.itemId,articleId);
                    long result = db.insert(DatabaseHelper.TABLE, null, cv);
                    if(result>0){
                        cv.put(databaseHelper.title,articleTitle);
                         result = db.insert(DatabaseHelper.TABLE, null, cv);
                        if(result>0){
                            cv.put(databaseHelper.path,articlePath);
                            result = db.insert(DatabaseHelper.TABLE, null, cv);
                            if(result>0){
                                db.close();
                                userCursor.close();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            });
    }
}
