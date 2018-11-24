package com.ck.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@Table(name="daily_plan",uniqueConstraints = {@UniqueConstraint(columnNames={"title"})})
@EqualsAndHashCode(callSuper=false)
public class DailyPlanPo extends BasePo{
	
	@Column(name="title")
	private String title;
	
	@Column(name="scores")
	private Integer scores;
	
	@Column(name="descr",length=512)
	private String descr;
}
