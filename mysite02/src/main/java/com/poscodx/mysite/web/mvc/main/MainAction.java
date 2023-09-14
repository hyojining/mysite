package com.poscodx.mysite.web.mvc.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.web.mvc.Action;

public class MainAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 주어진 요청을 해당 JSP 파일로 포워딩
		// JSP 페이지에서 클라이언트의 요청에 관한 정보를 읽고
		// JSP 페이지에서 클라이언트로 응답을 보냄
		request
		.getRequestDispatcher("/WEB-INF/views/main/index.jsp")
		.forward(request, response);
	}
}