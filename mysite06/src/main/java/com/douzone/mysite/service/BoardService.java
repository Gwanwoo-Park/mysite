package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public Map<String, Object> findAll(int page, String kwd) {
		
		List<BoardVo> list = boardRepository.findAll(page, kwd);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("listCount", list.size());
		
		int pageCount;
		if(list.size() % 5 == 0) {
			pageCount = list.size() / 5;
		} else {
			pageCount = list.size() / 5 + 1;
		}
		map.put("pageCount", pageCount);
		map.put("page", page);
		map.put("kwd", kwd);
		
		int limit = 0;
		if(page > 1) {
			limit = 5 * (page - 1);
		}
		map.put("limit", limit);
		
		int sibalPage = 1;
		if(page % 5 == 1) {
			sibalPage = page;
		}
		map.put("sibalPage", sibalPage);
		
		return map;
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

	public List<BoardVo> findKwd(String kwd) {
		List<BoardVo> list = boardRepository.findKwd(kwd);
		
		return list;
	}
}