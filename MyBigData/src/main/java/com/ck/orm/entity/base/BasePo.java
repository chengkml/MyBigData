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
/**
 * 实体父类
 * @author Chengk
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasePo {
	/**
	 * 唯一标识
	 */
	@Id
    @Column(name="id",length=32,nullable=false)
	private String id;
	
	/**
	 * 创建时间
	 */
	@CreatedDate
	@Column(name="create_Date")
	private Date createDate;
	
	/**
	 * 创建人
	 */
	@CreatedBy
	@Column(name="create_by",length=50)
	private String createBy;
	
	/**
	 * 最后更新时间
	 */
	@LastModifiedDate
	@Column(name="last_modified_time")
	private Date lastModifiedDate;
	
	/**
	 * 最后更新人
	 */
	@LastModifiedBy
	@Column(name="last_modified_by",length=50)
	private String lastModifiedBy;
}
