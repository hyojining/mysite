package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.web.mvc.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noParam = request.getParameter("no");
		Long no = Long.parseLong(noParam);
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");

		new BoardDao().modify(no, title, contents);
		
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
