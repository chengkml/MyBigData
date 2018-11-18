package com.ck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ck.controller.base.ControllerBase;

@Controller
@RequestMapping
public class RouterController extends ControllerBase{
	
	@RequestMapping(value="/myPlan",method=RequestMethod.GET)
	public String myPlan() {
		return "myPlan";
	}
	
	@RequestMapping(value="/frame",method=RequestMethod.GET)
	public String frame() {
		return "frame";
	}
	
}