package com.ck.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@Table(name="daily_plan")
@EqualsAndHashCode(callSuper=false)
public class DailyPlanPo extends BasePo{
	
	@Column(name="scores")
	private Integer scores;
	
	@Column(name="descr",length=512)
	private String descr;
}
