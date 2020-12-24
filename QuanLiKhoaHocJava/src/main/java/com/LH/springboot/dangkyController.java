package com.LH.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dangky")
public class dangkyController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@GetMapping
	public String Dangky() {
		return "sign-up";
	}

	@PostMapping
	public String DangKy(@RequestParam String tendangnhap, @RequestParam String matkhau, @RequestParam String nhaplaimatkhau, ModelMap modelMap) {
		
		if(matkhau.equals(nhaplaimatkhau)) {
			User user = new User();
			user.setUsername(tendangnhap);
			user.setPassword(matkhau);
			userDetailsServiceImpl.Dangky(user);
		}
		return "login";
	
		
	}
}