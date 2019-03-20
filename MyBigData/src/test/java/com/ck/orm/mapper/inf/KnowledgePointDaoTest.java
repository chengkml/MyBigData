package com.ck.orm.mapper.inf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.KnowledgePointDao;
import com.ck.orm.entity.KnowledgePointPo;
import com.ck.orm.mapper.base.MapperDaoTest;
import com.ck.util.MyDateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class KnowledgePointDaoTest extends MapperDaoTest<KnowledgePointMapper,KnowledgePointPo,KnowledgePointDao>{
	
	public void initData() {
		KnowledgePointPo po = new KnowledgePointPo();
		po.setCreateDate(new Date());
		po.setId("1");
		po.setKeyWord("ck");
		po.setContent("kk");
		po.setCreateBy("ck");
		dao.saveAndFlush(po);
		po = new KnowledgePointPo();
		po.setCreateDate(new Date());
		po.setId("2");
		po.setKeyWord("ck");
		po.setContent("kk");
		po.setCreateBy("ck");
		dao.saveAndFlush(po);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindKnowledgePointByCondsJpa() {
		initData();
		assertFalse(dao.findAll().isEmpty());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindKnowledgePointByConds() {
		initData();
		assertFalse(mDao.findKnowledgePointByConds(null,null, null, "ck").isEmpty());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindKnowledgePointByConds1() {
		initData();
		assertTrue(mDao.findKnowledgePointByConds(null,null, "%kk%", "ck").size()>0);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindKnowledgePointByConds2() {
		initData();
		assertTrue(mDao.findKnowledgePointByConds(new Date(), MyDateUtils.addDays(new Date(), 1), "", "ck").size()>0);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindKnowledgePointByConds3() {
		initData();
		assertTrue(mDao.findKnowledgePointByConds(null,null, "%ck%", "ck").size()>0);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindKnowledgePointByCondsByPage() {
		initData();
		Page<?> page = PageHelper.startPage(1,1,true);
		assertEquals(1,mDao.findKnowledgePointByConds(null,null, "%ck%", "ck").size());
		System.out.println(mDao.findKnowledgePointByConds(null,null, "%ck%", "ck").get(0).getCreateBy());
		assertEquals(1, page.size());
		assertEquals(2, page.getTotal());
		System.out.println(page);
	}

}
