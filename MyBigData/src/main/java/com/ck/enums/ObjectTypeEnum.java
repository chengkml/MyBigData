package com.ck.enums;
/**
 * @Title:对象类型枚举类
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
public enum ObjectTypeEnum {
	PLAN_ITEM("planItem"),PLAN("plan");
	
	private ObjectTypeEnum(String type) {
		this.type = type;
	}
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
