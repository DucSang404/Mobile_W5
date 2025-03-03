package com.ducsang.listview;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ducsang.listview.adapter.RecyclerViewAdapter;
import com.ducsang.listview.model.BaiHat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rvSongs;
    private RecyclerViewAdapter songAdapter;
    private List<Object> songList;
    private Button btnAddSong;
    private int songCount = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recylerview);

        btnAddSong = findViewById(R.id.btnAddSong);
        rvSongs = findViewById(R.id.rv_songs);
        songList = new ArrayList<>();

        songList.add("Nhạc Quốc Tế");
        songList.add(new BaiHat("S001", "Shape of You", "I'm in love with the shape of you", "Ed Sheeran", R.drawable.shape_of_you));
        songList.add(new BaiHat("S002", "How Long", "I said, ooh, I'm blinded by the lights", "Charlie Puth", R.drawable.how_long));


        songList.add("Nhạc Việt Nam");
        songList.add(new BaiHat("S003", "Mất kết nối", "Nỗi nhớ em trong đêm thật dài", "Dương Domic", R.drawable.mat_ket_noi));
        songList.add(new BaiHat("S004", "Exit Sign", "Anh không nhớ nổi lần cuối cùng anh nhìn vào mắt em đó là từ bao giờ\n", "Jack", R.drawable.exit_sign));

        songAdapter = new RecyclerViewAdapter(this, songList);
        rvSongs.setAdapter(songAdapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btnAddSong.setOnClickListener(v -> addNewSong());
    }

    private void addNewSong() {
        if (songAdapter == null) {
            Log.e("RecyclerViewActivity", "Adapter chưa được khởi tạo!");
            return;
        }

        songCount++;
        BaiHat newSong = new BaiHat("S00" + songCount, "Bài Hát Mới " + songCount, "Lời bài hát mẫu", "Ca sĩ Mới", R.drawable.son_tung);
        songList.add(newSong);
        songAdapter.notifyItemInserted(songList.size() - 1);

        // Kiểm tra ViewHolder trước khi gọi animation
        RecyclerView.ViewHolder viewHolder = rvSongs.findViewHolderForAdapterPosition(songList.size() - 1);
        if (viewHolder != null) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.item_animation_fade_in);
            viewHolder.itemView.startAnimation(animation);
        } else {
            Log.e("RecyclerViewActivity", "ViewHolder null, không thể chạy animation!");
        }
    }

}
