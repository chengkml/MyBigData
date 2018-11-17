package com.ck.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ck.domain.Sequence;
import com.ck.orm.dao.SequenceDao;
import com.ck.orm.entity.SequencePo;
import com.ck.repository.SequenceRepository;
import com.ck.service.base.BaseService;
import com.ck.vo.SeqVo;
/**
 * @Title:索引服务
 * @author:Chengk
 * @Date:2018年11月15日
 * @Version:1.0
 */
@Service
public class SequenceService extends BaseService<SequenceDao, SequenceRepository, SequencePo, Sequence, SeqVo>{
	
	/**
	 * 生成唯一标识
	 * @param type
	 * @return
	 */
	public synchronized String getSeq(String userId, String type) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Sequence seq = repo.getLastestSeq(type);
		String res = null;
		if(null == seq) {
			seq = new Sequence();
			seq.setId(uuid);
			seq.setCount(Sequence.SEQ_START);
			seq.setType(type);
			seq.setCreateBy(userId);
			seq.setCreateDate(new Date());
			seq.setLastModifiedBy(userId);
			seq.setLastModifiedDate(new Date());
		}
		res = seq.geneSeq(userId);
		seq.setSequenceVal(res);
		repo.saveModel(seq);
		return res;
	}

	@Override
	public SeqVo modelToVo(Sequence model) {
		return null;
	}

	@Override
	public Sequence voToModel(SeqVo vo) {
		return null;
	}
}
