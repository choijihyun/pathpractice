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
	
	

	//占쏙옙占쏙옙占� insert
	@ResponseBody
	@RequestMapping(value = "/subject/insertSubject", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
	public String insertSubject(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
			Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			@RequestParam(value = "subNo", required=false) String subNo,
			@RequestParam(value = "classNum", required=false) String classNum, //classNum 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占싱뤄옙占쏙옙 占쏙옙占쏙옙 ! 占쏙옙타占싣댐옙
			@RequestParam(value = "subName", required=true) String subName,
			@RequestParam(value = "day", required=true) String day,
			@RequestParam(value = "classRoom", required=false) String classRoom,
			@RequestParam(value = "profName", required=false) String profName,
			@RequestParam(value = "startHour", required=false) String startHour,
			@RequestParam(value = "endHour", required=false) String endHour,
			@RequestParam(value = "add", required=false) String add
			){
		//add占쏙옙占쏙옙 占싻뱄옙
		
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
        if(!subjectDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
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
        else {//占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占싼뤄옙占쌔댐옙

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}
	
	
	//search
	@ResponseBody
	@RequestMapping(value = "/subject/searchSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//占쏙옙 占싸븝옙占쏙옙 url //get占쏙옙占쏙옙占쏙옙占� 占쏙옙 /user/getUserPwdInfo.json占싱띰옙占� url占쏙옙 占쏙옙占싶쇽옙 占쏙옙占쏙옙 확占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
	public String searchSubject(//url占쏙옙 占쏙옙占쏙옙(占쏙옙占쏙옙)占쏙옙 占쌉쇽옙
			Locale locale, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			Model model, //占싫듸옙占쏙옙絹恙∽옙占� 占쏙옙占쏙옙 占식띰옙占쏙옙占�
			@RequestParam(value = "word", required=false) String word,
			@RequestParam(value = "select",required=true) final int select){

		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("word",word);			
		param.put("select",select);
		
		System.out.println("TTTT :::: "+word);
		
		
		List<SubjectDto> subjectDtoList;
		
		switch(select){
		case 1:	subjectDtoList =subjectDao.search1Subject(param);break;
		case 2:subjectDtoList =subjectDao.searchSubject(param);System.out.println("run");break;
		case 3:subjectDtoList =subjectDao.search2Subject(param);break;
		case 4:subjectDtoList =subjectDao.search3Subject(param);break;
		default:subjectDtoList =subjectDao.searchSubject(param);break;
		}
		//subNo 로 찾는 경우
		//subName 으로 찾는 경우
		//subjectKey 로 찾는 경우 
		//profName 으로 찾는 경우
		
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
    	
    	System.out.println(subjectDtoList);
		
    	if(!subjectDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
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
        	/*
        	Collections.sort( jsonList, new Comparator<JSONObject>() {

    		    public int compare(JSONObject a, JSONObject b) {
    		        String valA = new String();
    		        String valB = new String();
    		        int vA,vB;
    		        
    		      
    		        switch(select) {
    		        case 1: vA = (Integer) a.get("assignNo");vB = (Integer) b.get("assignNo");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        case 2: valA = (String) a.get("stuId");valB = (String) b.get("stuId");break;
    		        case 3: valA = (String) a.get("registerDate");valB = (String) b.get("registerDate");break;
    		        case 4: valA = (String) a.get("dueDate");valB = (String) b.get("dueDate");break;
    		        case 5: vA = (Integer) a.get("importance");vB = (Integer) b.get("importance");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        case 6: valA = (String) a.get("title");valB = (String) b.get("title");break; 
    		        case 7: valA = (String) a.get("contents");valB = (String) b.get("contents");break;
    		        case 8: valA = (String) a.get("subNo");valB = (String) b.get("subNo");break;
    		        case 9: vA= (Integer)a.get("success");vB = (Integer) b.get("success");if(vA==vB) return 0; if(vA>vB) return 1; else return -1;
    		        }

    		        return valA.compareTo(valB);
    		    }
    		});  */
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
    @RequestMapping(value = "/subject/deleteSubject.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙, get占쏙옙占� 占쏙옙占�
    public String deleteSubject(
    			Model model,
    			@RequestParam(value = "add", required=true) String add) { // 占싱뤄옙占쏙옙 5占쏙옙占쏙옙 占식띰옙占쏙옙拷占� 占쌨아울옙占쏙옙 占쏙옙占쏙옙 占싫억옙占쏙옙 x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //占쏙옙占쏙옙占쏙옙 id占쏙옙占쏙옙 hashmap 占쏙옙占쏙옙占쏙옙獵歐占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
    			
		param.put("add",add);	
		//
		System.out.println(param);
		
		//占쏙옙 占쌉쇽옙(url)占쏙옙 회占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占싱깍옙 占쏙옙占쏙옙占쏙옙
		//占쏙옙占쏙옙占� 占쏙옙占쏙옙 or 占쏙옙占싻몌옙 占싯뤄옙 占쌍몌옙 占쏙옙
		//int 占쏙옙占쏙옙占쏙옙 占쏙옙환占쏙옙 占실는듸옙 1占싱몌옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占싱몌옙 占쏙옙占쏙옙!!
		int result=0;
		try {
			result=subjectDao.deleteSubject(param);
			
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
	 