package com.tq.requisition.domain.model.operationRecord;

import java.util.Date;

import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 操作日志记录聚合根
 * @author jjh
 * @time 2015-12-18 23:15
 */
public class OperationRecord extends AggregateRoot{
	/**ip地址*/
	private String ipAddr;
	/**操作日期*/
	private Date oprTime;
	/**操作模块*/
	private String oprMoudle;
	/**操作动作*/
	private String oprAction;
	/**操作内容记录*/
	private String oprContent;
	/**物理地址*/
	private String macAddr;
	/**操作人*/
	private Account account;
}
