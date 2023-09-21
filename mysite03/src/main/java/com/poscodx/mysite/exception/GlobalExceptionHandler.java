package com.poscodx.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 여러 컨트롤러 클래스에서 발생하는 예외를 한 곳에서 처리
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);
	
	//전체 애플리케이션에서 발생하는 예외를 처리
	@ExceptionHandler(Exception.class)
	public String handlerException(Model model, Exception e) {
		// 1. 로깅(Logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
//      System.out.println(errors.toString());
		logger.error(errors.toString());

		// 2. 사과 페이지
		model.addAttribute("errors", errors.toString());
		return "error/exception";
	}
}
