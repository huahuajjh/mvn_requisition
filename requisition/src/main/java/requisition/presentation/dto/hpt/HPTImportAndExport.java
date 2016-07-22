package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

import com.excel.util.annotation.InputColAnnotation;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;

/**
 * 导入导出购房券信息model
 * 
 * @author jjh
 * @time 2016-21-05
 * 
 */
public class HPTImportAndExport {
	/** 序列 */
	@InputColAnnotation(colCoord = 0)
	private String num;

	/** 项目名称 */
	@InputColAnnotation(colCoord = 1)
	private String proName;

	/** 姓名 */
	@InputColAnnotation(colCoord = 2)
	private String name;

	/** 与户主关系 */
	@InputColAnnotation(colCoord = 3)
	private String relationship;

	/** 身份证 */
	@InputColAnnotation(colCoord = 4,required = true,requiredErrorMsg = "[身份证]不可为空")
	private String idNumber;

	/** 人口 */
	@InputColAnnotation(colCoord = 5,converErrorMsg = "类型错误，[人口]是整数")
	private int fmlNumber;

	/** 券号 */
	@InputColAnnotation(colCoord = 6,only=true,required = true,onlyErrorMsg = "[券号]只能唯一",requiredErrorMsg = "[券号]不可为空")
	private String ticketNumber;

	/** 金额 */
	@InputColAnnotation(colCoord = 7,required = true,converErrorMsg="类型错误，[补贴金额]是小数类型",requiredErrorMsg = "[金额]不可为空")
	private float money;

	/**所有者id*/
	private UUID fmlItemId;
	
	/**创建人ID*/
	private String createUId;
	/**创建时间*/
	private Date createDate;

	public HousePuraseTicket toHPT() {
		HousePuraseTicket hpt = new HousePuraseTicket();
		hpt.setBonus(money);
		hpt.setFmlItemId(fmlItemId);
		hpt.setIdNumber(idNumber);
		hpt.setMakeDate(new Date());
		hpt.setName(name);
		hpt.setTicketNumber(ticketNumber);
		hpt.setCreateDate(createDate);
		hpt.setCreateUId(createUId);
		return hpt;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public int getFmlNumber() {
		return fmlNumber;
	}

	public void setFmlNumber(int fmlNumber) {
		this.fmlNumber = fmlNumber;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public UUID getFmlItemId() {
		return fmlItemId;
	}

	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}

	public String getCreateUId() {
		return createUId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateUId(String createUId) {
		this.createUId = createUId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
