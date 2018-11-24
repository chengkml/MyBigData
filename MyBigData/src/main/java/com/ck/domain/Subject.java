package com.ck.domain;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Subject extends BaseModel{
	private String label;
	
	private String value;
	
	private Integer order;
	
	private String color;
	
	private String ico;
}
