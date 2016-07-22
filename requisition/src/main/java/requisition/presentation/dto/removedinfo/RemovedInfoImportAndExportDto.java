package com.tq.requisition.presentation.dto.removedinfo;

import java.util.Date;

import com.excel.util.annotation.InputColAnnotation;
import com.excel.util.annotation.OutputColAnnotation;
import com.tq.requisition.domain.model.removedInfo.RemovedInfo;

public class RemovedInfoImportAndExportDto {
	/**迁户人姓名*/
	@InputColAnnotation(colCoord = 0, required = true,requiredErrorMsg="[迁户人姓名]不可为空")
	@OutputColAnnotation(colCoord = 0)
	private String name;

	/**拆迁户适用政策*/
	@InputColAnnotation(colCoord = 1)
	@OutputColAnnotation(colCoord = 1)
	private String fitPolicy;
	
	/**迁户人身份证*/
	@InputColAnnotation(colCoord = 2, required = true,requiredErrorMsg="[迁户人身份证]不可为空")
	@OutputColAnnotation(colCoord = 2)
	private String idNumber;
	
	/**出生日期*/
	@InputColAnnotation(colCoord = 3, required = true,requiredErrorMsg="[出生日期]不可为空",converErrorMsg = "类型错误，[出生日期]是时间类型")
	@OutputColAnnotation(colCoord = 3)
	private Date birthDay;
	
	/**拆迁日期*/
	@InputColAnnotation(colCoord = 4, required = true,requiredErrorMsg="[拆迁日期]不可为空",converErrorMsg = "类型错误，[拆迁日期]是时间类型")
	@OutputColAnnotation(colCoord = 4)
	private Date removeDate;
	
	@InputColAnnotation(colCoord = 5, required = false)
	@OutputColAnnotation(colCoord = 5)
	/**迁户人地址*/
	private String address;
	
	/**创建人ID*/
	private String createId;
	/**创建时间*/
	private Date createDate;
	
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public RemovedInfo toRemovedInfo() {
		RemovedInfo info = new RemovedInfo();
		info.setAddress(address);
		info.setBirthDay(birthDay);
		info.setIdNumber(idNumber);
		info.setRemoveDate(removeDate);
		info.setName(name);
		info.setPolicy(fitPolicy);
		info.setCreateDate(createDate);
		info.setCreateId(createId);
		return info;
	}
}
