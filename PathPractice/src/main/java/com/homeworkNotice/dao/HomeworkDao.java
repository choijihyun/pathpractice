package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.UserDto;


@Repository
public class HomeworkDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	public int insertHomework(HashMap<Object,Object> param) {
		return sqlSession.insert("HomeworkDao.insertHomework",param);
	}
	
	public int deleteHomework(HashMap<Object, Object> param) {
		return sqlSession.delete("HomeworkDao.deleteHomework",param);
	}
	public int deleteHomework2(HashMap<Object, Object> param) {
		return sqlSession.delete("HomeworkDao.deleteHomework2",param);
	}
	public int deleteAllHomework(HashMap<Object, Object> param) {
		return sqlSession.delete("HomeworkDao.deleteAllHomework",param);
	}
	public int updateHomework(HashMap<Object,Object> param) {
		return sqlSession.update("HomeworkDao.updateHomework",param);
	}
	
	public List<HomeworkDto> selectHomework(HashMap<Object,Object> param) {
		System.out.println("Dao param:::::::"+param);
		
		return sqlSession.selectList("HomeworkDao.selectHomework", param);
	}
	
	public List<HomeworkDto> pushHomework() {
		return sqlSession.selectList("HomeworkDao.pushHomework");
	}
	public List<HomeworkDto> selectTeamHomework(HashMap<Object,Object> param) {
		return sqlSession.selectList("HomeworkDao.selectTeamHomework", param);
	}
	
	public List<HomeworkDto> selectSuccessHomework(HashMap<Object,Object> param) {
		return sqlSession.selectList("HomeworkDao.selectSuccessHomework", param);
	}
	
	public List<HomeworkDto> selectNotSuccessHomework(HashMap<Object,Object> param) {
		return sqlSession.selectList("HomeworkDao.selectNotSuccessHomework", param);
	}
	
}
