package com.ck.domain;

import java.util.List;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseModel{
	
	public static final int ACTIVE_PLANS = 7;
	
	private String name;
	
	private String type;
	
	private Integer age;
	
	private String job;
	
	private List<DailyPlan> plans;
	
	private List<KnowledgePoint> points;
	
}
