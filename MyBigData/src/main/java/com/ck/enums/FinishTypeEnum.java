package com.ck.enums;

public enum FinishTypeEnum {
	
	INITIAL(0,"待启动"),RUNNING(1,"执行中"),FINISH(2,"已完成"),ABANDON(3,"已废弃");
	
	private FinishTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private int value;
	
	private String label;
}
