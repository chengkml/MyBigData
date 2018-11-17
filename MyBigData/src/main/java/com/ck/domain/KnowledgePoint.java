package com.ck.domain;

import java.util.List;

import com.ck.domain.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class KnowledgePoint extends BaseModel{
	
	private Integer topicId;
	
	private String content;
	
	private List<KnowledgeTopic> topics;
}
