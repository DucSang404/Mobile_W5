package com.ducsang.listview.model;

public class BaiHat {
    private String code;
    private String title;
    private String lyric;
    private String artist;
    private int imageResId; // Thêm thuộc tính để lưu ảnh


    public BaiHat(String code, String title, String lyric, String artist, int imageResId) {
        this.code = code;
        this.title = title;
        this.lyric = lyric;
        this.artist = artist;
        this.imageResId = imageResId;

    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public String getLyric() { return lyric; }
    public String getArtist() { return artist; }
    public int getImageResId() { return imageResId; } // Getter mới

}
