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

public class WriteAction implements Action {

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
		
		Long g_no = 0L;
		Long o_no = 0L;
		Long depth = 0L;
		
		// 답글
		if(request.getParameter("no") != null) {
			BoardVo parentVo = new BoardDao().findByNo(Long.parseLong(request.getParameter("no")));
			g_no = parentVo.getG_no();
			o_no = parentVo.getO_no() + 1;
			depth = parentVo.getDepth()+1;
			
			new BoardDao().updateONo(g_no, parentVo.getO_no());
		
		}else { // 게시글
			g_no = new BoardDao().findMaxGroupNo() + 1;
			o_no = 1L;
			depth = 1L;
		}
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(0L);
		vo.setG_no(g_no);
		vo.setO_no(o_no);
		vo.setDepth(depth);
		vo.setUser_no(authUser.getNo());
		
		new BoardDao().insert(vo);
		
		response.sendRedirect(request.getContextPath() + "/board");
	}
}
