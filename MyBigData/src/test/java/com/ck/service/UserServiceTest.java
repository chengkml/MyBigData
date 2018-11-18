package com.ck.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.User;
import com.ck.orm.dao.DailyPlanDao;
import com.ck.orm.dao.DailyPlanItemDao;
import com.ck.orm.dao.UserDao;
import com.ck.orm.entity.DailyPlanItemPo;
import com.ck.orm.entity.DailyPlanPo;
import com.ck.orm.entity.UserPo;
import com.ck.repository.UserRepository;
import com.ck.service.base.BaseServiceTest;
import com.ck.vo.UserVo;

public class UserServiceTest extends BaseServiceTest<UserService,UserRepository, UserDao, UserPo, User, UserVo>{
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private DailyPlanDao planDao;
	
	@Autowired
	private DailyPlanItemDao planItemDao;
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserById() {
		addUser();
		assertNotNull(serv.getUserById("ck"));
	}

	private void addUser() {
		UserPo po = new UserPo();
		po.setId("ck");
		po.setName("ck");
		dao.save(po);
	}
	
	public void addPlan() {
		DailyPlanPo plan = new DailyPlanPo();
		plan.setId("1");
		plan.setCreateBy("ck");
		plan.setCreateDate(DateUtils.addDays(new Date(), -1));
		planDao.saveAndFlush(plan);
	}
	
	public void addPlanItem() {
		DailyPlanItemPo po = new DailyPlanItemPo();
		po.setId("1");
		po.setPlanId("1");
		po.setCreateBy("ck");
		po.setCreateDate(DateUtils.addDays(new Date(), -1));
		planItemDao.save(po);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserByName() {
		addUser();
		assertNotNull(serv.getUserByName("ck"));
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPlansByRange() {
		addUser();
		addPlan();
		Date start = DateUtils.addDays(new Date(), -2);
		Date end = new Date();
		assertEquals(serv.getPlansByRange("ck", start, end, 0, 5).getTotalElements(),1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPlansByPage() {
		addUser();
		addPlan();
		assertEquals(serv.getPlansByPage("ck", 0, 5).getTotalElements(),1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPlanItemsByRange() {
		addUser();
		addPlanItem();
		Date start = DateUtils.addDays(new Date(), -2);
		Date end = new Date();
		assertEquals(serv.getPlanItemsByRange("ck", start, end, 0, 5).getTotalElements(),1);
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPlanItemsByPage() {
		addUser();
		addPlanItem();
		assertEquals(serv.getPlanItemsByPage("ck", 0, 5).getTotalElements(),1);
	}
}
