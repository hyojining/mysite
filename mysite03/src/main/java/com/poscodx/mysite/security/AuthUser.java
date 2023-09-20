package com.poscodx.mysite.security;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) // 런타임 시에도 annotation 정보가 유지
@Target(PARAMETER) // 메서드의 파라미터에만 적용 -> 현재 로그인된 사용자 정보를 파라미터로 전달하기 위한 용도
public @interface AuthUser {
}