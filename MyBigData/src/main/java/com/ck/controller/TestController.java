package com.ck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ck.domain.User;
import com.ck.repository.UserRepository;
import com.ck.service.UserService;
@Controller
@RequestMapping(value="/test")
public class TestController {
	@Autowired
	private UserService serv;
	
	@Autowired
	private UserRepository repo;
	
	@RequestMapping(value="/test.do")
	public String test() {
		User user  = repo.getUserByName("ck");
		System.out.println(user.getPlansByPage(1, 5));
		return "test";
	}
}
