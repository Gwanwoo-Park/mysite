package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<BoardVo> list = new BoardRepository().findAll();

		request.setAttribute("list", list);

		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}

		request.setAttribute("page", page);

		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("authUser"));

		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}
}