package com.blue.cameraxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.blue.cameraxdemo.adapter.AsynciAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsynclistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynclist);
        recyclerView = findViewById(R.id.id_recycleView);

        AsynciAdapter asynciAdapter = new AsynciAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(asynciAdapter);


        findViewById(R.id.id_button).setOnClickListener(v -> {
            List<String> data = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                data.add("" + new Random().nextInt() * i);
            }
            asynciAdapter.submitList(data);
        });
    }
}
