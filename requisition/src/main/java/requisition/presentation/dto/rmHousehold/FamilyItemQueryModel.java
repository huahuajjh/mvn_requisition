package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.UUID;

import com.tq.requisition.infrastructure.utils.ThreeState;

/**
 * 模糊查询拆迁人员model
 * @author jjh
 * @time 2015-12-29 15：39
 *
 */
public class FamilyItemQueryModel {
		/*private fields*/
		/**身份证*/
		private String idNumber;
		/**项目名称*/
		private String proName;
		/**是否是独身子女枚举*/
		private ThreeState isOnlyChild;
		/**是否是半边户*/
		private ThreeState half;
		/**the street address id*/
		private UUID streetId;
		/**the community address id*/
		private UUID communityId;
		/**是否转户枚举*/
		private ThreeState isTransfer;
		/**是否社保枚举*/
		private ThreeState isSSecurity;
		/**组地址id*/
		private UUID groupId;
		/**姓名*/
		private String name;
		/**户主姓名*/
		private String headName;
		/**创建人的ID*/
		private String createUId;
		/**地址*/
		private String address;
		
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		public String getProName() {
			return proName;
		}
		public void setProName(String proName) {
			this.proName = proName;
		}
		public ThreeState getIsOnlyChild() {
			return isOnlyChild;
		}
		public void setIsOnlyChild(ThreeState isOnlyChild) {
			this.isOnlyChild = isOnlyChild;
		}
		public ThreeState isHalf() {
			return half;
		}
		public void setHalf(ThreeState half) {
			this.half = half;
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
		public ThreeState getIsTransfer() {
			return isTransfer;
		}
		public void setIsTransfer(ThreeState isTransfer) {
			this.isTransfer = isTransfer;
		}
		public ThreeState getIsSSecurity() {
			return isSSecurity;
		}
		public void setIsSSecurity(ThreeState isSSecurity) {
			this.isSSecurity = isSSecurity;
		}
		public UUID getGroupId() {
			return groupId;
		}
		public void setGroupId(UUID groupId) {
			this.groupId = groupId;
		}
		public ThreeState getHalf() {
			return half;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getHeadName() {
			return headName;
		}
		public void setHeadName(String headName) {
			this.headName = headName;
		}
		public String getCreateUId() {
			return createUId;
		}
		public void setCreateUId(String createUId) {
			this.createUId = createUId;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
}
