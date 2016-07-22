package com.tq.requisition.domain.service.idomainservice;

import java.util.List;

import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.exception.DomainException;

/**
 * 社保领域服务接口
 * @author jjh
 * @time 2015-01-04 17:30
 */
public interface ISSService {
	/**
	 * 新增社保信息
	 * @param ss
	 * 		待新增的社保信息实体
	 * @return
	 * 		返回新增消息
	 */
	void addSSInfo(SocialsecurityInfo ss)  throws DomainException ;
	
	/**
	 * 批量新增社保信息
	 * @param list
	 * 		待新增的社保信息集合
	 * @return
	 * 		返回新增后的消息
	 */
	void addSSBatch(List<SocialsecurityInfo> list)  throws DomainException ;
	
}
