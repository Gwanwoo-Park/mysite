package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardWriteInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tmp = request.getParameter("no");
		
		Long no = Long.parseLong(tmp);
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		System.out.println("왜 널이지" + no);
		
	    request.setAttribute("title", title);
	    request.setAttribute("contents", contents);
	    
	    new BoardRepository().insert(no, title, contents);
	    
	    WebUtil.redirect(request.getContextPath() + "/board", request, response);
	}
}