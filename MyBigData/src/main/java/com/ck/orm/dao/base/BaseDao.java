package com.ck.orm.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
/**
 * @Title:Dao泛型父接口
 * @author:Chengk
 * @Date:2018年11月16日
 * @Version:1.0
 */
@Repository
@NoRepositoryBean
public interface BaseDao<P> extends JpaRepository<P, String>,JpaSpecificationExecutor<P>{

}
