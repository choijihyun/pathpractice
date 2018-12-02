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

import com.homeworkNotice.dao.HomeworkDao;
import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dto.CompleteDto;
import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.UserDto;

@Controller
public class HomeworkController {

	@Autowired
	private HomeworkDao	homeworkDao;
	private UserDao userDao;
	
	
	//insert
	@ResponseBody
    @RequestMapping(value = "/homework/insertHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙, get占쏙옙占� 占쏙옙占�
    public String insertUser(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "success", required=true) final int success,
    			@RequestParam(value = "team",required=false) String team) { // 占싱뤄옙占쏙옙 5占쏙옙占쏙옙 占식띰옙占쏙옙拷占� 占쌨아울옙占쏙옙 占쏙옙占쏙옙 占싫억옙占쏙옙 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		
		param.put("stuId",stuId);			
		param.put("dueDate",dueDate);		
		param.put("importance",importance);		
		param.put("title",title);
		param.put("contents",contents);
		param.put("subNo",subNo);
		param.put("success",success);
		param.put("team", team);
		
		int result=0;
		try {
			result=homeworkDao.insertHomework(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


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


	//delete
	@ResponseBody
	@RequestMapping(value = "/homework/deleteAllHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
	public String deleteAllHomework(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
			Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			@RequestParam(value = "stuId", required=true) String stuId) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		
		int result=homeworkDao.deleteAllHomework(param);
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
				jSONObject.put("result","1");//id占쏙옙 占쏙옙占쏙옙占싹곤옙 占쏙옙占쏙옙占� 占승댐옙 占쏙옙占�
		}
		else {//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占싼뤄옙占쌔댐옙
			jSONObject.put("result", "0"); //id占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십는곤옙占�
		}
		return jSONObject.toString();//占쏙옙청占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙환占쏙옙占쌔댐옙.
	}

	
	//delete
	@ResponseBody
	@RequestMapping(value = "/homework/deleteHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
	public String deleteHomework(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
			Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value="assignNo",required=true) final int assignNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("assignNo",assignNo);
		
		
		int result=homeworkDao.deleteHomework(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
				jSONObject.put("result","1");//id占쏙옙 占쏙옙占쏙옙占싹곤옙 占쏙옙占쏙옙占� 占승댐옙 占쏙옙占�
		}
		else {//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占싼뤄옙占쌔댐옙
			jSONObject.put("result", "0"); //id占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십는곤옙占�
		}
		return jSONObject.toString();//占쏙옙청占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙환占쏙옙占쌔댐옙.
	}

	
	//update
	@ResponseBody
    @RequestMapping(value = "/homework/updateHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙, get占쏙옙占� 占쏙옙占�
    public String updateUser(
    			Model model,

    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "assignNo", required=true) final int assignNo,
    			@RequestParam(value = "success", required=true) final int success,
    			@RequestParam(value = "team",required=true) String team) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
					
		param.put("dueDate",dueDate);		
		param.put("importance",importance);		
		param.put("title",title);
		param.put("contents",contents);
		param.put("subNo",subNo);
		param.put("stuId",stuId);
		param.put("assignNo", assignNo);
		param.put("success",success);
		param.put("team",team);
		
		int result=0;
		try {
			result=homeworkDao.updateHomework(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}


    	JSONObject jSONObject = new JSONObject();
    	if(result==1) {
    		jSONObject.put("result", "1");//占쏙옙占쏙옙    		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}
	

	
	
	//select
		@ResponseBody
		@RequestMapping(value = "/homework/selectHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
		public String selectHomework(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
				Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				@RequestParam(value = "stuId", required=true) String stuId,
				@RequestParam(value = "select",required=true) final int select) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
	    	JSONObject tmp;
	    	
	    	
	    	if(!homeworkDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
	        	for(int i=0;i<homeworkDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("assignNo",homeworkDtoList.get(i).getAssignNo());
	        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
	        		jSONObject.put("registerDate", homeworkDtoList.get(i).getRegisterDate());
	        		jSONObject.put("dueDate", homeworkDtoList.get(i).getDueDate());
	        		jSONObject.put("importance", homeworkDtoList.get(i).getImportance());
	        		jSONObject.put("title",homeworkDtoList.get(i).getTitle());
	        		jSONObject.put("contents",homeworkDtoList.get(i).getContents());
	        		jSONObject.put("subNo",homeworkDtoList.get(i).getSubNo());
	        		jSONObject.put("success",homeworkDtoList.get(i).getSuccess());
	        		jSONObject.put("team", homeworkDtoList.get(i).getTeam());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}

	        	jSONArray.clear();
	        	/*
	        	if(select==1) {
	        		for(int i=0;i<homeworkDtoList.size();i++){
	        			for(int j=0;j<homeworkDtoList.size();j++) {
	        			
		        		if((int)(jsonList.get(j).get("importance"))>(int)(jsonList.get(j+1).get("importance"))){
		        			tmp = jsonList.get(j);
		        			//jsonList.get(j)=jsonList.get(j+1).clone();
		        			
		        		}
	        			
		        	}
	        		}
	        		
	        	}else if(select==2) {
		        	for(int i=0;i<homeworkDtoList.size();i++){
		        		if(jsonList.get(i).
		        		//jSONArray.add(jsonList.get(i));
		        	}
		        	
	        	}else if(select==3) {
		        	for(int i=0;i<homeworkDtoList.size();i++){
		        		
		        		jSONArray.add(jsonList.get(i));
		        	}
		        	
	        	}*/
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
		
		//pushHomework
			@ResponseBody
			@RequestMapping(value = "/homework/pushHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
			public String pushHomework(
					Locale locale, 
					Model model
					) {
				
				List<HomeworkDto> homeworkDtoList =homeworkDao.pushHomework();	
				HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙

		    	JSONArray jSONArray=new JSONArray();
		    	List<JSONObject> jsonList=new ArrayList<JSONObject>();

		    	if(!homeworkDtoList.isEmpty()) {//반환받은 데이터가 유효하면(db에 있으면) 브라우저 화면에 결과를 뿌려준다
		    		for(int i=0;i<homeworkDtoList.size();i++) {
		    		//	param.clear();
		    			//param.put("stuId",homeworkDtoList.get(i).getStuId());
		    			JSONObject jSONObject = new JSONObject();
		    	//		List<UserDto> pushList=userDao.pushUser(param);
		        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
		        		
		        		jSONArray.add(jSONObject);
		        		
		        		jsonList.add((JSONObject)jSONArray.get(i));
		        		
		        		System.out.println(jsonList);
		        	
		    		}

		        	System.out.println(jsonList);
		        	
		        	jSONArray.clear();
		        	for(int i=0;i<homeworkDtoList.size();i++){
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
		@RequestMapping(value = "/homework/selectTeamHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
		public String selectTeamHomework(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
				Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				@RequestParam(value = "stuId", required=true) String stuId) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectTeamHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!homeworkDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
	        	for(int i=0;i<homeworkDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("assignNo",homeworkDtoList.get(i).getAssignNo());
	        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
	        		jSONObject.put("registerDate", homeworkDtoList.get(i).getRegisterDate());
	        		jSONObject.put("dueDate", homeworkDtoList.get(i).getDueDate());
	        		jSONObject.put("importance", homeworkDtoList.get(i).getImportance());
	        		jSONObject.put("title",homeworkDtoList.get(i).getTitle());
	        		jSONObject.put("contents",homeworkDtoList.get(i).getContents());
	        		jSONObject.put("subNo",homeworkDtoList.get(i).getSubNo());
	        		jSONObject.put("success",homeworkDtoList.get(i).getSuccess());
	        		jSONObject.put("team", homeworkDtoList.get(i).getTeam());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	/*
	        	Collections.sort( jsonList, new Comparator<JSONObject>() {

	    		    public int compare(JSONObject a, JSONObject b) {
	    		        String valA = new String();
	    		        String valB = new String();
	    		        int vA,vB;


	    		        return valA.compareTo(valB);
	    		    }
	    		});
	    		
	    		*/
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<homeworkDtoList.size();i++){
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
		@RequestMapping(value = "/homework/selectSuccessHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
		public String selectSuccessHomework(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
				Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				@RequestParam(value = "stuId", required=true) String stuId) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectSuccessHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!homeworkDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
	        	for(int i=0;i<homeworkDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("assignNo",homeworkDtoList.get(i).getAssignNo());
	        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
	        		jSONObject.put("registerDate", homeworkDtoList.get(i).getRegisterDate());
	        		jSONObject.put("dueDate", homeworkDtoList.get(i).getDueDate());
	        		jSONObject.put("importance", homeworkDtoList.get(i).getImportance());
	        		jSONObject.put("title",homeworkDtoList.get(i).getTitle());
	        		jSONObject.put("contents",homeworkDtoList.get(i).getContents());
	        		jSONObject.put("subNo",homeworkDtoList.get(i).getSubNo());
	        		jSONObject.put("success",homeworkDtoList.get(i).getSuccess());
	        		jSONObject.put("team", homeworkDtoList.get(i).getTeam());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	/*
	        	Collections.sort( jsonList, new Comparator<JSONObject>() {

	    		    public int compare(JSONObject a, JSONObject b) {
	    		        String valA = new String();
	    		        String valB = new String();
	    		        int vA,vB;


	    		        return valA.compareTo(valB);
	    		    }
	    		});
	    		
	    		*/
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<homeworkDtoList.size();i++){
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
		@RequestMapping(value = "/homework/selectNotSuccessHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
		public String selectNotSuccessHomework(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
				Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
				@RequestParam(value = "stuId", required=true) String stuId) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectNotSuccessHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!homeworkDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
	        	for(int i=0;i<homeworkDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("assignNo",homeworkDtoList.get(i).getAssignNo());
	        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
	        		jSONObject.put("registerDate", homeworkDtoList.get(i).getRegisterDate());
	        		jSONObject.put("dueDate", homeworkDtoList.get(i).getDueDate());
	        		jSONObject.put("importance", homeworkDtoList.get(i).getImportance());
	        		jSONObject.put("title",homeworkDtoList.get(i).getTitle());
	        		jSONObject.put("contents",homeworkDtoList.get(i).getContents());
	        		jSONObject.put("subNo",homeworkDtoList.get(i).getSubNo());
	        		jSONObject.put("success",homeworkDtoList.get(i).getSuccess());
	        		jSONObject.put("team", homeworkDtoList.get(i).getTeam());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	/*
	        	Collections.sort( jsonList, new Comparator<JSONObject>() {

	    		    public int compare(JSONObject a, JSONObject b) {
	    		        String valA = new String();
	    		        String valB = new String();
	    		        int vA,vB;


	    		        return valA.compareTo(valB);
	    		    }
	    		});
	    		
	    		*/
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<homeworkDtoList.size();i++){
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
