package com.ck.domain;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.base.BaseModelTest;
import com.ck.repository.DailyPlanRepository;
import com.ck.repository.UserRepository;

public class UserTest extends BaseModelTest{
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private DailyPlanRepository planRepo;
	
	private void addData() {
		User user = new User();
		user.setId("1");
		user.setName("ck");
		user.setCreateDate(new Date());
		repo.saveModel(user);
		DailyPlan plan = new DailyPlan();
		plan.setId("1");
		plan.setCreateBy("ck");
		plan.setCreateDate(new Date());
		planRepo.saveModel(plan);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlansByPage() {
		addData();
		User user = repo.getUserByName("ck");
		assertTrue(user.getPlansByPage(1, 5).size()>0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlansByRange() {
		addData();
		User user = repo.getUserByName("ck");
		assertTrue(user.getPlansByRange(DateUtils.addDays(new Date(), -15), new Date(), 1, 5).size()>0);
	}
}
