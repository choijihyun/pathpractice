package com.homeworkNotice.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homeworkNotice.dao.SubjectDao;
import com.homeworkNotice.dto.CompleteDto;
import com.homeworkNotice.dto.SubjectDto;
import com.homeworkNotice.dto.UserDto;
import com.homeworkNotice.dao.UserDao;

@Controller
public class SubjectController {

	@Autowired
	private SubjectDao	subjectDao;
	private UserDao userDao;
	private UserDto	userDto;
	

	@ResponseBody
	@RequestMapping(value = "/subject/insertSubject", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�뜝�룞�삕 �뜝�떥釉앹삕�뜝�룞�삕 url //get�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕 /user/getUserPwdInfo.json�뜝�떛�씛�삕�뜝占� url�뜝�룞�삕 �뜝�룞�삕�뜝�떢�눦�삕 �뜝�룞�삕�뜝�룞�삕 �솗�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕 �뜝�뙇�뙋�삕.
	public String insertSubject(
			Locale locale, 
			Model model, 
			@RequestParam(value = "subNo", required=false) String subNo,
			@RequestParam(value = "classNum", required=false) String classNum, //classNum �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛琉꾩삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 ! �뜝�룞�삕���뜝�떍�뙋�삕
			@RequestParam(value = "subName", required=true) String subName,
			@RequestParam(value = "day", required=true) String day,
			@RequestParam(value = "classRoom", required=false) String classRoom,
			@RequestParam(value = "profName", required=false) String profName,
			@RequestParam(value = "startHour", required=false) String startHour,
			@RequestParam(value = "endHour", required=false) String endHour,
			@RequestParam(value = "add", required=false) String add
			){
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("subNo",subNo);
		param.put("classNum",classNum);
		param.put("subName",subName);
		param.put("day",day);
		param.put("classRoom",classRoom);
		param.put("profName",profName);
		param.put("startHour",startHour);
		param.put("endHour",endHour);
		param.put("add",add);


		int result=0;
		try {
			result=subjectDao.insertSubject(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    
    	if(result==1) {
    		jSONObject.put("result", "1");//�뜝�룞�삕�뜝�룞�삕    		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

	@ResponseBody
    @RequestMapping(value = "/subject/searchDirSubject", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String searchDirSubject(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "add", required=true) String add) {

		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("add",add);
		
    	List<SubjectDto> subjectDtoList=subjectDao.searchDirSubject(param);
    	
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!subjectDtoList.isEmpty()) {
        	for(int i=0;i<subjectDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("add",subjectDtoList.get(i).getAdd());
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<subjectDtoList.size();i++){
        		jSONArray.add(jsonList.get(i));
        	}
        	
        	JSONObject jsObject=new JSONObject();
        	jsObject.put("result", jSONArray);

            return jsObject.toString();
        } 
        else {
        	
    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}
	//wwww
	
	//search
	@ResponseBody
	@RequestMapping(value = "/subject/searchSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�뜝�룞�삕 �뜝�떥釉앹삕�뜝�룞�삕 url //get�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕 /user/getUserPwdInfo.json�뜝�떛�씛�삕�뜝占� url�뜝�룞�삕 �뜝�룞�삕�뜝�떢�눦�삕 �뜝�룞�삕�뜝�룞�삕 �솗�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕 �뜝�뙇�뙋�삕.
	public String searchSubject(
			Locale locale, //�뜝�떕�벝�삕�뜝�룞�삕永방걲�댙�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�떇�씛�삕�뜝�룞�삕�뜝占�
			Model model, //�뜝�떕�벝�삕�뜝�룞�삕永방걲�댙�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�떇�씛�삕�뜝�룞�삕�뜝占�
			@RequestParam(value = "word", required=false) String word,
			@RequestParam(value = "select",required=true) final int select){
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		int word1=0;
		
		if(select==3) {
			word1 = Integer.parseInt(word);
			param.put("word", word1);
			param.put("select",select);
		}
		else {
		param.put("word",word);			
		param.put("select",select);
		}
		
		List<SubjectDto> subjectDtoList;
		
		switch(select){
		case 1:	subjectDtoList =subjectDao.search1Subject(param);break; //subno
		case 2:subjectDtoList =subjectDao.searchSubject(param);break;//subname
		case 3:subjectDtoList =subjectDao.search2Subject(param);System.out.println("run");break;//subjectKey
		case 4:subjectDtoList =subjectDao.search3Subject(param);break;//profname
		default:subjectDtoList =subjectDao.searchSubject(param);break;
		}
		//subNo 
		//subName 
		//subjectKey  
		//profName 
		
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
    	
    	System.out.println(subjectDtoList);
		
    	if(!subjectDtoList.isEmpty()) {//�뜝�룞�삕�솚�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢怨ㅼ삕 �뜝�룞�삕�슚�뜝�떦紐뚯삕(db�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕) �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�띂�뿉 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떬琉꾩삕�뜝�뙏�뙋�삕
        	for(int i=0;i<subjectDtoList.size();i++) {
        		
        		JSONObject jSONObject = new JSONObject();
        		if(subjectDtoList.get(i).getAdd().equals("0")) {
        		System.out.println("elel");	
        		jSONObject.put("subNo",subjectDtoList.get(i).getSubNo());
        		jSONObject.put("classNum",subjectDtoList.get(i).getClassNum());
        		jSONObject.put("subName",subjectDtoList.get(i).getSubName());
        		jSONObject.put("day", subjectDtoList.get(i).getDay());
        		jSONObject.put("classroom", subjectDtoList.get(i).getClassRoom());
        		jSONObject.put("profName", subjectDtoList.get(i).getProfName());
        		jSONObject.put("startHour",subjectDtoList.get(i).getStartHour());
        		jSONObject.put("endHour",subjectDtoList.get(i).getEndHour());
        		jSONObject.put("add",subjectDtoList.get(i).getAdd());
        		jSONObject.put("subjectKey", subjectDtoList.get(i).getSubjectKey());
        		
        		}
        		
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}

        	System.out.println(jsonList);
        	
        	
        	jSONArray.clear();
        	for(int i=0;i<subjectDtoList.size();i++){
        		jSONArray.add(jsonList.get(i));
        	}
        	
        	JSONObject jsObject=new JSONObject();
        	jsObject.put("result", jSONArray);
            return jsObject.toString();
        } 
        else {

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	return jSONObject.toString();
        }
	}


	@ResponseBody
    @RequestMapping(value = "/subject/deleteSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕, get�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝占�
    public String deleteSubject(
    			Model model,
    			@RequestParam(value = "add", required=true) String add) { // �뜝�떛琉꾩삕�뜝�룞�삕 5�뜝�룞�삕�뜝�룞�삕 �뜝�떇�씛�삕�뜝�룞�삕�떣�뜝占� �뜝�뙣�븘�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�떕�뼲�삕�뜝�룞�삕 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 id�뜝�룞�삕�뜝�룞�삕 hashmap �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜷閭먨뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
    			
		param.put("add",add);	
		//
		System.out.println(param);
		
		//�뜝�룞�삕 �뜝�뙃�눦�삕(url)�뜝�룞�삕 �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떛源띿삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
		//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 or �뜝�룞�삕�뜝�떩紐뚯삕 �뜝�떙琉꾩삕 �뜝�뙇紐뚯삕 �뜝�룞�삕
		//int �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�솚�뜝�룞�삕 �뜝�떎�뒗�벝�삕 1�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕!!
		int result=0;
		try {
			result=subjectDao.deleteSubject(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//�뜝�뙎琉꾩삕�뜝�룞�삕 �뜝�룞�삕�뜝�뜦�꽌 �뜝�룞�삕�뜝�룞�삕 or �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�뙏�눦�삕 �뜝�떕�벝�삕�뜝�룞�삕永방걲占� json �뜝�룞�삕�뜝�룞�삕�뜝�떢紐뚯삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�뙐嫄곗뼲�삕
    	if(result==1) {
    		jSONObject.put("result", "1");//�뜝�룞�삕�뜝�룞�삕     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

}
	 