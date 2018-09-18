package com.example.smile.domain;

import java.sql.Date;

public class Music {

    private String musicName ;
    private String singer;
    private String album;
    private int worshipsCount;
    private int singsCount;
    private String songScore;
    private Date createDate;

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

    public int getWorshipsCount() {
        return worshipsCount;
    }

    public void setWorshipsCount(int worshipsCount) {
        this.worshipsCount = worshipsCount;
    }

    public int getSingsCount() {
        return singsCount;
    }

    public void setSingsCount(int singsCount) {
        this.singsCount = singsCount;
    }

    public String getSongScore() {
        return songScore;
    }

    public void setSongScore(String songScore) {
        this.songScore = songScore;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicName='" + musicName + '\'' +
                ", singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", worshipsCount=" + worshipsCount +
                ", singsCount=" + singsCount +
                ", songScore='" + songScore + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
