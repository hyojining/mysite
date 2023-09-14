package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gNoParam = request.getParameter("g_no");
        String oNoParam = request.getParameter("o_no");
        String depthParam = request.getParameter("depth");

        Long g_no = Long.parseLong(gNoParam);
        Long o_no = Long.parseLong(oNoParam);
        Long depth = Long.parseLong(depthParam);

        request.setAttribute("g_no", g_no);
        request.setAttribute("o_no", o_no);
        request.setAttribute("depth", depth);
        
		WebUtil.forward("board/write", request, response);
	}

}
