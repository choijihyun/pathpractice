package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.SubjectDto;

@Repository
public class SubjectDao {
	@Autowired
    private SqlSession sqlSession;    
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<SubjectDto> searchSubject(HashMap<Object,Object> param) {
		return sqlSession.selectList("SubjectDao.searchSubject", param);
	}
	public List<SubjectDto> searchDirSubject(HashMap<Object,Object> param) {
		return sqlSession.selectList("SubjectDao.searchDirSubject", param);
	}
	public List<SubjectDto> search1Subject(HashMap<Object,Object> param) {
		return sqlSession.selectList("SubjectDao.search1Subject", param);
	}
	public List<SubjectDto> search2Subject(HashMap<Object,Object> param) {
		return sqlSession.selectList("SubjectDao.search2Subject", param);
	}
	public List<SubjectDto> search3Subject(HashMap<Object,Object> param) {
		return sqlSession.selectList("SubjectDao.search3Subject", param);
	}
	public List<SubjectDto> searchCredit(HashMap<Object,Object> param) {
		System.out.println("Dao param:::::::"+param);
		return sqlSession.selectList("SubjectDao.searchCredit", param);
	}
	public int insertSubject(HashMap<Object, Object> param) {
		return sqlSession.insert("SubjectDao.insertSubject", param);		
	}

	public int deleteSubject(HashMap<Object, Object> param) {
		return sqlSession.delete("SubjectDao.deleteSubject",param);
	}

}
