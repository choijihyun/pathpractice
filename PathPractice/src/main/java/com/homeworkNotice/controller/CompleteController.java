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

import com.homeworkNotice.dao.CompleteDao;
import com.homeworkNotice.dto.CompleteDto;


@Controller
public class CompleteController {
	
	@Autowired
	private CompleteDao completeDao;
	

	//insert
	@ResponseBody
    @RequestMapping(value = "/complete/insertComplete.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)// value��� ���� ����, get��� ���
    public String insertUser(
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId,
    			@RequestParam(value = "subNo", required=true) String subNo){ // �̷��� 5���� �Ķ���͸� �޾ƿ��� ���� �Ⱦ��� x

		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
		param.put("stuId",stuId);
		param.put("subNo",subNo);
		
		//
		
		//�� �Լ�(url)�� ȸ�������� �� �����̱� ������
		//����� ���� or ���и� �˷� �ָ� ��
		//int ������ ��ȯ�� �Ǵµ� 1�̸� ���� ������ ���̸� ����!!
		int result=0;
		try {
			result=completeDao.insertComplete(param);
			
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
	

	@ResponseBody
    @RequestMapping(value = "/complete/searchComplete", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String searchComplete(
    			Locale locale, 
    			Model model,
    			@RequestParam(value = "stuId", required=true) String stuId) {

		HashMap<Object, Object> param=new HashMap<Object, Object>(); //������ id���� hashmap ������ִϱ� ������ ����
		
		param.put("stuId",stuId);
		
		
    	List<CompleteDto> completeDtoList=completeDao.searchComplete(param);
    	
    	JSONArray jSONArray=new JSONArray();
    	List<JSONObject> jsonList=new ArrayList<JSONObject>();
        if(!completeDtoList.isEmpty()) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
        	for(int i=0;i<completeDtoList.size();i++) {
        		JSONObject jSONObject = new JSONObject();
        		jSONObject.put("stuId",completeDtoList.get(i).getStuId());
        		jSONObject.put("subNo", completeDtoList.get(i).getSubNo());
        		jSONArray.add(jSONObject);
        		
        		jsonList.add((JSONObject)jSONArray.get(i));
        		
        		System.out.println(jsonList);
        	}
        	
        	System.out.println(jsonList);
        	
        	jSONArray.clear();
        	for(int i=0;i<completeDtoList.size();i++){
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


	//delete
	@ResponseBody
	@RequestMapping(value = "/complete/deleteAllComplete.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String deleteAllComplete(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "stuId", required=true) String stuId) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		
		
		int result=completeDao.deleteAllComplete(param);
	
		
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
	@RequestMapping(value = "/complete/deleteComplete.json", produces="application/json;text/plain;charset=UTF-8", method = RequestMethod.GET)//�� �κ��� url //get������� �� /user/getUserPwdInfo.json�̶�� url�� ���ͼ� ���� Ȯ�� �� �� �ִ�.
	public String deleteComplete(//url�� ����(����)�� �Լ�
			Locale locale, //�ȵ���̵忡�� ���� �Ķ����
			Model model, //�ȵ���̵忡�� ���� �Ķ����
			@RequestParam(value = "stuId", required=true) String stuId,
			@RequestParam(value="subNo",required=true) String subNo) {
		
		HashMap<Object, Object> param=new HashMap<Object, Object>();
		
		param.put("stuId",stuId);
		param.put("subNo",subNo);
		
		
		int result=completeDao.deleteComplete(param);
	
		
		JSONObject jSONObject = new JSONObject();
		if(result==1) {//��ȯ���� �����Ͱ� ��ȿ�ϸ�(db�� ������) ������ ȭ�鿡 ����� �ѷ��ش�
				jSONObject.put("result","1");//id�� �����ϰ� ����� �´� ���
		}
		else {//������ ������� �������� �ѷ��ش�
			jSONObject.put("result", "0"); //id�� �������� �ʴ°��
		}
		return jSONObject.toString();//��û�� ������� ��ȯ���ش�.
	}


}
