package com.ck.domain;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class KnowledgeTopic extends BaseModel{
	
	private Integer topicId;
	
	private String topicName;
	
	private Integer parentTopicId;
	
	private String topicDescr;
}
