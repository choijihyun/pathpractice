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
    @RequestMapping(value = "/timeTable/insertTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String insertTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subjectKey", required=true) final int subjectKey) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
				
		param.put("stuId",stuId);	
		param.put("subjectKey",subjectKey);
		
		//
		System.out.println(param);
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
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
    	//�׷��� ���⼭ ���� or ���� �����ؼ� �ȵ���̵忡 json �����͸� ����� �������ٰž�
    	if(result==1) {
    		jSONObject.put("result", "1");//����    		
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
        if(!timeTableDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
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
        else {//������ ������� �������� �ѷ��ش�

    		JSONObject jSONObject = new JSONObject();
        	jSONObject.put("result", "no data");
        	
        	return jSONObject.toString();
        }
	}


	@ResponseBody
    @RequestMapping(value = "/timeTable/deleteAllTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String deleteAllTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
    			
		param.put("stuId",stuId);
		
		System.out.println(param);
		
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=timeTableDao.deleteAllTimeTable(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
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

	
	
	@ResponseBody
    @RequestMapping(value = "/timeTable/deleteTimeTable.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String deleteTimeTable(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subjectKey", required=true) final int subjectKey) { // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x
		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
    			
		param.put("stuId",stuId);	
		param.put("subjectKey",subjectKey);
		//
		System.out.println(param);
		
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=timeTableDao.deleteTimeTable(param);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		System.out.println(result);
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

	
	
	
	}
