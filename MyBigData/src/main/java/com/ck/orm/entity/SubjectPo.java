package com.ck.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="subject")
@EqualsAndHashCode(callSuper=false)
public class SubjectPo extends BasePo{
	
	@Column(name="subject_label",length=50)
	private String label;
	
	@Column(name="subject_value",length=50)
	private String value;
	
	@Column(name="subject_type",length=50)
	private String type;
	
	@Column(name="subject_order")
	private Integer order;
	
	@Column(name="ico_color")
	private String color;
	
	@Column(name="ico_name")
	private String ico;
}
