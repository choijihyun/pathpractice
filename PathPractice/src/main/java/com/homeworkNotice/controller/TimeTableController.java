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
import com.homeworkNotice.dto.SubjectDto;
import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dao.TimeTableDao;
import com.homeworkNotice.dto.TimeTableDto;

@Controller
public class TimeTableController {

	@Autowired
	private TimeTableDao timeTableDao;

	@ResponseBody
    @RequestMapping(value = "/timeTable/insertTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕, get�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝占�
    public String insertTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subjectKey", required=true) final int subjectKey) { // �뜝�떛琉꾩삕�뜝�룞�삕 5�뜝�룞�삕�뜝�룞�삕 �뜝�떇�씛�삕�뜝�룞�삕�떣�뜝占� �뜝�뙣�븘�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�떕�뼲�삕�뜝�룞�삕 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 id�뜝�룞�삕�뜝�룞�삕 hashmap �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜷閭먨뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
				
		param.put("stuId",stuId);	
		param.put("subjectKey",subjectKey);
		
		//subject 키가 중복이면 들어오지 않게 처리
		
		System.out.println(param);
		//�뜝�룞�삕 �뜝�뙃�눦�삕(url)�뜝�룞�삕 �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떛源띿삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
		//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 or �뜝�룞�삕�뜝�떩紐뚯삕 �뜝�떙琉꾩삕 �뜝�뙇紐뚯삕 �뜝�룞�삕
		//int �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�솚�뜝�룞�삕 �뜝�떎�뒗�벝�삕 1�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕!!
		int result=0;
		try {
			result=timeTableDao.insertTimeTable(param);
			System.out.println(result);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Duplicated row!");
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


	@ResponseBody
    @RequestMapping(value = "/timeTable/searchTimeTable", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String searchTimeTable(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) {

		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		
    	List<TimeTableDto> timeTableDtoList=timeTableDao.searchTimeTable(param);
    	
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!timeTableDtoList.isEmpty()) {//�뜝�룞�삕�솚�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢怨ㅼ삕 �뜝�룞�삕�슚�뜝�떦紐뚯삕(db�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕) �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솕�뜝�띂�뿉 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떬琉꾩삕�뜝�뙏�뙋�삕
        	for(int i=0;i<timeTableDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("stuId",timeTableDtoList.get(i).getStuId());
        		jSONObject.put("subjectKey", timeTableDtoList.get(i).getSubjectKey());
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<timeTableDtoList.size();i++){
        		jSONArray.add(jsonList.get(i));
        	}
        	
        	JSONObject jsObject=new JSONObject();
        	jsObject.put("result", jSONArray);

            return jsObject.toString();
        } 
        else {//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떬琉꾩삕�뜝�뙏�뙋�삕

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}


	@ResponseBody
    @RequestMapping(value = "/timeTable/deleteAllTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕, get�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝占�
    public String deleteAllTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) { // �뜝�떛琉꾩삕�뜝�룞�삕 5�뜝�룞�삕�뜝�룞�삕 �뜝�떇�씛�삕�뜝�룞�삕�떣�뜝占� �뜝�뙣�븘�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�떕�뼲�삕�뜝�룞�삕 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 id�뜝�룞�삕�뜝�룞�삕 hashmap �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜷閭먨뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
    			
		param.put("stuId",stuId);
		
		System.out.println(param);
		
		//�뜝�룞�삕 �뜝�뙃�눦�삕(url)�뜝�룞�삕 �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떛源띿삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
		//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 or �뜝�룞�삕�뜝�떩紐뚯삕 �뜝�떙琉꾩삕 �뜝�뙇紐뚯삕 �뜝�룞�삕
		//int �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�솚�뜝�룞�삕 �뜝�떎�뒗�벝�삕 1�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕!!
		int result=0;
		try {
			result=timeTableDao.deleteAllTimeTable(param);
			
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

	
	
	@ResponseBody
    @RequestMapping(value = "/timeTable/deleteTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕, get�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝占�
    public String deleteTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subjectKey", required=true) final int subjectKey) { // �뜝�떛琉꾩삕�뜝�룞�삕 5�뜝�룞�삕�뜝�룞�삕 �뜝�떇�씛�삕�뜝�룞�삕�떣�뜝占� �뜝�뙣�븘�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�떕�뼲�삕�뜝�룞�삕 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 id�뜝�룞�삕�뜝�룞�삕 hashmap �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜷閭먨뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
    			
		param.put("stuId",stuId);	
		param.put("subjectKey",subjectKey);
		//
		System.out.println(param);
		
		//�뜝�룞�삕 �뜝�뙃�눦�삕(url)�뜝�룞�삕 �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떛源띿삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
		//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 or �뜝�룞�삕�뜝�떩紐뚯삕 �뜝�떙琉꾩삕 �뜝�뙇紐뚯삕 �뜝�룞�삕
		//int �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�솚�뜝�룞�삕 �뜝�떎�뒗�벝�삕 1�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛紐뚯삕 �뜝�룞�삕�뜝�룞�삕!!
		int result=0;
		try {
			result=timeTableDao.deleteTimeTable(param);
			
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
