package ru.demi.springmvc.ctrls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCtrl {

	@RequestMapping("/")
	public String getIndexPage() {
		return "index";
	}
}
