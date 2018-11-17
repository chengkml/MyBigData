package com.ck.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommonException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private String code;
}
