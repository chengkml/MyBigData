package com.ck.domain.base;

import java.util.Date;

import lombok.Data;
@Data
public class BaseModel {
	
	private String id;
	
	private Date createDate;
	
	private String createBy;
	
	private Date lastModifiedDate;
	
	private String lastModifiedBy;
	
}
