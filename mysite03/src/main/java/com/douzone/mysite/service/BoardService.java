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

//	public void insert(GuestbookVo vo) {
//		boardRepository.insert(vo);
//	}
//
//	public void delete(Long no, String password) {
//		boardRepository.delete(no, password);
//	}
}