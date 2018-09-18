package com.example.smile.service.impl;

import com.example.smile.dao.SingMapper;
import com.example.smile.domain.Music;
import com.example.smile.service.SingService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingServiceImpl implements SingService {

    @Mapper
    SingMapper singMapper;

    @Override
    public boolean insertSing(Music music) {
        boolean result = false;
        if (music != null){
            result = singMapper.insertSing(music);
        }
        return result;
    }

    @Override
    public boolean updateSingByName(String singName) {
        boolean result = false;
        if (singName != null && singName != ""){
            result = singMapper.updateSingByName(singName);
        }
        return result;
    }

    @Override
    public boolean deleteSingByName(String singName) {
        boolean result = false;
        if (singName != null && singName != ""){
            result = singMapper.deleteSingByName(singName);
        }
        return result;
    }

    @Override
    public Music selectSingByName(String singName) {
        Music music = null;
        if (singName != "" && singName != null){
            music = singMapper.selectSingByName(singName);
        }
        return music;
    }

    @Override
    public List<Music> selectSingAll() {
       List<Music> musics =  new ArrayList<Music>();
        musics =  singMapper.selectSingAll();
        return musics;
    }

    @Override
    public List<Music> selectSingByDate(String startDate, String endDate) {
        List<Music> musics =  new ArrayList<Music>();
        musics =  singMapper.selectSingByDate(startDate,endDate);
        return musics;
    }
}
