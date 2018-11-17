package com.ck.orm.dao.base;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.base.SpringJunitTestBase;

public abstract class BaseDaoTest<P, D> extends SpringJunitTestBase{
	@Test
	@Rollback(true)
	@Transactional
	public abstract void testSave();
	
	@Test
	@Rollback(true)
	@Transactional
	public abstract void testDelete();
	
	@Autowired
	protected BaseDao<P> pDao;
	
	protected D dao;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void convertDao() {
		dao = (D)pDao;
	}
}
