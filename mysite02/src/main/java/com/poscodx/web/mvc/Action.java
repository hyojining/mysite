package com.poscodx.web.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 서블릿 요청을 처리하고 응답을 생성하는 데 사용
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
