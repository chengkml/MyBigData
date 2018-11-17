package com.ck.orm.dao;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.KnowledgePointPo;

public class KnowledgePointDaoTest extends BaseDaoTest<KnowledgePointPo, KnowledgePointDao>{

	@Override
	public void testSave() {
		KnowledgePointPo po = new KnowledgePointPo();
		po.setId("1");
		dao.save(po);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}

}
