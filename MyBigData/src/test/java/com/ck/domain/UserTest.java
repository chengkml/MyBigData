package com.ck.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.domain.base.BaseModelTest;
import com.ck.repository.DailyPlanItemRepository;
import com.ck.repository.DailyPlanRepository;
import com.ck.repository.UserRepository;

public class UserTest extends BaseModelTest{
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private DailyPlanRepository planRepo;
	
	@Autowired
	private DailyPlanItemRepository planItemRepo;
	
	private void addDailyPlans() {
		addUser();
		DailyPlan plan = new DailyPlan();
		plan.setId("1");
		plan.setCreateBy("ck");
		plan.setCreateDate(new Date());
		planRepo.saveModel(plan);
	}

	private void addUser() {
		User user = new User();
		user.setId("1");
		user.setName("ck");
		user.setCreateDate(new Date());
		repo.saveModel(user);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlansByPage() {
		addDailyPlans();
		User user = repo.getUserByName("ck");
		assertEquals(user.getPlansByPage(1, 5).getTotalElements(), 1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlansByRange() {
		addDailyPlans();
		User user = repo.getUserByName("ck");
		assertEquals(user.getPlansByRange(DateUtils.addDays(new Date(), -15), new Date(), 1, 5).getTotalElements(), 1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlanItemsByPage() {
		addDailyPlanItems();
		User user = repo.getUserByName("ck");
		assertEquals(user.getPlanItemsByPage(1, 5).getTotalElements(), 1);
	}
	
	private void addDailyPlanItems() {
		addUser();
		DailyPlanItem item = new DailyPlanItem();
		item.setId("1");
		item.setCreateBy("ck");
		item.setCreateDate(new Date());
		planItemRepo.saveModel(item);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetPlanItemsByRange() {
		addDailyPlanItems();
		User user = repo.getUserByName("ck");
		assertTrue(user.getPlanItemsByRange(DateUtils.addDays(new Date(), -15), new Date(), 1, 5).getTotalElements()>0);
	}
}
