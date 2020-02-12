package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardModifyAdd implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tmp = request.getParameter("no");
		
		Long no = Long.parseLong(tmp);
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setName(name);
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardRepository().update(vo);
	    
	    WebUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
