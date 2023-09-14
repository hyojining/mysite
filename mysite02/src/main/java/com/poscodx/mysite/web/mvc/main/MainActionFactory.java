package com.poscodx.mysite.web.mvc.main;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class MainActionFactory implements ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction(); // 컨트롤러(액션)를 생성하고 반환
	}

}