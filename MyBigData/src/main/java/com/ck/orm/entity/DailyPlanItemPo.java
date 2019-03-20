package com.ck.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ck.orm.entity.base.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 计划项实体
 * @author Chengk
 */
@Data
@Entity
@Table(name="daily_plan_item")
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItemPo extends BasePo{
	
	/**
	 * 计划Id
	 */
	@Column(name="plan_id",length=32)
	private String planId;
	
	/**
	 * 项目内容
	 */
	@Lob
	@Column(name="item_content")
	private String itemContent;
	
	/**
	 * 优先级
	 */
	@Column(name="priority")
	private Integer priority;
	
	/**
	 * 计划完成时间
	 */
	@Column(name="plan_time")
	private Date planTime;
	
	/**
	 * 实际完成时间
	 */
	@Column(name="finish_time")
	private Date finishTime;
	
	/**
	 * 完成类型
	 */
	@Column(name="finish_type")
	private Integer finishType;
	
	/**
	 * 完成情况描述
	 */
	@Column(name="finish_descr",length=4096)
	private String finishDescr;
	
	/**
	 * 开始时间
	 */
	@Column(name="start_time")
	private Date startTime;
	
	/**
	 * 自动提醒开关
	 */
	@Column(name="remindOn")
	private Integer remindOn;
}
