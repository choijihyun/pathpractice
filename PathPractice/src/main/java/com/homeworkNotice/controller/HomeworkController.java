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
import com.homeworkNotice.dto.CompleteDto;
import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.UserDto;

@Controller
public class HomeworkController {

	@Autowired
	private HomeworkDao	homeworkDao;
	
	
	//insert
	@ResponseBody
    @RequestMapping(value = "/homework/insertHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String insertUser(
    			Model model,

    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "dueDate", required=true) String dueDate,
    			@RequestParam(value = "importance", required=true) final int importance,
    			@RequestParam(value = "title", required=true) String title,
    			@RequestParam(value = "contents", required=true) String contents,
    			@RequestParam(value = "subNo", required=true) String subNo,
    			@RequestParam(value = "success", required=true) final int success,
    			@RequestParam(value = "team",required=false) String team) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
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
    	//�׷��� ���⼭ ���� or ���� �����ؼ� �ȵ���̵忡 json �����͸� ����� �������ٰž�
    	if(result==1) {
    		jSONObject.put("result", "1");//����    		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}


	//delete
	@ResponseBody
	@RequestMapping(value = "/homework/deleteAllHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String deleteAllHomework(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "stuId", required=true) String stuId) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		
		int result=homeworkDao.deleteAllHomework(param);
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
				jSONObject.put("result","1");//id�� �����ϰ� ����� �´� ���
		}
		else {//������ ������� �������� �ѷ��ش�
			jSONObject.put("result", "0"); //id�� �������� �ʴ°��
		}
		return jSONObject.toString();//��û�� ������� ��ȯ���ش�.
	}

	
	//delete
	@ResponseBody
	@RequestMapping(value = "/homework/deleteHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String deleteHomework(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value="assignNo",required=true) final int assignNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("assignNo",assignNo);
		
		
		int result=homeworkDao.deleteHomework(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
				jSONObject.put("result","1");//id�� �����ϰ� ����� �´� ���
		}
		else {//������ ������� �������� �ѷ��ش�
			jSONObject.put("result", "0"); //id�� �������� �ʴ°��
		}
		return jSONObject.toString();//��û�� ������� ��ȯ���ش�.
	}

	
	//update
	@ResponseBody
    @RequestMapping(value = "/homework/updateHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
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
		
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
					
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
    		jSONObject.put("result", "1");//����    		
    	}
    	else {
    		jSONObject.put("result", "0");
    	}
    	return jSONObject.toString();
	}
	

	
	
	//select
		@ResponseBody
		@RequestMapping(value = "/homework/selectHomework.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
		public String selectHomework(//url�� ����(����)�� �Լ�
				Locale locale, //�ȵ���̵忡�� ���� �Ķ����
				Model model, //�ȵ���̵忡�� ���� �Ķ����
				@RequestParam(value = "stuId", required=true) String stuId,
				@RequestParam(value = "select", required=true) final int select) {
			
			HashMap<Object, Object> param=new HashMap<Object, Object>();
			
			param.put("stuId",stuId);			
			
			List<HomeworkDto> homeworkDtoList =homeworkDao.selectHomework(param);	

	    	JSONArray jSONArray=new JSONArray();
	    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
			
	    	if(!homeworkDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
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
	        		
	        		jSONArray.add(jSONObject);
	        		
	        		jsonList.add((JSONObject)jSONArray.get(i));
	        		
	        		System.out.println(jsonList);
	        	}
	        	
	        	Collections.sort( jsonList, new Comparator<JSONObject>() {

	    		    public int compare(JSONObject a, JSONObject b) {
	    		        String valA = new String();
	    		        String valB = new String();
	    		        int vA,vB;


	    		        return valA.compareTo(valB);
	    		    }
	    		});
	        	System.out.println(jsonList);
	        	
	        	jSONArray.clear();
	        	for(int i=0;i<homeworkDtoList.size();i++){
	        		jSONArray.add(jsonList.get(i));
	        	}
	        	
	        	JSONObject jsObject=new JSONObject();
	        	jsObject.put("result", jSONArray);

	            return jsObject.toString();
	        } 
	        else {//������ ������� �������� �ѷ��ش�

	    		JSONObject jSONObject = new JSONObject();
	        	jSONObject.put("result", "no data");
	        	
	        	return jSONObject.toString();
	        }
		}

}
