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
public class KnowledgeTopicPo extends BasePo{
	@Column(name="topic_id")
	private Integer topicId;
	
	@Column(name="topic_name", length=50)
	private String topicName;
	
	@Column(name="parent_topic_id")
	private Integer parentTopicId;
	
	@Column(name="topic_descr", length=1024)
	private String topicDescr;
}
