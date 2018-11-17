package com.ck.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.User;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.UserPo;
import com.ck.repository.base.BaseRepositoryTest;

public class UserRepositoryTest extends BaseRepositoryTest<UserRepository, UserDao, UserPo, User>{
	@Autowired
	private UserDao dao;
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserByName() {
		UserPo po = new UserPo();
		po.setId("1");
		po.setName("ck");
		dao.save(po);
		assertNotNull(repo.getUserByName("ck"));
	}
}
