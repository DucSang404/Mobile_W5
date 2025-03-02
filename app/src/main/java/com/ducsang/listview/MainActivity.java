package com.ducsang.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ducsang.listview.adapter.MonHocAdapterListView;
import com.ducsang.listview.model.MonHoc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int vitri = -1;

    ListView listView;
    ArrayList<MonHoc> arrayList;
    MonHocAdapterListView adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnListView).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListViewActivity.class));
        });

        findViewById(R.id.btnGridView).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GridViewActivity.class));
        });

        findViewById(R.id.btnRecyclerView).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
        });
    }

}