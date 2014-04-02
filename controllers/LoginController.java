package com.java.learning.mvcdatabase.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.learning.mvcdatabase.dao.Offer;
import com.java.learning.mvcdatabase.dao.User;
import com.java.learning.mvcdatabase.service.UsersService;

@Controller
public class LoginController {
	private UsersService userService;
	
	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login") //it it the name showing in the url address.
	public String showLogin(){		
		return "login"; // it is the name of jsp file to be used.
	}	
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(){		
		return "loggedout";
	}	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model){		
		model.addAttribute("user", new User());
		return "newaccount";
	}	
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid User user, BindingResult result){		
		
		if(result.hasErrors()){
			return "newaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(userService.exists(user.getUsername())){
			result.rejectValue("username","DuplicateKey.user.username");
			return "newaccount";	
		}
		/*
		try{
			userService.create(user);			
		}catch(DuplicateKeyException e){
			result.rejectValue("username","DuplicateKey.user.username","This username already exists");
			return "newaccount";			
		}
		*/
		userService.create(user);
		return "accountcreated";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model){		
		
		List <User> users=userService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping("/denied")
	public String showDenied(){		
		return "denied";
	}	
	
}
