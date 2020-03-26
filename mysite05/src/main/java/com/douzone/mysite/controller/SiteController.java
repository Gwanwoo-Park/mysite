package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping(value = "admin/main/update", method = RequestMethod.POST)
	public String update(
		@RequestParam(value = "title", required = true, defaultValue = "MySite") String title,
		@RequestParam(value = "welcomeMessage", required = true, defaultValue = "환영 기본") String welcomeMessage,
		@RequestParam(value = "profile", required = true, defaultValue = "/images/202021635030414.jpg") String profile,
		@RequestParam(value = "description", required = true, defaultValue = "설명 기본") String description,
		@RequestParam(value = "file") MultipartFile multipartFile,
		Model model) {
		
		SiteVo vo = new SiteVo();
		
		if(multipartFile.isEmpty()) {
			System.out.println(profile);
			SiteVo newSiteVo= new SiteVo();
			newSiteVo= siteService.find();
			vo.setProfile(newSiteVo.getProfile());
		} else {
			String url = siteService.restore(multipartFile);
			model.addAttribute("url", url);
			vo.setProfile(url);
		}
		
		vo.setTitle(title);
		vo.setWelcomeMessage(welcomeMessage);
		vo.setDescription(description);
		
		vo = siteService.update(vo);
		
		model.addAttribute("vo", vo);
		
		return "redirect:/";
	}
}