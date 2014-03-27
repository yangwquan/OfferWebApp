package com.java.learning.mvcdatabase.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.learning.mvcdatabase.dao.Offer;
import com.java.learning.mvcdatabase.service.OfferService;


@Controller
public class OffersController {		
	/*public String Showhome(HttpSession session){		
		session.setAttribute("name", "Boris");
		return "home";
	}*/
	
	/*@RequestMapping("/")
	public ModelAndView showHome(){		
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model =mv.getModel();
		model.put("name", "River");
		return mv;
	}*/
	
	private OfferService offerService;	
	
	@Autowired
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}

	@RequestMapping("/offers")
	public String showOffers(Model model){		
		List<Offer> offers= offerService.getCurrent();
		model.addAttribute("offers", offers);
		return "offers";
	}
	
	@RequestMapping("/createoffer")
	public String creatOffer(Model model){		
		
		model.addAttribute("offer", new Offer());
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result){		
		
		if(result.hasErrors()){
			/*System.out.println("Form does not validate.");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error:errors){
				System.out.println(error.getDefaultMessage());
			*/
			return "createoffer";
		}
		offerService.create(offer);
		return "offercreated";
	}
}
