package com.ck.enums;

import com.ck.annotation.Enums;

/**
 * 完成类型枚举
 * @author Chengk
 */
@Enums("finishType")
public enum FinishTypeEnum implements AbstractEnum{
	
	INITIAL("0","待启动"),RUNNING("1","执行中"),FINISH("2","已完成"),ABANDON("3","已废弃");
	
	private FinishTypeEnum(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	private String code;
	
	private String label;
}
