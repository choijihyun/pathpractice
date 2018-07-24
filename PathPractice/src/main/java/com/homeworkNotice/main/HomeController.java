package com.homeworkNotice.main;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dto.UserDto;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
    	System.out.println("server start");
    	/*
    	 * 헷갈릴 때 공부용으로 써~~~~~~~~~~~~~~
    	List<UserDto> userDtoList=userDao.selectAllList();
    	for(UserDto userDto : userDtoList) {
    		System.out.println(userDto.getId());
    	}
    	List<HomeworkDto> homeworkDtoList=homeworkDao.selectAllList();
    	for(HomeworkDto homeworkDto : homeworkDtoList) {
    		System.out.println(homeworkDto.getId());
    	}
    	 * 헷갈릴 때 공부용으로 써~~~~~~~~~~~~~~
    	*/
        return "home";
    }
    /*
    @RequestMapping(value = "/homeTest", method = RequestMethod.GET)
    public String homeTest(Locale locale, Model model) {
    	StringBuffer sb=new StringBuffer();
    	List<UserDto> userDtoList=userDao.selectAllList();
    	for(UserDto userDto : userDtoList) {
    		sb.append(userDto.getId().toString());
    	}
    	return "home";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Locale locale, Model model) {
    	StringBuffer sb=new StringBuffer();
    	List<UserDto> userDtoList=userDao.selectAllList();
    	for(UserDto userDto : userDtoList) {
    		sb.append(userDto.getId().toString());
    	}
    	return sb.toString();
    }*/
}
