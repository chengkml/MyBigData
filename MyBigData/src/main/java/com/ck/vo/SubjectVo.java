package com.ck.vo;

import com.ck.vo.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SubjectVo extends BaseVo{
	
	private static final long serialVersionUID = 1L;
	
	private String label;
	
	private String value;
	
	private Integer order;
	
	private String color;
	
	private String ico;

}
