package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.PageVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		
		String currentPageParam = request.getParameter("p");
		if (currentPageParam != null && !currentPageParam.isEmpty()) {
			currentPage = Integer.parseInt(currentPageParam);
		}
		
		int postSize = 5;
		int pageSize = 5;
		int totalPost = new BoardDao().countList();
		int totalPage = (totalPost -1) / postSize + 1;
		int endPage = (int)Math.ceil((double)currentPage / pageSize) * pageSize;
		int startPage = endPage - pageSize + 1;
		int nextPage = 1;
		int prevPage = 1;
		
		if(endPage >= totalPage) {
			nextPage = -1;
		}
		if(startPage == 1) {
			prevPage = -1;
		}
		
		List<BoardVo> pageList = new BoardDao().findPageList(currentPage, postSize);
		request.setAttribute("list", pageList);
		
//		List<BoardVo> list = new BoardDao().findAll();
//		request.setAttribute("list", list);
		
		PageVo pageVo = new PageVo(postSize, pageSize, totalPost, totalPage, startPage, endPage, currentPage, nextPage, prevPage);
		request.setAttribute("pageVo", pageVo);
		
		WebUtil.forward("board/list", request, response);
	}

}
