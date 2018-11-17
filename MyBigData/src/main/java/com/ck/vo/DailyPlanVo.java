package com.ck.vo;

import java.util.List;

import com.ck.vo.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DailyPlanVo extends BaseVo{
	
	private static final long serialVersionUID = 1L;

	private Integer scores;

	private String descr;

	private List<DailyPlanItemVo> planItems;
}
