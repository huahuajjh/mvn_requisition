package com.tq.requisition.domain.model.archiveFile;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 归档文件聚合根
 * @author jjh
 * @time 2015-12-18 19:13
 */
public class ArchiveFile extends AggregateRoot{
	/**文档路径*/
	private String filePath;
	/**文档类型*/
	private String fileTypeId;
	/**文档标题*/
	private String title;
}
