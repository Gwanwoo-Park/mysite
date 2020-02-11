package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tmp = request.getParameter("no");
		Long no = Long.parseLong(tmp);
		
		String title = request.getParameter("title");
		
		String contents = new BoardRepository().findContents(no);
		request.setAttribute("contents", contents);
		request.setAttribute("title", title);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}
}