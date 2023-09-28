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
	private ApplicationContext applicationContext;
	
	@Autowired
	private ServletContext servletContext;
	
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
		
		SiteVo site = applicationContext.getBean(SiteVo.class);
		
		siteService.updateSite(vo); // 사이트 정보 업데이트
		servletContext.setAttribute("siteVo", vo);
		
//		site.setTitle(vo.getTitle());
//		site.setWelcome(vo.getWelcome());
//		site.setProfile(vo.getProfile());
//		site.setDescription(vo.getDescription());
		BeanUtils.copyProperties(vo, site); // 한 번에 copy
		
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