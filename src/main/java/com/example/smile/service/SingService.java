package com.example.smile.service;

import com.example.smile.domain.Music;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingService {

    /**
     * 插入新的诗歌
     * @param music
     * @return
     */
    public boolean insertSing(Music music);

    /**
     * 根据诗歌名称更新诗歌详情
     * @param singName
     * @return
     */
    public boolean updateSingByName(String singName);

    /**
     * 根据诗歌名称删除诗歌
     * @param singName
     * @return
     */
    public boolean deleteSingByName(String singName);

    /**
     * 根据诗歌名称查询诗歌详情
     * @param singName
     * @return
     */
    public Music selectSingByName(String singName);

    /**
     * 查询所有诗歌
     * @return
     */
    public List<Music> selectSingAll();

    /**
     * 根据时间查询诗歌
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Music> selectSingByDate(String startDate,String endDate);

}
