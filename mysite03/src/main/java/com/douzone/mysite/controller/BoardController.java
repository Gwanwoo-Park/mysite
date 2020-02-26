package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"", "list"})
	public String list(Model model) {
		List<BoardVo> list = boardService.findAll();
		model.addAttribute("list", list);
		
		return "board/list";
	}
//	
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
//	public String add(GuestbookVo vo) {
//		boardService.insert(vo);
//		
//		return "redirect:/guestbook";
//	}
//	
//	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
//	public String delete(@PathVariable("no") Long no, Model model) {
//		model.addAttribute("no",no);
//		
//		return "guestbook/delete";
//	}
//	
//	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
//	public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
//		boardService.delete(no, password);
//		return "redirect:/guestbook";
//	}
}