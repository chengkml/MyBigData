package com.ck.domain;

import java.util.Date;

import com.ck.domain.base.BaseModel;
import com.ck.enums.FinishTypeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItem extends BaseModel{
	
	private String planId;
	
	private String itemContent;
	
	private Integer priority;
	
	private Date planTime;
	
	private Date finishTime;
	
	private Integer finishType;
	
	private String finishDescr;
	
	private Date startTime;
	
	private Integer remindOn;
	
	public boolean checkFinished() {
		return FinishTypeEnum.FINISH.getValue()==finishType;
	}
}
