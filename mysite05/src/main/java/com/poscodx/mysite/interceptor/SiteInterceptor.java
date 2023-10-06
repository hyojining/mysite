package com.poscodx.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

/**
 * 모든 요청이 컨트롤러에 도달하기 전에
 * 사이트 정보를 로드하고 servlet context에 저장한다.
 * 이렇게 저장된 정보는 다른 컴포넌트에서 접근하여 사용할 수 있다.(예를 들어, JSP에서 사이트 정보를 표시하는 데 활용 가능)
 */
public class SiteInterceptor implements HandlerInterceptor {
	@Autowired
	private SiteService siteService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 현재 요청의 servlet context에서 "siteVo"라는 이름으로 사이트 정보(`SiteVo`)를 가져온다.
		SiteVo siteVo = (SiteVo)request.getServletContext().getAttribute("siteVo");
		
		if(siteVo == null) { // 만약 사이트 정보가 없다면
			siteVo = siteService.getSite(); // siteService를 통해 가져와서
			request.getServletContext().setAttribute("siteVo", siteVo); // servlet context에 저장
		}
		
		return true;
	}
}
