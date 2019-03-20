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
	
	@Column(name="key_word", length=128)
	private String keyWord;
	
	@Column(name="point_content", length=10240)
	private String content;
}
