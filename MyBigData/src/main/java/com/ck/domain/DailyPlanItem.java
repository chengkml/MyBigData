package com.ck.domain;

import java.util.Date;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItem extends BaseModel{
	
	private Integer planId;
	
	private String itemContent;
	
	private Integer priority;
	
	private Date planTime;
	
	private Date finishTime;
	
	private Short finishType;
	
	private String finishDescr;
}
