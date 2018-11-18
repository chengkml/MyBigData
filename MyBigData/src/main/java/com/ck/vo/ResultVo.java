package com.ck.vo;

import com.ck.vo.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class ResultVo extends BaseVo{
	
	private static final long serialVersionUID = 1L;

	private boolean success = true;
	
	private Object value;
	
	private String msg;
}
