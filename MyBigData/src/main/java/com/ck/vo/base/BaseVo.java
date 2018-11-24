package com.ck.vo.base;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date createDate;
	
	private String createBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date lastModifiedDate;
	
	private String lastModifiedBy;

}
