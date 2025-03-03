package com.ducsang.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ducsang.listview.R;
import com.ducsang.listview.model.BaiHat;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_BAI_HAT = 0;
    private static final int ITEM_HEADER = 1;

    private Context context;
    private List<Object> songList;
    private int lastPosition = -1; // Biến để kiểm soát animation

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLyric, tvArtist, tvCode;
        ImageView imgSong;


        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLyric = itemView.findViewById(R.id.tv_lyric);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            tvCode = itemView.findViewById(R.id.tv_code);
            imgSong = itemView.findViewById(R.id.img_song);
        }
    }

    // ViewHolder cho header
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tv_header);
        }
    }

    public RecyclerViewAdapter(Context context, List<Object> songList) {
        this.context = context;
        this.songList = songList;
    }

    // Xác định kiểu item tại vị trí position
    @Override
    public int getItemViewType(int position) {
        if (songList.get(position) instanceof BaiHat) {
            return ITEM_BAI_HAT;
        } else {
            return ITEM_HEADER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_BAI_HAT) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_item_song, parent, false);
            return new SongViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.row_item_header, parent, false);
            return new HeaderViewHolder(view);
        }    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof SongViewHolder) {
            BaiHat song = (BaiHat) songList.get(position);
            SongViewHolder songHolder = (SongViewHolder) holder;

            songHolder.tvTitle.setText(song.getTitle());
            songHolder.tvLyric.setText(song.getLyric());
            songHolder.tvArtist.setText(song.getArtist());
            songHolder.tvCode.setText(song.getCode());
            if (song.getImageResId() != 0) {
                songHolder.imgSong.setImageResource(song.getImageResId());
            }
            // Áp dụng animation khi xuất hiện
            setAnimation(songHolder.itemView, position);

            // Nhấn vào item để thay đổi animation
            songHolder.itemView.setOnClickListener(v -> {
                Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.item_animation_rotate);
                v.startAnimation(rotateAnim);
            });

            songHolder.itemView.setOnLongClickListener(v -> {
                removeItem(position);
                return true;
            });

        } else if (holder instanceof HeaderViewHolder) {
            String headerTitle = (String) songList.get(position);
            ((HeaderViewHolder) holder).tvHeader.setText(headerTitle);
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    // Xóa item khỏi danh sách và cập nhật RecyclerView
    public void removeItem(int position) {
        if (position >= 0 && position < songList.size()) {
            songList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, songList.size());
        }
    }


    // Áp dụng animation cho item
    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_fade_in);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }
}
