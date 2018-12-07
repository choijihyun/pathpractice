package com.homeworkNotice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homeworkNotice.dto.HomeworkDto;
import com.homeworkNotice.dto.SubjectDto;
import com.homeworkNotice.dto.TeamDto;

@Repository
public class TeamDao {
	
	@Autowired
    private SqlSession sqlSession;    
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public int insertTeam(HashMap<Object,Object> param) {
		return sqlSession.insert("TeamDao.insertTeam",param);
	}
	public int deleteTeam(HashMap<Object, Object> param) {
		return sqlSession.delete("TeamDao.deleteTeam",param);
	}
	public int deleteAllTeam(HashMap<Object, Object> param) {
		return sqlSession.delete("TeamDao.deleteAllTeam",param);
	}
	public int updateTeam(HashMap<Object,Object> param) {
		return sqlSession.update("TeamDao.updateTeam",param);
	}
	
	public List<TeamDto> searchTeam(HashMap<Object,Object> param) {
		return sqlSession.selectList("TeamDao.searchTeam", param);
	}
}
