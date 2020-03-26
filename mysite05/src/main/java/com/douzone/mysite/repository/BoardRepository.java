package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BoardVo findContents(Long no) {
		sqlSession.update("board.update", no);
		
		return sqlSession.selectOne("board.findContents", no);
	}
	
	public List<BoardVo> findAll(int page, String kwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("kwd", kwd);
		
		List<BoardVo> list = sqlSession.selectList("board.findAll", map);
		return list;
	}
	
	public List<BoardVo> findKwd(String kwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("kwd", kwd);
		
		return sqlSession.selectList("board.findKwd", map);
	}
	
	public void insertReply(BoardVo vo) {
		sqlSession.update("board.updateParent", vo);
		sqlSession.insert("board.insertReply", vo);
	}
	
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public void modify(BoardVo vo) {
		sqlSession.update("board.modify", vo);
	}
	
	public void updateParent(BoardVo vo) {
		sqlSession.update("board.updateParent", vo);
	}
	
	public void delete(Long no) {
		sqlSession.delete("board.delete", no);
	}
	
	public BoardVo find(Long no) {
		BoardVo vo = sqlSession.selectOne("board.find", no);
		
		return vo;
	}
}