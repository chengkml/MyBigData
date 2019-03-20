package com.ck.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 通用异常
 * @author Chengk
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public CommonException(String msg) {
		this.msg = msg;
	}
	
	public CommonException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private String code;
	
	private String msg;
}
