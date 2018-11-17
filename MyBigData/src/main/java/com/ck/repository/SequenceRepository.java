package com.ck.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ck.domain.Sequence;
import com.ck.orm.dao.SequenceDao;
import com.ck.orm.entity.SequencePo;
import com.ck.repository.base.BaseRepository;
/**
 * @Title:索引Repo
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Repository
public class SequenceRepository extends BaseRepository<SequenceDao, SequencePo, Sequence>{

	@Override
	public Sequence poToModel(SequencePo po) {
		Sequence seq = new Sequence();
		if(null!=po) {
			BeanUtils.copyProperties(po, seq);
		}
		return seq;
	}

	@Override
	public SequencePo modelToPo(Sequence model) {
		SequencePo po = new SequencePo();
		if(null!=model) {
			BeanUtils.copyProperties(model, po);
		}
		return po;
	}
	
	/**
	 * 获取最新的索引
	 * @param type
	 * @return
	 */
	public Sequence getLastestSeq(String type) {
		SequencePo po = dao.getLatestSeqByType(type);
		if(null!=po) {
			return poToModel(po);
		}
		return null;
	}

}
