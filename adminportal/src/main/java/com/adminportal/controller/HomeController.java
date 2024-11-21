package com.adminportal.controller;

import com.adminportal.domain.Settings;
import com.adminportal.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
@RequiredArgsConstructor
@Controller
public class HomeController {

	private final SettingsService settingsService;

	@GetMapping("/")
	public String index(Model model){
		model.addAttribute("loginInUser", getAuthentication());
		model.addAttribute("formatted", settingsService.findOne((long) 1));
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		List<Settings> settingsList = settingsService.findOneActive();

		if (Objects.isNull(settingsList)) {
			return null;
		}

		model.addAttribute("settingsList", settingsList);
		return "home";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}

	public String getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}
		return authentication.getName();
	}
}
