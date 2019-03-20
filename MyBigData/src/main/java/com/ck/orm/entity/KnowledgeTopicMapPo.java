package com.ck.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.ck.orm.entity.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@Table(name="knowledge_topic")
@EqualsAndHashCode(callSuper=false)
public class KnowledgeTopicMapPo extends BasePo{
	
	@Column(name="topic_id",length=32,nullable=false)
	private String topicId;
	
	@Column(name="knowledge_id",length=32,nullable=false)
	private String knowledgeId;
}
