package com.ck.controller.base;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import lombok.Data;

@Data
public class Result {

	private boolean success = true;
	
	private Object data;
	
	private String msg;
	
	public void setMsg(String format, Object... args) {
		FormattingTuple ft = MessageFormatter.arrayFormat(format, args);
		msg = ft.getMessage();
	}
}
