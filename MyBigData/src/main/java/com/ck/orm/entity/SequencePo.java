package com.ck.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 索引表
 * @author Chengk
 */
@Data
@Entity
@Table(name="sequence_records")
@EqualsAndHashCode(callSuper=false)
public class SequencePo extends BasePo{
	
	@Column(name="sequence_type", length=50)
	private String type;
	
	@Column(name="sequence_val", length=32)
	private String sequenceVal;
	
	@Column(name="sequence_count")
	private Integer count;
	
}
