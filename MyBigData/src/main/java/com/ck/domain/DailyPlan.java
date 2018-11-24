package com.ck.domain;

import java.util.List;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlan extends BaseModel{
	
	private String title;
	
	private Integer scores;
	
	private String descr;
	
	private List<DailyPlanItem> planItems;
}
