package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String noParam = request.getParameter("no");

        if (noParam != null && !noParam.isEmpty()) {
            Long no = Long.parseLong(noParam);
			BoardVo vo = new BoardDao().findByNo(no);
			request.setAttribute("vo", vo);
        }
		
		WebUtil.forward("board/write", request, response);
	}

}
