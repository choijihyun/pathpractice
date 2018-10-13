package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.SubjectDto;
import com.homeworkNotice.dto.TimeTableDto;

@Repository
public class TimeTableDao {
	
	@Autowired
    private SqlSession sqlSession;    
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertTimeTable(HashMap<Object, Object> param) {
		return sqlSession.insert("TimeTableDao.insertTimeTable",param);		
	}
	
	public List<TimeTableDto> searchTimeTable(HashMap<Object,Object> param) {
		return sqlSession.selectList("TimeTableDao.searchTimeTable", param);
	}

	public int deleteTimeTable(HashMap<Object, Object> param) {
		return sqlSession.delete("TimeTableDao.deleteTimeTable",param);
	}
	
	public int deleteAllTimeTable(HashMap<Object, Object> param) {
		return sqlSession.delete("TimeTableDao.deleteAllTimeTable",param);
	}
}
