package com.ck.orm.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.ck.orm.entity.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="users")
@EqualsAndHashCode(callSuper=false)
public class UserPo extends BasePo{
	
	@Column(name="user_name", length=50,nullable=false)
	private String name;
	
	@Column(name="user_type", length=10)
	private String type;
	
	@Column(name="user_age")
	private Integer age;
	
	@Column(name="user_job", length=100)
	private String job;
}
