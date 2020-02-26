package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping({ "", "list" })
	public String list(Model model) {
		List<BoardVo> list = boardService.findAll();
		model.addAttribute("list", list);

		return "board/list";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(HttpSession session, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findContents(no);
		model.addAttribute("vo", vo);

		return "board/view";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		// 접근제어
		////////////////////////////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		boardService.insert(vo);

		return "redirect:/board";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(HttpSession session, @PathVariable("no") Long no, Model model) {
		// 접근제어
		////////////////////////////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////
		model.addAttribute("no", no);

		boardService.delete(no);

		return "redirect:/board";
	}

	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(HttpSession session, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findContents(no);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(HttpSession session, BoardVo vo) {
		boardService.modify(vo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String reply(HttpSession session, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.find(no);
		model.addAttribute("vo", vo);
		
		return "board/write";
	}
}