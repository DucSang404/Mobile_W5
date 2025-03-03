package com.ducsang.listview;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ducsang.listview.adapter.MonHocAdapterListView;
import com.ducsang.listview.model.MonHoc;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
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
        setContentView(R.layout.listview);

        EditText editText1;
        Button btnNhap;

        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);

        Button btnCapNhat;
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);


        listView = (ListView) findViewById(R.id.listview);
        editText1 = (EditText) findViewById(R.id.editText1);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);

        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Spring","Spring Boot",R.drawable.spring));
        arrayList.add(new MonHoc("React","React JS",R.drawable.react));
        arrayList.add(new MonHoc("Node","Node JS",R.drawable.nodejs));
        arrayList.add(new MonHoc("Golang","Golang" ,R.drawable.golang));

        adapter = new MonHocAdapterListView(ListViewActivity.this, R.layout.row_monhoc, arrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "Bạn đang chọn vào mục "+position+"-"+arrayList.get(position).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        EditText finalEditText = editText1;
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = finalEditText.getText().toString();
                arrayList.add(new MonHoc(name, "New desc", R.drawable.spring));
                adapter.notifyDataSetChanged();
            }
        });

        EditText finalEditText1 = editText1;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finalEditText1.setText(arrayList.get(position).getName());
                vitri = position;
            }
        });


        EditText finalEditText2 = editText1;
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonHoc monHoc = arrayList.get(vitri);
                monHoc.setName(finalEditText2.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
