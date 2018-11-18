package com.ck.vo;

import java.util.Date;

import com.ck.vo.base.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlanItemVo extends BaseVo{
	
	private static final long serialVersionUID = 1L;

	private Integer planId;

	private String itemContent;

	private Integer priority;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date planTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date finishTime;

	private Short finishType;

	private String finishDescr;
}
