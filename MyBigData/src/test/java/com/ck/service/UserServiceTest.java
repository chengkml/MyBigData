package com.ck.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.base.BaseServiceTest;
import com.ck.vo.UserVo;

public class UserServiceTest extends BaseServiceTest<UserService,UserRepository, UserDao, UserPo, User, UserVo>{
	
	@Autowired
	private UserDao dao;
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserById() {
		addUser();
		assertNotNull(serv.getUserById("1"));
	}

	private void addUser() {
		UserPo po = new UserPo();
		po.setId("1");
		po.setName("ck");
		dao.save(po);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserByName() {
		addUser();
		assertNotNull(serv.getUserByName("ck"));
	}
}
