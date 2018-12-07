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
		return sqlSession.selectList("UserDao.selectUser", param);
	}
	public List<UserDto> selectUserInfo(HashMap<Object, Object> param) { 
		return sqlSession.selectList("UserDao.selectUserInfo", param);
	}
	public List<UserDto> selectAllList() {
		return sqlSession.selectList("UserDao.selectAllUser");
	}
	public List<UserDto> pushUser(HashMap<Object,Object> param) {
		return sqlSession.selectList("UserDao.pushUser",param);//�̰ŵ� ������???????
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

	public int insertToken(HashMap<Object, Object> param) {
		return sqlSession.insert("UserDao.insertToken", param);		
	}

	public List<UserDto> checkUserWithSessionKey(HashMap<Object, Object> param) { 
		return sqlSession.selectList("UserDao.checkUserWithSessionKey", param);
	}
	
	public int keepLogin(HashMap<Object, Object> param) {
		return sqlSession.update("UserDao.keepLogin", param);		
	}
	
	public int checkbox(HashMap<Object,Object> param) {
		return sqlSession.update("UserDao.checkbox",param);
	}
}
