package com.ck.vo;

import java.util.Date;

import com.ck.vo.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItemVo extends BaseVo{
	
	private static final long serialVersionUID = 1L;

	private int planId;

	private String itemContent;

	private int priority;

	private Date planTime;

	private Date finishTime;

	private short finishType;

	private String finishDescr;
}
