package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String g_noParam = request.getParameter("g_no");
//		Long g_no = Long.parseLong(g_noParam);
//		
//		String o_noParam = request.getParameter("o_no");
//		Long o_no = Long.parseLong(o_noParam);
//		
//		String depthParam = request.getParameter("depth");
//		Long depth = Long.parseLong(depthParam);
//		
//		if (g_noParam != null && !g_noParam.isEmpty()) {
//            request.setAttribute("g_no", g_no);
//        }
//		if (o_noParam != null && !o_noParam.isEmpty()) {
//            request.setAttribute("o_no", o_no);
//        }
//		if (depthParam != null && !depthParam.isEmpty()) {
//            request.setAttribute("depth", depth);
//        }
		WebUtil.forward("board/write", request, response);
	}

}
