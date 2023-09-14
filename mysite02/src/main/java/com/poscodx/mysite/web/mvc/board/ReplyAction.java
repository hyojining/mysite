package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		String g_noParam = request.getParameter("g_no");
		Long g_no = Long.parseLong(g_noParam);
		
		//String o_noParam = request.getParameter("o_no");
		//Long o_no = Long.parseLong(o_noParam);
		
		//String depthParam = request.getParameter("depth");
		//Long depth = Long.parseLong(depthParam);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(0L);
		
		vo.setG_no(g_no);
		//vo.setO_no(o_no);
		//vo.setDepth(depth + 1);
	
		vo.setUser_no(authUser.getNo());
		
		new BoardDao().updateONo(vo);
		new BoardDao().insert(vo);
		
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
