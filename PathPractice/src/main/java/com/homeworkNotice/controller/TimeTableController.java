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
    @RequestMapping(value = "/timeTable/insertTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙, get占쏙옙占� 占쏙옙占�
    public String insertTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subjectKey", required=true) final int subjectKey) { // 占싱뤄옙占쏙옙 5占쏙옙占쏙옙 占식띰옙占쏙옙拷占� 占쌨아울옙占쏙옙 占쏙옙占쏙옙 占싫억옙占쏙옙 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
				
		param.put("stuId",stuId);	
		param.put("subjectKey",subjectKey);
		
		//
		System.out.println(param);
		//占쏙옙 占쌉쇽옙(url)占쏙옙 회占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占싱깍옙 占쏙옙占쏙옙占쏙옙
		//占쏙옙占쏙옙占� 占쏙옙占쏙옙 or 占쏙옙占싻몌옙 占싯뤄옙 占쌍몌옙 占쏙옙
		//int 占쏙옙占쏙옙占쏙옙 占쏙옙환占쏙옙 占실는듸옙 1占싱몌옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占싱몌옙 占쏙옙占쏙옙!!
		int result=0;
		try {
			result=timeTableDao.insertTimeTable(param);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//占쌓뤄옙占쏙옙 占쏙옙占썩서 占쏙옙占쏙옙 or 占쏙옙占쏙옙 占쏙옙占쏙옙占쌔쇽옙 占싫듸옙占쏙옙絹恙� json 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쌕거억옙
    	if(result==1) {
    		jSONObject.put("result", "1");//占쏙옙占쏙옙    		
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
        if(!timeTableDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
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
        else {//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占싼뤄옙占쌔댐옙

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}


	@ResponseBody
    @RequestMapping(value = "/timeTable/deleteAllTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙, get占쏙옙占� 占쏙옙占�
    public String deleteAllTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) { // 占싱뤄옙占쏙옙 5占쏙옙占쏙옙 占식띰옙占쏙옙拷占� 占쌨아울옙占쏙옙 占쏙옙占쏙옙 占싫억옙占쏙옙 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
    			
		param.put("stuId",stuId);
		
		System.out.println(param);
		
		//占쏙옙 占쌉쇽옙(url)占쏙옙 회占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占싱깍옙 占쏙옙占쏙옙占쏙옙
		//占쏙옙占쏙옙占� 占쏙옙占쏙옙 or 占쏙옙占싻몌옙 占싯뤄옙 占쌍몌옙 占쏙옙
		//int 占쏙옙占쏙옙占쏙옙 占쏙옙환占쏙옙 占실는듸옙 1占싱몌옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占싱몌옙 占쏙옙占쏙옙!!
		int result=0;
		try {
			result=timeTableDao.deleteAllTimeTable(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//占쌓뤄옙占쏙옙 占쏙옙占썩서 占쏙옙占쏙옙 or 占쏙옙占쏙옙 占쏙옙占쏙옙占쌔쇽옙 占싫듸옙占쏙옙絹恙� json 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쌕거억옙
    	if(result==1) {
    		jSONObject.put("result", "1");//占쏙옙占쏙옙     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

	
	
	@ResponseBody
    @RequestMapping(value = "/timeTable/deleteTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙, get占쏙옙占� 占쏙옙占�
    public String deleteTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subjectKey", required=true) final int subjectKey) { // 占싱뤄옙占쏙옙 5占쏙옙占쏙옙 占식띰옙占쏙옙拷占� 占쌨아울옙占쏙옙 占쏙옙占쏙옙 占싫억옙占쏙옙 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
    			
		param.put("stuId",stuId);	
		param.put("subjectKey",subjectKey);
		//
		System.out.println(param);
		
		//占쏙옙 占쌉쇽옙(url)占쏙옙 회占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占싱깍옙 占쏙옙占쏙옙占쏙옙
		//占쏙옙占쏙옙占� 占쏙옙占쏙옙 or 占쏙옙占싻몌옙 占싯뤄옙 占쌍몌옙 占쏙옙
		//int 占쏙옙占쏙옙占쏙옙 占쏙옙환占쏙옙 占실는듸옙 1占싱몌옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占싱몌옙 占쏙옙占쏙옙!!
		int result=0;
		try {
			result=timeTableDao.deleteTimeTable(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	//占쌓뤄옙占쏙옙 占쏙옙占썩서 占쏙옙占쏙옙 or 占쏙옙占쏙옙 占쏙옙占쏙옙占쌔쇽옙 占싫듸옙占쏙옙絹恙� json 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쌕거억옙
    	if(result==1) {
    		jSONObject.put("result", "1");//占쏙옙占쏙옙     		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}

	
	
	
	}
