package com.java.learning.mvcdatabase.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.learning.mvcdatabase.dao.Offer;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String showHome(){		
		return "home";
	}		
}
