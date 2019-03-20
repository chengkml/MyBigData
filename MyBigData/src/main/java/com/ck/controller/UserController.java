package com.ck.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.controller.base.BaseController;
import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.UserService;
/**
 * 用户控制器
 * @author Chengk
 */
@RestController
@RequestMapping(value="/user/")
public class UserController extends BaseController<UserPo,User,UserDao,UserRepository,UserService>{
	
}
