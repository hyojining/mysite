package com.poscodx.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

@Controller
public class MainController {
	@Autowired
	private SiteService siteService;	
	
	@RequestMapping("/")
	public String index(Model model) {
		SiteVo vo = siteService.getSite(); // 사이트 정보 가져오기
		
		model.addAttribute("siteVo", vo); // 사이트 정보를 모델에 추가하여 뷰에 전달
		return "main/index";
	}
	
}