package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> findAll() {
		List<BoardVo> list = boardRepository.findAll();
		
		return list;
	}
	
	public BoardVo findContents(Long no) {
		BoardVo vo = boardRepository.findContents(no);
		
		return vo;
	}

	public void insert(BoardVo vo) {
		if(vo.getgNo() == null) {
			boardRepository.insert(vo);
		} else boardRepository.insertReply(vo);
		
	}

	public void delete(Long no) {
		boardRepository.delete(no);
	}

	public void modify(BoardVo vo) {
		boardRepository.modify(vo);
	}

	public BoardVo find(Long no) {
		BoardVo vo = boardRepository.find(no);
		
		return vo;
	}
}