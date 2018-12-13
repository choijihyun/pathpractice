package com.homeworkNotice.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.homeworkNotice.dao.UserDao;
import com.homeworkNotice.dao.TeamDao;
import com.homeworkNotice.dto.TeamDto;

@Controller
public class TeamController {
	
	//주호 안쓰는거 같아서 주석 쳐놔써!!!
/*	@Autowired
	private SubjectDao	subjectDao;
	private UserDao userDao;*/
	@Autowired
	private TeamDao teamDao;

	//insert
	@ResponseBody
	@RequestMapping(value = "/team/insertTeam.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String insertTeam(
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "leaderName", required=true) String leaderName,
			@RequestParam(value = "leaderNum", required=true) String leaderNum, //class ���� �������� �̷��� ���� ! ��Ÿ�ƴ�
			@RequestParam(value = "memOneName", required=true) String memOneName,
			@RequestParam(value = "memOneNum", required=true) String memOneNum, //class ���� �������� �̷��� ���� ! ��Ÿ�ƴ�
			@RequestParam(value = "memTwoName", required=false) String memTwoName,
			@RequestParam(value = "memTwoNum", required=false) String memTwoNum, //class ���� �������� �̷��� ���� ! ��Ÿ�ƴ�
			@RequestParam(value = "memThreeName", required=false) String memThreeName,
			@RequestParam(value = "memThreeNum", required=false) String memThreeNum, //class ���� �������� �̷��� ���� ! ��Ÿ�ƴ�
			@RequestParam(value = "memFourName", required=false) String memFourName,
			@RequestParam(value = "memFourNum", required=false) String memFourNum
			){
		//add���� �й�
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("leaderName",leaderName);
		param.put("leaderNum",leaderNum);
		param.put("memOneName",memOneName);
		//주호 memoneNum -> memOneNum
		param.put("memOneNum",memOneNum);
		param.put("memTwoName",memTwoName);
		param.put("memTwoNum",memTwoNum);
		param.put("memThreeName",memThreeName);
		param.put("memThreeNum",memThreeNum);
		param.put("memFourName",memFourName);
		param.put("memFourNum",memFourNum);


		int result=0;
		try {
			result=teamDao.insertTeam(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		System.out.println(result);
    	JSONObject jSONObject = new JSONObject();
    	
    	if(result==1) {
    		jSONObject.put("result", param.get("teamNum"));    		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}


	//update
	@ResponseBody
    @RequestMapping(value = "/team/updateTeam.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String updateTeam(
    			Model model,
    			@RequestParam(value = "teamNum",required=true) final int teamNum,
    			@RequestParam(value = "leaderName", required=true) String leaderName,
    			@RequestParam(value = "leaderNum", required=true) String leaderNum,
    			@RequestParam(value = "memOneName", required=true) String memOneName,
    			@RequestParam(value = "memOneNum", required=true) String memOneNum,
    			@RequestParam(value = "memTwoName", required=false) String memTwoName,
    			@RequestParam(value = "memTwoNum", required=false) String memTwoNum,
    			@RequestParam(value = "memThreeName", required=false) String memThreeName,
    			@RequestParam(value = "memThreeNum", required=false) String memThreeNum,
    			@RequestParam(value = "memFourName", required=false) String memFourName,
    			@RequestParam(value = "memFourNum", required=false) String memFourNum) { 
		HashMap<Object, Object> param=new HashMap<Object, Object>(); 
					

		param.put("teamNum",teamNum);
		param.put("leaderName",leaderName);
		param.put("leaderNum",leaderNum);
		param.put("memOneName",memOneName);
		//주호 memoneNum -> memOneNum
		param.put("memOneNum",memOneNum);
		param.put("memTwoName",memTwoName);
		param.put("memTwoNum",memTwoNum);
		param.put("memThreeName",memThreeName);
		param.put("memThreeNum",memThreeNum);
		param.put("memFourName",memFourName);
		param.put("memFourNum",memFourNum);

		
		int result=0;
		try {
			result=teamDao.updateTeam(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println("updateeeeeeeeeeeeeeee"+result);
    	JSONObject jSONObject = new JSONObject();
    	if(result==1) {
    		jSONObject.put("result", "1");		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}
	

	
	
	//select
		@ResponseBody
		@RequestMapping(value = "/team/searchTeam.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
		public String searchTeam(
				Locale locale, 
				Model model, 
				@RequestParam(value = "teamNum", required=true) final int teamNum) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("teamNum",teamNum);			
			
			List<TeamDto> teamDtoList =teamDao.searchTeam(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!teamDtoList.isEmpty()) {
	        	for(int i=0;i<teamDtoList.size();i++) {
	        		JSONObject jSONObject = new JSONObject();
	        		jSONObject.put("teamNum",teamDtoList.get(i).getTeamNum());
	        		jSONObject.put("leaderName",teamDtoList.get(i).getLeaderName());
	        		jSONObject.put("leaderNum",teamDtoList.get(i).getLeaderNum());
	        		jSONObject.put("memOneName",teamDtoList.get(i).getMemOneName());
	        		jSONObject.put("memOneNum",teamDtoList.get(i).getMemOneNum());
	        		jSONObject.put("memTwoName",teamDtoList.get(i).getMemTwoName());
	        		jSONObject.put("memTwoNum",teamDtoList.get(i).getMemTwoNum());
	        		jSONObject.put("memThreeName",teamDtoList.get(i).getMemThreeName());
	        		jSONObject.put("memThreeNum",teamDtoList.get(i).getMemThreeNum());
	        		jSONObject.put("memFourName",teamDtoList.get(i).getMemFourName());
	        		jSONObject.put("memFourNum",teamDtoList.get(i).getMemFourNum());
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<teamDtoList.size();i++){
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
	    @RequestMapping(value = "/team/deleteTeam.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
	    public String deleteTeam(
	    			Model model,
	    			@RequestParam(value = "teamNum", required=true) final int teamNum) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
			HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
	    			
			param.put("teamNum",teamNum);	
			//
			System.out.println(param);
			
			int result=0;
			try {
				result=teamDao.deleteTeam(param);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			System.out.println(result);
	    	JSONObject jSONObject = new JSONObject();
	    	
	    	if(result==1) {
	    		jSONObject.put("result", "1");//����     		
	    	}
	    	else {
	    		jSONObject.put("result", "0");
	    	}
	    	return jSONObject.toString();
		}

	
}
