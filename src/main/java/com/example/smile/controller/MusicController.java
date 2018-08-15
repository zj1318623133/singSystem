package com.example.smile.controller;

import com.example.smile.dao.MusicMapper;
import com.example.smile.domain.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {

    @Autowired
    MusicMapper musicMapper;

    @RequestMapping(value = "select")
    public Music select(){

        Music music = musicMapper.selectMusicByName("使命");
        System.out.println(music);
        return music;
    }

}
