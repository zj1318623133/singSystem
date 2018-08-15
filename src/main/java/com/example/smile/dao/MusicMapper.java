package com.example.smile.dao;

import com.example.smile.domain.Music;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MusicMapper {

    /**
     * 根据歌曲名称查找单曲
     * @param musicName 歌曲名称
     * @return
     */
    Music selectMusicByName(String musicName);

    /**
     * 插入新的歌曲
     * @param musicName 歌曲名称
     * @param signer  歌手
     * @param album 专辑
     * @return
     */
    int insertMusic(String musicName,String signer,String album);

}
