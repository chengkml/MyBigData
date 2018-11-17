package com.ck.orm.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ck.orm.dao.base.BaseDao;
import com.ck.orm.entity.SequencePo;
@Repository
public interface SequenceDao extends BaseDao<SequencePo>{
	@Query("select p from SequencePo p where p.type=:seqType and p.count = (select max(p.count) from SequencePo p where p.type=:seqType)")
	public SequencePo getLatestSeqByType(@Param("seqType") String seqType);
}
