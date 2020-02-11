package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tmp = request.getParameter("no");
		
		Long no = Long.parseLong(tmp);
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String contents = request.getParameter("contents");
		
		BoardVo boardVo = new BoardVo();
		boardVo.setNo(no);
		boardVo.setTitle(title);
		boardVo.setName(name);
		boardVo.setContents(contents);
		
		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}
}