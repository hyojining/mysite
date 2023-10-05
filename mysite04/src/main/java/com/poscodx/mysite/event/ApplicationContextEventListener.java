package com.poscodx.mysite.event;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

/**
 * 스프링 애플리케이션 컨텍스트가 초기화되고 새로 고침될 때,
 * `SiteVo` 빈이 동적으로 생성되고, 사이트 정보가 변경된 내용으로 업데이트 된다.
 */
public class ApplicationContextEventListener {
	@Autowired
	private ApplicationContext applicationContext;
	
	@EventListener({ContextRefreshedEvent.class}) // application context가 초기화되고 새로고침될 때 호출
	public void handlerContextRefreshedEvent() {
		System.out.println("--- Context Refreshed Event Received : ---" + applicationContext);
		
		SiteService siteService = applicationContext.getBean(SiteService.class); // SiteService 빈 얻기
		SiteVo site = siteService.getSite(); // 사이트 정보 가져오기
		
		MutablePropertyValues propertyValues = new MutablePropertyValues(); // 객체의 속성값을 변경할 수 있는 컨테이너 역할
		propertyValues.add("title", site.getTitle());
		propertyValues.add("profile", site.getProfile());
		propertyValues.add("welcome", site.getWelcome());
		propertyValues.add("description", site.getDescription());
		
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition(); // 스프링 컨테이너에 등록할 빈의 정의
		beanDefinition.setBeanClass(SiteVo.class); // SiteVo 클래스를 빈으로 등록
		beanDefinition.setPropertyValues(propertyValues); // 빈의 속성값 설정
		
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory(); // 빈을 생성하고, 의존성 주입을 처리하는 역할
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry)factory; // application context에서 빈의 정의를 관리하는 역할
		registry.registerBeanDefinition("site", beanDefinition); // 스프링 컨테이너에게 "site"라는 이름으로 새로운 빈 정의를 등록
	}
	
}