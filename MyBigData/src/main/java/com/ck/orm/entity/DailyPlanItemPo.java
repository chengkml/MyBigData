package com.ck.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="daily_plan_item")
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItemPo extends BasePo{
	
	@Column(name="plan_id",length=32)
	private String planId;
	
	@Column(name="item_content",length=1024)
	private String itemContent;
	
	@Column(name="priority")
	private Integer priority;
	
	@Column(name="plan_time")
	private Date planTime;
	
	@Column(name="finish_time")
	private Date finishTime;
	
	@Column(name="finish_type")
	private Integer finishType;
	
	@Column(name="finish_descr",length=4096)
	private String finishDescr;
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="remindOn")
	private Integer remindOn;
}
