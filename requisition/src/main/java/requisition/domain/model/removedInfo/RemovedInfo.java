package com.tq.requisition.domain.model.removedInfo;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;
import com.tq.requisition.exception.DomainException;

/**
 * 已迁户聚合根
 * @author jjh
 * @time 2015-12-18 19:05
 */
public class RemovedInfo extends AggregateRoot{
	/**姓名*/
	private String name;
	/**身份证号*/
	private String idNumber;
	/**出生年月*/
	private Date birthday;
	/**地址*/
	private String address;
	/**街道信息地址*/
	private UUID streetId;
	/**街道信息地址*/
	private UUID communityId;
	/**拆迁日期*/
	private Date removeDate;
	/**是否删除*/
	private boolean del;
	/**创建人的ID*/
	private String createId;
	/**创建时间*/
	private Date createDate;
	/**适用政策*/
	private String policy;
	
	public RemovedInfo(){
		this.id = UUID.randomUUID();
		this.del = false;
	}
	
	/*getters and setters*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Date getBirthDay() {
		return birthday;
	}
	public void setBirthDay(Date birthDay) {
		this.birthday = birthDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UUID getStreetId() {
		return streetId;
	}
	public void setStreetId(UUID streetId) {
		this.streetId = streetId;
	}
	public UUID getCommunityId() {
		return communityId;
	}
	public void setCommunityId(UUID communityId) {
		this.communityId = communityId;
	}
	public Date getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}
	public boolean getDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/*public methods*/
	public void validate() throws DomainException {
		check(this.idNumber, "身份证不能为空");
		check(this.name, "姓名不能为空");
		check(this.birthday, "出生日期不能为空");
		check(this.removeDate, "拆迁日期不能为空");
	}
	
	public void modify(RemovedInfo entity) {
		this.address = entity.getAddress();
		this.birthday = entity.getBirthDay();
		this.communityId = entity.getCommunityId();
		this.streetId = entity.getStreetId();
		this.idNumber = entity.getIdNumber();
		this.name = entity.getName();
		this.removeDate = entity.getRemoveDate();
	}
}
