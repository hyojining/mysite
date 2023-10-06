package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscodx.mysite.vo.UserVo;

/**
 * 사용자 정보를 메서드의 인자로 주입하는 데 사용되는 클래스
 * 컨트롤러 메서드의 매개변수에 `@AuthUser` 어노테이션을 사용하여 사용자 정보 ('UserVo')를 주입할 수 있도록 한다.
 */
public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	// 컨트롤러 메서드의 인자를 해결하고 값을 반환하는 메서드
	@Override
	public Object resolveArgument(
		MethodParameter parameter, // 메서드의 매개변수 정보
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, // 웹 요청 정보
		WebDataBinderFactory binderFactory) throws Exception {
		
		// 특정 매개변수가 현재 resolver에서 처리 가능한지 여부 확인
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest(); // HTTP 요청
		HttpSession session = request.getSession(); // 세션 정보
		return session.getAttribute("authUser"); // 세션에서 "authUser"라는 이름으로 저장된 사용자 정보(`UserVo` 객체)
	}

	// `AuthUser` 어노테이션이 매개변수에 붙어 있는지 확인하고, 매개변수의 타입이 `UserVo`인지 확인하여 처리 가능 여부 결정
	@Override
	public boolean supportsParameter(MethodParameter parameter) { 
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안 붙어 있으면,
		if(authUser == null) {
			return false;
		}
		
		// Parameter Type이 UserVo가 아니면,
		if(!parameter.getParameterType().equals(UserVo.class)) {
			return false;
		}
		
		return true;
	}
	
}