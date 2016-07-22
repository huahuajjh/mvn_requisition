package com.tq.requisition.presentation.dto.project;

import java.util.Date;
import java.util.UUID;

public class AnnouncementDto {
	/*private fields*/
	/**文号*/
	private String number;
	/**公告文件路径*/
	private String docPath;
	/**公告日期*/
	private Date date;
	/**公告名称*/
	private String name;
	/**项目id*/
	private UUID proId;
	/**公告序列*/
	private int sequence;
	/**公告id*/
	private UUID id;
	
	/*getters and setters*/
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getProId() {
		return proId;
	}

	public void setProId(UUID proId) {
		this.proId = proId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	/*override*/
	@Override
	public String toString() {
		return "AnnouncementDto [number=" + number + ", docPath=" + docPath
				+ ", date=" + date + ", name=" + name + ", proId=" + proId
				+ ", sequence=" + sequence + "]";
	}

	/*constructors*/
	public AnnouncementDto(){}
	public AnnouncementDto(UUID id,String number, String docPath, Date date,
			String name, UUID proId, int sequence) {
		super();
		this.number = number;
		this.docPath = docPath;
		this.date = date;
		this.name = name;
		this.proId = proId;
		this.sequence = sequence;
		this.id = id;
	}
	
	/*public methods*/
	public static AnnouncementDto obtain(UUID id,String number, String docPath, Date date,
			String name, UUID proId, int sequence) {
		return new AnnouncementDto(id,number, docPath, date, name, proId, sequence);
	}
	
}
