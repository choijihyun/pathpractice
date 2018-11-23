package com.homeworkNotice.controller;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homeworkNotice.dao.BlackboardDao;
import com.homeworkNotice.dto.BlackboardDto;


@Controller
public class BlackboardController {
	
	@Autowired
	private BlackboardDao blackboardDao;
	
	@ResponseBody
    @RequestMapping(value = "/blackboard/getAnnounce.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String getAnnounce(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subject", required=true) String subject) {
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("subject", subject);
		System.out.println("subject :"+subject);
    	List<BlackboardDto> BlackboardDtoList=blackboardDao.getAnnounce(param);
    	System.out.println(BlackboardDtoList);
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
    	System.out.println("list : "+BlackboardDtoList);
        if(!BlackboardDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
        	for(int i=0;i<BlackboardDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("date",BlackboardDtoList.get(i).getdate());
        		jSONObject.put("contest",BlackboardDtoList.get(i).getcontest());
        	
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<BlackboardDtoList.size();i++){
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
    @RequestMapping(value = "/blackboard/getSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.POST)// value��� ���� ����, get��� ���
    public String getSubject(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) {
		System.out.println("blackboard controller!!");
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		
    	List<BlackboardDto> BlackboardDtoList=blackboardDao.getSubject(param);
    	
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!BlackboardDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
        	for(int i=0;i<BlackboardDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		if(BlackboardDtoList.get(i).getsubject()!="") {
        			jSONObject.put("subject",BlackboardDtoList.get(i).getsubject());        			
        			jSONArray.add(jSONObject);
        			jsonList.add((JSONObject)jSONArray.get(i));
        		}
        	}
        	
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<BlackboardDtoList.size();i++){
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

}
