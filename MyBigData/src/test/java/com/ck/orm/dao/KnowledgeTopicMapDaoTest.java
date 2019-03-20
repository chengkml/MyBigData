package com.ck.orm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.KnowledgeTopicMapPo;

public class KnowledgeTopicMapDaoTest extends BaseDaoTest<KnowledgeTopicMapPo, KnowledgeTopicMapDao>{

	@Override
	public void testSave() {
		KnowledgeTopicMapPo po = new KnowledgeTopicMapPo();
		po.setId("1");
		po.setKnowledgeId("1");
		po.setTopicId("1");
		dao.save(po);
		po = new KnowledgeTopicMapPo();
		po.setId("2");
		po.setKnowledgeId("1");
		po.setTopicId("2");
		dao.save(po);
	}

	@Override
	public void testDelete() {
		testSave();
		assertNotNull(dao.getOne("1"));
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void getTopicsByKnowledgeId() {
		testSave();
		assertEquals(2, dao.findTopicsByKnowledgeId("1").size());
	}

}
