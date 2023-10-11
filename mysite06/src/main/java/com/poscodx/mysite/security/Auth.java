package com.poscodx.mysite.security;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE, METHOD}) // 클래스와 메서드에 적용
public @interface Auth {
	// public String value() default "";
	public String Role() default "USER"; // 사용자 역할을 나타내는 속성
	public boolean test() default false;
}