package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping( "" )
	public String list(@RequestParam(value = "page", required = true, defaultValue = "1") int page,
					   @RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
				       Model model) {
		
			Map<String, Object> map = new HashMap<>();
			map = boardService.findAll(page, kwd);
			model.addAllAttributes(map);
			
		return "board/list";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findContents(no);
		model.addAttribute("vo", vo);

		return "board/view";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo vo) {
		boardService.insert(vo);

		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		
		model.addAttribute("no", no);

		boardService.delete(no);

		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findContents(no);
		model.addAttribute("vo", vo);

		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, BoardVo vo) {
		boardService.modify(vo);

		return "redirect:/board";
	}

	@Auth
	@RequestMapping(value = "/reply/{no}", method = RequestMethod.GET)
	public String reply(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.find(no);
		model.addAttribute("vo", vo);

		return "board/write";
	}
}