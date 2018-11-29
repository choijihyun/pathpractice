package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.UserDto;

/**
 * @writer      : JungHyun 
 * @createDate  : 2018. 1. 21.
 * @description : user���̺��� ���� ������~
 */

@Repository
public class UserDao {
	@Autowired
    private SqlSession sqlSession;    
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<UserDto> selectUser(HashMap<Object, Object> param) { 
		return sqlSession.selectList("UserDao.selectUser", param);//�̰ǹ�����??????? 
		//user-mapping.xml�� �������� sql(����)�� userDao(ĳ����)�� ����ϴ� �κ��̾�
	}
	public List<UserDto> selectUserInfo(HashMap<Object, Object> param) { 
		return sqlSession.selectList("UserDao.selectUserInfo", param);//�̰ǹ�����??????? 
		//user-mapping.xml�� �������� sql(����)�� userDao(ĳ����)�� ����ϴ� �κ��̾�
	}
	public List<UserDto> selectAllList() {
		return sqlSession.selectList("UserDao.selectAllUser");//�̰ŵ� ������???????
	}
	public int insertUser(HashMap<Object, Object> param) {
		return sqlSession.insert("UserDao.insertUser", param);		
	}
	
	public int updatePw(HashMap<Object, Object> param) {
		return sqlSession.insert("UserDao.updatePw", param);		
	}
	
	public int updateUser(HashMap<Object,Object> param) {
		return sqlSession.update("UserDao.updateUser",param);
	}
	
	public int deleteUser(HashMap<Object, Object> param) {
		return sqlSession.delete("UserDao.deleteUser",param);
	}
	
	public List<UserDto> isUseCookie(HashMap<Object, Object> param) {
		return sqlSession.selectList("UserDao.selectCookie",param);
	}
}
