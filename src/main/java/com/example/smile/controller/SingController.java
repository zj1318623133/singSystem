package com.example.smile.controller;

import com.example.smile.domain.Music;
import com.example.smile.service.SingService;
import com.example.smile.util.DataUtil;
import com.example.smile.util.HttpUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class SingController {

    @Resource
    SingService singService;

    /**
     * 插入新的诗歌
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "insertSing",method = RequestMethod.POST)
    public ModelAndView insertSing(HttpServletRequest request, HttpServletResponse response, Music music){
        boolean result = singService.insertSing(music);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        if (result){
            modelAndView.addObject("insertResult","插入成功");
        }else {
            modelAndView.addObject("insertResult","插入失败");
        }
        return modelAndView;
    }

    /**
     * 根据诗歌的名称查询详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "selectSingByName",method = RequestMethod.POST)
    public ModelAndView selectSingByName(HttpServletRequest request, HttpServletResponse response){

        ModelAndView modelAndView = new ModelAndView();
        String singName = request.getParameter("singName");
        Music music = singService.selectSingByName(singName);
        modelAndView.addObject("selectByNameResult",music);

        return modelAndView;
    }

    /**
     * 查询所有诗歌
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "selectSingAll",method = RequestMethod.POST)
    public ModelAndView selectSingAll(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("home");
        List<Music> musics = singService.selectSingAll();

        modelAndView.addObject("selectAllResult",musics);

        return modelAndView;
    }

    /**
     * 根据诗歌名称删除指定诗歌
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "deleteSingByName",method = RequestMethod.POST)
    public ModelAndView deleteSingByName(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("home");
        String singName = request.getParameter("singName");
        singService.deleteSingByName(singName);
        return modelAndView;
    }

    /**
     * 根据诗歌名称 更新诗歌详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateSingByName",method = RequestMethod.POST)
    public ModelAndView updateSingByName(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("home");
    }

    /**
     * 根据时间查询诗歌
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "selectSingByDate",method = RequestMethod.POST)
    public ModelAndView selectSingByDate(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("home");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Music> musics = singService.selectSingByDate(startDate, endDate);
        modelAndView.addObject("selectByDateResult",musics);
        return modelAndView;
    }

    @RequestMapping(value = "searchSing",method = RequestMethod.POST)
    public ModelAndView searchSingByName(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("home");
        String name = request.getParameter("keyword");
        String s = DataUtil.selectSing(name);
        return modelAndView;
    }
}
