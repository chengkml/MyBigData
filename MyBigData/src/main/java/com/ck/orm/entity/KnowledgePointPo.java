package com.ck.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="knowledge_point")
@EqualsAndHashCode(callSuper=false)
public class KnowledgePointPo extends BasePo{
	
	@Column(name="topic_id")
	private Integer topicId;
	
	@Column(name="point_content", length=10240)
	private String content;
}
