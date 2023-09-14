package com.poscodx.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.web.mvc.main.MainActionFactory;
import com.poscodx.web.mvc.Action;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String configPath = getServletConfig().getInitParameter("config");
		System.out.println(configPath);
		
		super.init();
	}

	// HTTP GET 요청을 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = request.getParameter("a"); // 클라이언트로부터 액션을 요청받음
		Action action = new MainActionFactory().getAction(actionName); // 요청에 따른 액션 객체 생성
		action.execute(request, response); // 액션 실행
	}

	// HTTP POST 요청을 처리하기 위해 doGet 메소드 호출
	// GET 및 POST 요청 모두가 동일한 로직으로 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}