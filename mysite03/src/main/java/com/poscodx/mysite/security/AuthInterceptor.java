package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor { // 사용자의 인증 및 권한 여부 처리
	
	/**
	 * HTTP 요청이 컨트롤러에 도달하기 전에 실행되는 메서드
	 * 사용자가 요청한 리소스에 대한 접근 권한을 검사하고 관리
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if(!(handler instanceof HandlerMethod)) {
			// DefaultServletHanlder가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3-1. Handler Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 3-2. Handler Method의 @Auth가 없는 경우, Type(Class)의 @Auth 가져오기
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		// 4. @Auth 가 없는 경우
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth 가 붙어 있는 경우, 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser"); // 현재 세션에서 사용자 정보(authUser)를 가져옴

		if(authUser == null) { // 사용자가 인증되었는지 확인
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 6. 권한(Authorization) 체크를 위해서 @Auth의 Role 가져오기("USER", "ADMIN")
		String role = auth.Role();
		
		if("USER".equals(role)) { // USER인 경우, 권한을 확인하지 않고 요청을 계속 진행
			return true;
		}
		
		if(!"ADMIN".equals(authUser.getRole())) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 7. 인증 확인!!!
		return true;
	}

}