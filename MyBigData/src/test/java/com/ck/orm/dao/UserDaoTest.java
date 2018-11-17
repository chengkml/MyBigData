package com.ck.orm.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.UserPo;

public class UserDaoTest extends BaseDaoTest<UserPo, UserDao>{

	@Override
	public void testSave() {
		UserPo po = new UserPo();
		po.setId("1");
		po.setName("ck");
		dao.save(po);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserByName() {
		testSave();
		assertNotNull(dao.getUserByName("ck"));
	}

}
