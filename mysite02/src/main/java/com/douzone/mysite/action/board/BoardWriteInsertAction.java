package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardWriteInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tmp = request.getParameter("no");
		Long no = Long.parseLong(tmp);
		
	    String passwordResult = new GuestbookRepository().findPassword(no);
	    String password = request.getParameter("password");
		
	    System.out.println("passwordResult: " + passwordResult);
	    System.out.println("password: " + password);
	    
	    if (password.equals(passwordResult))
	    {
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			
			new GuestbookRepository().delete(vo);
	    } else System.out.println("안돼 돌아가!");
	    
	    WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
	}

}
