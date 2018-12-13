package com.homeworkNotice.main;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String home(Locale locale, Model model,
    		@RequestParam(value="token", required=false) String token) {
    	System.out.println("server start");
    	/*
    	 * �򰥸� �� ���ο����� ��~~~~~~~~~~~~~~
    	List<UserDto> userDtoList=userDao.selectAllList();
    	for(UserDto userDto : userDtoList) {
    		System.out.println(userDto.getId());
    	}
    	List<HomeworkDto> homeworkDtoList=homeworkDao.selectAllList();
    	for(HomeworkDto homeworkDto : homeworkDtoList) {
    		System.out.println(homeworkDto.getId());
    	}
    	 * �򰥸� �� ���ο����� ��~~~~~~~~~~~~~~
    	*/
    	model.addAttribute("token", token);
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
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Locale locale, Model model,
    		@RequestParam(value="token", required=false) String token) {
    	System.out.println("register!");

    	model.addAttribute("token", token);
        return "register";
    }
    
    @RequestMapping(value = "/push_notification", method = RequestMethod.POST)
    public String push_notification(Locale locale, Model model) {
    	System.out.println("push_notification!");
        return "push_notification";
    }


    //using url parameter for send assignment data to assignment_add.jsp 
    @RequestMapping(value = "/assignment_add", method = RequestMethod.GET)
    public String assignment_add(Locale locale, Model model,
    		@RequestParam(value="title", required=false) String title,
    		@RequestParam(value="dueDate", required=false) String dueDate,
    		@RequestParam(value="importance", required=false, defaultValue="0") int importance,
    		@RequestParam(value="contents", required=false) String contents,
    		@RequestParam(value="assignNo", required=false, defaultValue="0") int assignNo,
    		@RequestParam(value="subNo", required=false, defaultValue="0") int subNo,
    		@RequestParam(value="subjectKey", required=false, defaultValue="0") int subjectKey,
    		@RequestParam(value="team", required=false, defaultValue="0") int team) {
  
    	System.out.println("assignment_add! ");
    	
    	model.addAttribute("title", title);
    	model.addAttribute("dueDate", dueDate);
    	model.addAttribute("importance", importance);
    	model.addAttribute("contents", contents);
    	model.addAttribute("assignNo", assignNo);
    	model.addAttribute("subNo", subNo);
    	model.addAttribute("subjectKey", subjectKey);
    	model.addAttribute("team", team);
        return "assignment_add";
    }
    
    @RequestMapping(value = "/assignment_add_team", method = RequestMethod.GET)
    public String assignment_add_team(Locale locale, Model model,
    		@RequestParam(value="title", required=false) String title,
    		@RequestParam(value="dueDate", required=false) String dueDate,
    		@RequestParam(value="importance", required=false, defaultValue="0") int importance,
    		@RequestParam(value="contents", required=false) String contents,
    		@RequestParam(value="assignNo", required=false, defaultValue="0") int assignNo,
    		@RequestParam(value="subNo", required=false, defaultValue="0") int subNo,
    		@RequestParam(value="subjectKey", required=false, defaultValue="0") int subjectKey,
    		@RequestParam(value="team", required=false, defaultValue="0") int team) {
  
    	System.out.println("assignment_add_team!");
    	
    	model.addAttribute("title", title);
    	model.addAttribute("dueDate", dueDate);
    	model.addAttribute("importance", importance);
    	model.addAttribute("contents", contents);
    	model.addAttribute("assignNo", assignNo);
    	model.addAttribute("subNo", subNo);
    	model.addAttribute("subjectKey", subjectKey);
    	model.addAttribute("team", team);
        return "assignment_add_team";
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
    public String find_subject(Locale locale, Model model,
    		@RequestParam(value="page", required=false) String page,
    		@RequestParam(value="title", required=false) String title,
    		@RequestParam(value="dueDate", required=false) String dueDate,
    		@RequestParam(value="importance", required=false, defaultValue="0") int importance,
    		@RequestParam(value="contents", required=false) String contents,
    		@RequestParam(value="assignNo", required=false, defaultValue="0") int assignNo) {
       System.out.println("find_subject!");
       
       model.addAttribute("page", page);
       model.addAttribute("title", title);
       model.addAttribute("dueDate", dueDate);
       model.addAttribute("importance", importance);
       model.addAttribute("contents", contents);
       model.addAttribute("assignNo", assignNo);
       
       return "find_subject";
    }
    
    @RequestMapping(value = "/announcement", method = RequestMethod.GET)
    public String announcement(Locale locale, Model model,
    		@RequestParam(value="subject", required=true) String subject) {
       System.out.println("announcement!");
       model.addAttribute("subject", subject);
       return "announcement";
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

    @RequestMapping(value = "/menu_non", method = RequestMethod.GET)
    public String menu_non(Locale locale, Model model) {
       System.out.println("menu_non!");
        return "menu_non";
    }
    
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypage(Locale locale, Model model) {
       System.out.println("mypage!");
        return "mypage";
    }
    
    @RequestMapping(value = "/timetable_page", method = RequestMethod.GET)
    public String timetable_page(Locale locale, Model model,
    		@RequestParam(value="subjectKey", required=false, defaultValue="0") int subjectKey) {
       
    	System.out.println("timetable_page!");
       
    	model.addAttribute("subjectKey", subjectKey);
    	return "timetable_page";
    }
    
    @RequestMapping(value = "/update_information", method = RequestMethod.GET)
    public String update_information(Locale locale, Model model) {
       System.out.println("update_information!");
        return "update_information";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET	)
    public String logout(Locale locale, Model model) {
       System.out.println("logout!");
        return "logout";
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
