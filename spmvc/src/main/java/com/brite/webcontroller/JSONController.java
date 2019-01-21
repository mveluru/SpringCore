package com.brite.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.brite.model.Shop;

@Controller
@RequestMapping("/shop")
public class JSONController {
	
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String  getshopInJson(@PathVariable String name,ModelMap model){
		
		model.addAttribute("Name", name);
		
		return "display";
	}

}
