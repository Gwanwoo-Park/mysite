package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = guestbookRepository.findAll();
		
		return list;
	}

	public void insert(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}

	public void delete(Long no, String password) {
		guestbookRepository.delete(no, password);
	}

	public List<GuestbookVo> getMessageList(Long startNo) {
		return guestbookRepository.findAll(startNo);
	}
	
	public boolean writeMessage( GuestbookVo vo ) {
		int count = guestbookRepository.insert2(vo);
		return count == 1;
	}
}