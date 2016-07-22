package com.tq.requisition.domain.model.project;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.Entity;

/**
 * 公告实体
 * @author jjh
 * @time 2015-12-14 12:52
 */
public class Announcement extends Entity{
	/**文号*/
	private String number;
	/**公告文件路径*/
	private String docPath;
	/**公告日期*/
	private Date date;
	/**公告序列-1，2，3*/
	private int sequence;
	/**公告序列-项目id*/
	private UUID proId;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	
	/*constructors*/
	public Announcement(){}
	public Announcement(UUID id,String number, String docPath, Date date, int sequence,
			UUID proId) {
		super();
		this.number = number;
		this.docPath = docPath;
		this.date = date;
		this.sequence = sequence;
		this.proId = proId;
		this.id = id;
	}
	
	/*public methods*/
	public static Announcement obtain(String number, String docPath, Date date, int sequence,
			UUID proId) {
		return new Announcement(UUID.randomUUID(),number, docPath, date, sequence, proId);
	}

	/**
	 * 编辑
	 * @param model
	 * 		公告model
	 */
	public void modify(Announcement model) {
		this.date = model.getDate();
		this.docPath = model.getDocPath();
		this.number = model.getNumber();
		this.sequence = model.getSequence();
	}
	
	/**
	 * 验证
	 */
	public void validate() {
		
	}
}
