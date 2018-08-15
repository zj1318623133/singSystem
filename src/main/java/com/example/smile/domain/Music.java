package com.example.smile.domain;

public class Music {

    private String musicName ;
    private String singer;
    private String album;
    private int songCount;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicName='" + musicName + '\'' +
                ", singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", songCount=" + songCount +
                '}';
    }
}
