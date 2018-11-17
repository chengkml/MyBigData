package com.ck.orm.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasePo {
	@Id
    @Column(name="id",length=32,nullable=false)
	private String id;
	
	@CreatedDate
	@Column(name="create_Date")
	private Date createDate;
	
	@CreatedBy
	@Column(name="create_by",length=50)
	private String createBy;
	
	@LastModifiedDate
	@Column(name="last_modified_time")
	private Date lastModifiedDate;
	
	@LastModifiedBy
	@Column(name="last_modified_by",length=50)
	private String lastModifiedBy;
}
