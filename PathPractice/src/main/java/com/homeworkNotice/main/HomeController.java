package com.homeworkNotice.main;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.CommandMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        return "index";
    }
    

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeMain(Locale locale, Model model) {
    	System.out.println("main!");
        return "home";
    }
    
    
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(Locale locale, Model model) {
    	System.out.println("join!");
        return "join";
    }
    
    @RequestMapping(value = "/assignment_add", method = RequestMethod.GET)
    public String assignment_add(Locale locale, Model model) {
    	System.out.println("assignment_add!");
        return "assignment_add";
    }
    
    @RequestMapping(value = "/assignment", method = RequestMethod.GET)
    public String assignment(Locale locale, Model model) {
    	System.out.println("assignment!");
        return "assignment";
    }
    
    @RequestMapping(value = "/changePW", method = RequestMethod.GET)
    public String changePW(Locale locale, Model model) {
    	System.out.println("changePW!");
        return "changePW";
    }
    
    @RequestMapping(value = "/find_subject", method = RequestMethod.GET)
    public String find_subject(Locale locale, Model model) {
    	System.out.println("find_subject!");
        return "find_subject";
    }
    
    @RequestMapping(value = "/findPW", method = RequestMethod.GET)
    public String findPW(Locale locale, Model model) {
    	System.out.println("findPW!");
        return "findPW";
    }
    
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(Locale locale, Model model) {
    	System.out.println("menu!");
        return "menu";
    }
    
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypage(Locale locale, Model model) {
    	System.out.println("mypage!");
        return "mypage";
    }
    
    @RequestMapping(value = "/timetable_page", method = RequestMethod.GET)
    public String timetable_page(Locale locale, Model model) {
    	System.out.println("timetable_page!");
        return "timetable_page";
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
