package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardReplyAdd implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tmp = request.getParameter("authUserNo");
		Long no = Long.parseLong(tmp);
		
		tmp = request.getParameter("gNo");
		Integer gNo = Integer.parseInt(tmp);
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		tmp = request.getParameter("depth");
		Integer depth = Integer.parseInt(tmp);
		
		tmp = request.getParameter("oNo");
		Integer oNo = Integer.parseInt(tmp);
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setgNo(gNo);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setDepth(depth);
		vo.setoNo(oNo);
		
		new BoardRepository().updateParent(vo);
		
		new BoardRepository().insertReply(vo);
	    
	    WebUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}