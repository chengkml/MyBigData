package com.ck.orm.dao;

import com.ck.orm.dao.base.BaseDaoTest;
import com.ck.orm.entity.KnowledgeTopicPo;

public class KnowledgeTopicDaoTest extends BaseDaoTest<KnowledgeTopicPo, KnowledgeTopicDao>{

	@Override
	public void testSave() {
		KnowledgeTopicPo po = new KnowledgeTopicPo();
		po.setId("1");
		dao.save(po);
	}

	@Override
	public void testDelete() {
		testSave();
		dao.delete(dao.findOne("1"));
	}

}
