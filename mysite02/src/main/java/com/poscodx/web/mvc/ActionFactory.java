package com.poscodx.web.mvc;

public interface ActionFactory {
	// 요청에 따라 액션 객체를 동적으로 생성하고 반환
	Action getAction(String actionName);
}
