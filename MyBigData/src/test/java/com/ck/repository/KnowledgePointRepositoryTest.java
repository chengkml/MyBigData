package com.ck.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ck.domain.KnowledgePoint;
import com.ck.orm.dao.KnowledgePointDao;
import com.ck.orm.dao.KnowledgeTopicMapDao;
import com.ck.orm.entity.KnowledgePointPo;
import com.ck.orm.entity.KnowledgeTopicMapPo;
import com.ck.repository.base.BaseRepositoryTest;

public class KnowledgePointRepositoryTest extends BaseRepositoryTest<KnowledgePointRepository, KnowledgePointDao, KnowledgePointPo, KnowledgePoint>{
	
	@Autowired
	private KnowledgePointDao kpDao;
	
	@Autowired
	private KnowledgeTopicMapDao kpmDao;
	
	@Test
	public void testGetById() {
		initData();
		KnowledgePoint point = repo.getById("1");
		assertEquals(2, point.getTopics().size());
	}

	private void initData() {
		KnowledgePointPo kpo = new KnowledgePointPo();
		kpo.setId("1");
		kpDao.save(kpo);
		KnowledgeTopicMapPo po = new KnowledgeTopicMapPo();
		po.setId("1");
		po.setKnowledgeId("1");
		po.setTopicId("1");
		kpmDao.save(po);
		po = new KnowledgeTopicMapPo();
		po.setId("2");
		po.setKnowledgeId("1");
		po.setTopicId("2");
		kpmDao.save(po);
	}
}
