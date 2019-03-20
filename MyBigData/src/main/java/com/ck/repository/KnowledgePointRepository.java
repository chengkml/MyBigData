package com.ck.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ck.domain.KnowledgePoint;
import com.ck.orm.dao.KnowledgePointDao;
import com.ck.orm.dao.KnowledgeTopicMapDao;
import com.ck.orm.entity.KnowledgePointPo;
import com.ck.orm.entity.KnowledgeTopicPo;
import com.ck.orm.mapper.inf.KnowledgePointMapper;
import com.ck.repository.base.BaseRepository;

@Repository
public class KnowledgePointRepository extends BaseRepository<KnowledgePointDao, KnowledgePointPo, KnowledgePoint>{

	@Autowired
	private KnowledgeTopicMapDao ktDao;
	
	@Autowired
	private KnowledgeTopicRepository topicRepo;
	
	@Autowired
	private KnowledgePointMapper mDao;
	
	@Override
	public KnowledgePoint poToModel(KnowledgePointPo po) {
		KnowledgePoint m = new KnowledgePoint();
		BeanUtils.copyProperties(po, m);
		List<KnowledgeTopicPo> topics = ktDao.findTopicsByKnowledgeId(po.getId());
		m.setTopics(topicRepo.posToModels(topics));
		return m;
	}

	@Override
	public KnowledgePointPo modelToPo(KnowledgePoint model) {
		KnowledgePointPo po = new KnowledgePointPo();
		BeanUtils.copyProperties(po, model);
		return po;
	}
	
	public List<KnowledgePoint> findKnowledgeByConds(Date beginTime, Date endTime, String keyWord, String name) {
		return super.posToModels(mDao.findKnowledgePointByConds(beginTime, endTime, keyWord, name));
	}
}
