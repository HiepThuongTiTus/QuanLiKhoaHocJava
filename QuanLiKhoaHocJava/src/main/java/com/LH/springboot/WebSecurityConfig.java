package com.LH.springboot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**","/css/**","/js/**");
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/khoahoc").permitAll() 
			.antMatchers("/admin/quanlikhoahoc/delete/**").hasAuthority("ADMIN")
			.antMatchers("/admin/quanlikhoahoc/edit/**").hasAnyAuthority("ADMIN","EDITOR")
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN","EDITOR")
			.antMatchers("/khoahoc/**").hasAnyAuthority("ADMIN","EDITOR","USER")
			.antMatchers("/admin/**").authenticated()
			.antMatchers("/admin/quanlikhoahoc/edit/**").authenticated()
			.antMatchers("/khoahoc/**").authenticated()
			.and()
			.formLogin()
				/* .permitAll() */ 
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				/* .loginProcessingUrl("/dologin") */
				/* .defaultSuccessUrl("/login_success") */
				.failureUrl("/login_error")
				/*
				 * .successHandler(new AuthenticationSuccessHandler() {
				 * 
				 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
				 * HttpServletResponse response, Authentication authentication) throws
				 * IOException, ServletException { String name = authentication.getName();
				 * System.out.println("Đăng nhập với user là: "+name);
				 * response.sendRedirect("/login_success"); } }) .failureHandler(new
				 * AuthenticationFailureHandler() {
				 * 
				 * @Override public void onAuthenticationFailure(HttpServletRequest request,
				 * HttpServletResponse response, AuthenticationException exception) throws
				 * IOException, ServletException { System.out.println("Đăng nhập thất bại!");
				 * response.sendRedirect("/login_error"); } })
				 */
			.and()
			.logout()
				.permitAll()
				.logoutUrl("/doLogout")
				.logoutSuccessUrl("/logout_success")
				/*
				 * .logoutSuccessHandler(new LogoutSuccessHandler() {
				 * 
				 * @Override public void onLogoutSuccess(HttpServletRequest request,
				 * HttpServletResponse response, Authentication authentication) throws
				 * IOException, ServletException { System.out.println("Đăng xuất thành công!");
				 * response.sendRedirect("/logout_success"); } })
				 */
				/* .and().csrf().disable() */
			.and()	
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
}
