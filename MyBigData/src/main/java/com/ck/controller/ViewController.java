package com.ck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 视图控制器
 * @author Chengk
 */
@Controller
@RequestMapping
public class ViewController {
	
	@GetMapping(value="/planItems")
	public String myPlan() {
		return "plan_item_list";
	}
	
	@GetMapping(value="/frame")
	public String frame() {
		return "frame";
	}
	
}
