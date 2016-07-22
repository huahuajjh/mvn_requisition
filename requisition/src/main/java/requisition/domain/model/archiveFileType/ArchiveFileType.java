package com.tq.requisition.domain.model.archiveFileType;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 归档文件类型聚合根
 * @author jjh
 * @time 2015-12-18 19:15
 */
public class ArchiveFileType extends AggregateRoot{
	/**类型名称*/
	private String typeName;
	/**父类型*/
	private ArchiveFileType parentType;
}
