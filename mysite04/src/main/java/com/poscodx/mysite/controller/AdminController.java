package com.poscodx.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

@Auth(Role="ADMIN") // ADMIN 역할을 가진 사용자만 접근 가능
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ApplicationContext applicationContext; // 스프링 빈을 관리하고 어플리케이션의 설정 정보를 제공
	
	@Autowired
	private ServletContext servletContext; // 웹 전반에 걸쳐 공유되는 데이터를 저장하고 액세스하는 데 사용
	
	@Autowired
	private SiteService siteService;

	@Autowired
	private FileUploadService fileuploadService;

	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = siteService.getSite(); // 사이트 정보 가져오기
		model.addAttribute("siteVo", vo); // 사이트 정보를 모델에 추가하여 뷰에 전달
		return "admin/main";
	}

	@RequestMapping("/main/update")
	public String update(SiteVo vo, MultipartFile file) {
		String profile = fileuploadService.restore(file); // 사용자가 업로드한 파일 처리
		if(profile != null) {
			vo.setProfile(profile);
		}
		
		SiteVo site = applicationContext.getBean(SiteVo.class); // SiteVo 클래스의 새 인스턴스 생성
		
		siteService.updateSite(vo); // 사이트 정보 업데이트
		servletContext.setAttribute("siteVo", vo); // 웹 전반에 걸쳐 공유되는 siteVo 속성 업데이트
		
//		site.setTitle(vo.getTitle());
//		site.setWelcome(vo.getWelcome());
//		site.setProfile(vo.getProfile());
//		site.setDescription(vo.getDescription());
		BeanUtils.copyProperties(vo, site); // vo 객체의 속성값을 site 객체로 한 번에 copy
		
		return "redirect:/admin";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}	
}