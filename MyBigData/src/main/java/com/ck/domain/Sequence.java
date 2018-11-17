package com.ck.domain;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Title:索引模型
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Sequence extends BaseModel{
	
	public static final int SEQ_START = 0;
	
	private String type;
	
	private String sequenceVal;
	
	private Integer count;

	/**
	 * 生成索引
	 * @param userId
	 * @return
	 */
	public String geneSeq(String userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(userId).append("_").append(type).append("_").append(String.valueOf(++count));
		return sb.toString();
	}

}
