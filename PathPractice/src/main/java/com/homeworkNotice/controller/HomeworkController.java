package com.homeworkNotice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.homeworkNotice.dao.SubjectDao;
import com.homeworkNotice.dto.CompleteDto;
import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.UserDto;
import com.homeworkNotice.dto.SubjectDto;


@Controller
public class HomeworkController {

	@Autowired
	private HomeworkDao	homeworkDao;
	private UserDao userDao;
	@Autowired
	private SubjectDao subjectDao;
	
	
	//team 이 1이면 그 해당하는 student 들도 homework 등록 해줌 
	//해당하는 homework 정보 얻어와서 insert 같이 해줌
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
    			@RequestParam(value = "team",required=false) final int team) { // 占싱뤄옙占쏙옙 5占쏙옙占쏙옙 占식띰옙占쏙옙拷占� 占쌨아울옙占쏙옙 占쏙옙占쏙옙 占싫억옙占쏙옙 x
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
			@RequestParam(value = "select", required=true) final int select,
			@RequestParam(value="assignNo",required=true) final int assignNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("assignNo",assignNo);
	
		int result=0;
		
		//0이면 assignNo
		if(select==0) {
			result=homeworkDao.deleteHomework(param);
		}else if(select==1) {
			result=homeworkDao.deleteHomework2(param);
		}
	
		
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
    			@RequestParam(value = "team",required=true) final int team) {
		
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
				@RequestParam(value = "select",required=true) final int select,
				@RequestParam(value = "order",required=true) final int order) throws ParseException {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);
			param.put("select",select);
			param.put("order",order);
			System.out.println("@@@@@@@"+param);
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
	    	JSONObject tmp=new JSONObject();
	    	int j=0;
	    	double[] creditList=new double[50];
        	
	    	
	    	//select 0: all//select 1: personal//select 2:team//select 3:complete
	    	if(!homeworkDtoList.isEmpty()) {//占쏙옙환占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙효占싹몌옙(db占쏙옙 占쏙옙占쏙옙占쏙옙) 占쏙옙占쏙옙占쏙옙 화占썽에 占쏙옙占쏙옙占� 占싼뤄옙占쌔댐옙
	    		for(int i=0;i<homeworkDtoList.size();i++) {
	    			JSONObject jSONObject = null;
	        		if(select==0&&(homeworkDtoList.get(i).getSuccess()==0)) {
	        			jSONObject = new JSONObject();
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
	        		}else if(select==1&&(homeworkDtoList.get(i).getTeam()==0)&&(homeworkDtoList.get(i).getSuccess()==0)) {
	        			jSONObject = new JSONObject();jSONObject.put("assignNo",homeworkDtoList.get(i).getAssignNo());
		        		jSONObject.put("stuId",homeworkDtoList.get(i).getStuId());
		        		jSONObject.put("registerDate", homeworkDtoList.get(i).getRegisterDate());
		        		jSONObject.put("dueDate", homeworkDtoList.get(i).getDueDate());
		        		jSONObject.put("importance", homeworkDtoList.get(i).getImportance());
		        		jSONObject.put("title",homeworkDtoList.get(i).getTitle());
		        		jSONObject.put("contents",homeworkDtoList.get(i).getContents());
		        		jSONObject.put("subNo",homeworkDtoList.get(i).getSubNo());
		        		jSONObject.put("success",homeworkDtoList.get(i).getSuccess());
		        		jSONObject.put("team", homeworkDtoList.get(i).getTeam());
	        		}else if(select==2&&(homeworkDtoList.get(i).getTeam()!=0)&&(homeworkDtoList.get(i).getSuccess()==0)){
	        			System.out.println("select 2 히히");
	        			jSONObject = new JSONObject();
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
	        		}else if(select==3&&(homeworkDtoList.get(i).getSuccess()==1)) {
	        			jSONObject = new JSONObject();
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
	        		}else { 
	        			jSONObject=new JSONObject();
	        		}
	        		if(!jSONObject.isEmpty()) {
	        			jSONArray.add(jSONObject);
	        			jsonList.add((JSONObject)jSONArray.get(j));
	        			j++;
	        			
	        			System.out.println(jsonList);
	        		}
	        	}
	    		
	    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    		Date d1,d2,d3,d4;
	    		Date now=new Date();

	        	jSONArray.clear();
	        	
	        	// jSONArray 에 정렬해서 넣는 부분 order 에 따라서 다름
	        	
    			for(int i=0;i<jsonList.size();i++) {
    	        	param.clear();
    	        	param.put("subNo",(String)jsonList.get(i).get("subNo"));
    	        	System.out.println("#####"+param);
    	        	
    	        	List<SubjectDto> subjectCredit=subjectDao.searchCredit(param);
        		
    	        	System.out.println(subjectCredit.size());
    	        	
    	        	if(!subjectCredit.isEmpty()) {
            			creditList[i]=subjectCredit.get(0).getCredit();
            			System.out.println("!!!!"+creditList[i]);
        			}else {
        				creditList[i]=1;
        			}
        			
    			}
    			
	        	//과제별 서브젝트에서 credit 얻어오고 그럴려면 여기에 subjectDao 필요
	        	//우선순위별 정렬
	        	//(학점/12+중요도)*2^(10-n) //10일 이전
	        	//(학점/12+중요도)*2^(n-10)*-1//10일 이후
	        	long n1,n2;
	        	long calc1,calc2;
	        	double sav1=0,sav2=0,temp=0;
	        	
	        	if(order==0) {
		        	for(int i=0;i<jsonList.size();i++){
		        		for(int k=0;k<jsonList.size()-1;k++) {
		        			d1=format.parse((String)jsonList.get(k).get("dueDate"));
		        			d2=format.parse((String)jsonList.get(k+1).get("dueDate"));
		        			
		        			calc1=d1.getTime()-now.getTime();
		        			calc2=d2.getTime()-now.getTime();
		        			
		        			n1=calc1/(24*60*60*1000);
		        			n2=calc2/(24*60*60*1000);
		        			
		        			System.out.println("##########################n1:"+n1);
		        			System.out.println("##########################n2:"+n2);
		        			if(n1<=10) {
		        				sav1=((creditList[k]/12.0)+(int)jsonList.get(k).get("importance"))*Math.pow(2,10-n1);
		        			}else if(n1>10) {
		        				sav1=((creditList[k]/12.0)+(int)jsonList.get(k).get("importance")*Math.pow(2, n1-10)*-1);
		        			}
		        			
		        			if(n2<=10) {
		        				sav2=((creditList[k+1]/12.0)+(int)jsonList.get(k+1).get("importance"))*Math.pow(2,10-n2);
		        			}else if(n2>10) {
		        				sav2=((creditList[k+1]/12.0)+(int)jsonList.get(k+1).get("importance")*Math.pow(2, n2-10)*-1);
		        			}
		        			
		        			if(sav1<sav2) {
		        				tmp.putAll(jsonList.get(k));
		        				jsonList.get(k).clear();
		        				jsonList.get(k).putAll(jsonList.get(k+1));
		        				jsonList.get(k+1).clear();
		        				jsonList.get(k+1).putAll(tmp);
		        			
		        				temp=creditList[k];
		        				creditList[k+1]=creditList[k];
		        				creditList[k]=temp;
		        			}
		        		}
		        	}	
	        	}
	        	else if(order==1) {
		        	for(int i=0;i<jsonList.size();i++){
		        		for(int k=0;k<jsonList.size()-1;k++) {
		        			d1=format.parse((String)jsonList.get(k).get("dueDate"));
		        			d2=format.parse((String)jsonList.get(k+1).get("dueDate"));
		        		
		        			if(d1.compareTo(d2)>0) {
		        				tmp.putAll(jsonList.get(k));
		        				jsonList.get(k).clear();
		        				jsonList.get(k).putAll(jsonList.get(k+1));
		        				jsonList.get(k+1).clear();
		        				jsonList.get(k+1).putAll(tmp);
		        			}
		        		}
		        	}	
	        	}
	        	else if(order==2) {
		        	for(int i=0;i<jsonList.size();i++){
		        		for(int k=0;k<jsonList.size()-1;k++) {
		        			
		        			if((int)(jsonList.get(k).get("importance"))<(int)(jsonList.get(k+1).get("importance"))) {
		        				tmp.putAll(jsonList.get(k));
		        				jsonList.get(k).clear();
		        				jsonList.get(k).putAll(jsonList.get(k+1));
		        				jsonList.get(k+1).clear();
		        				jsonList.get(k+1).putAll(tmp);
		        			}
		        		}
		        	}	
	        	}
	        	
	        	for(int i=0;i<jsonList.size();i++) {
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
		    			param.clear();
		    			//param.put("stuId",homeworkDtoList.get(i).getStuId());
		    			JSONObject jSONObject = new JSONObject();
		    			//List<UserDto> pushList=userDao.pushUser(param);
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
		
		
}
