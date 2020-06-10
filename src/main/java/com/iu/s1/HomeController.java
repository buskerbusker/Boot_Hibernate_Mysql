package com.iu.s1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) throws Exception {
		return "index";
	}

	@GetMapping("/message/messageResult")
	public ModelAndView message(String result, String path) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.addObject("path", path);

		mv.setViewName("common/result");
		return mv;
	}

}
