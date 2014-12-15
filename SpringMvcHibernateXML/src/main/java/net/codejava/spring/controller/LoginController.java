package net.codejava.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	String leLogin = "toto";
	String lePass = "pass";
	
	@RequestMapping(value="/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping(value="/login-check")
	public String login_check(@RequestParam("loginSaisi") String loginSaisi, @RequestParam("passSaisi") String passSaisi, HttpServletRequest request) {
		if(loginSaisi.equals(leLogin) && passSaisi.equals(lePass)){
			request.getSession().setAttribute("logged", "true");
			return "redirect:/admin/";
		}
		else {
			return "redirect:/login/";
		}
		
		

	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
	

}
