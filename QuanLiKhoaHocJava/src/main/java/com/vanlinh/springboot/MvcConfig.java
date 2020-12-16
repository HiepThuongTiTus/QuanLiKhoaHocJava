package com.vanlinh.springboot;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	
	@Override 
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login_success").setViewName("login_success");
		registry.addViewController("/login_error").setViewName("login_error");
		registry.addViewController("/logout_success").setViewName("logout_success");
		registry.addViewController("/403").setViewName("403");
	}
	 
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path logoKhoaHocUploadDir = Paths.get("./logo-khoahoc/");
		String logoKhoaHocUploadPath = logoKhoaHocUploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/logo-khoahoc/**").addResourceLocations("file:/"+logoKhoaHocUploadPath+"/");
	}
	
}
