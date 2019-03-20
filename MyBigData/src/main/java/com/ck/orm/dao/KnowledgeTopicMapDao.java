package com.ck.orm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.KnowledgeTopicMapPo;
import com.ck.orm.entity.KnowledgeTopicPo;
@Repository
public interface KnowledgeTopicMapDao extends BaseDao<KnowledgeTopicMapPo>{
	
	@Query("select p from KnowledgeTopicMapPo m,KnowledgeTopicPo p where p.id = m.topicId and m.knowledgeId=:knowledgeId")
	List<KnowledgeTopicPo> findTopicsByKnowledgeId(@Param("knowledgeId") String knowledgeId);
}
