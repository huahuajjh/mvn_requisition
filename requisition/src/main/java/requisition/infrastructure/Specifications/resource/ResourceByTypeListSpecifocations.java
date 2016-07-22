package com.tq.requisition.infrastructure.Specifications.resource;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.model.resource.Resource;
import com.tq.requisition.domain.model.share.ResourceType;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class ResourceByTypeListSpecifocations  extends Specification<Resource> {

	private String resIds = "";
	private int hierarchy = -1;
	private String type = "";
	
	public ResourceByTypeListSpecifocations(Class<Resource> _t,int hierarchy,ResourceType type,UUID... resIds) {
		super(_t);
		this.type = type.toString();
		this.hierarchy = hierarchy;
		if (resIds != null && resIds.length > 0) {
			for (int i = 0; i < resIds.length; i++) {
				this.resIds = "\"" + resIds[i].toString() + "\",";
			}
			this.resIds = this.resIds.substring(0, resIds.length - 1);
		}
	}

	/**
	 * 获取hql表达式
	 * 
	 * @return IHqlExpression hql表达式实例
	 */
	@Override
	public IHqlExpression getHqlExpression() {
		HqlExpression hqlExpression = new HqlExpression();
		hqlExpression.setSql("select * from tb_resource where id in (" + resIds + ") and ");
		return hqlExpression;
	}
}
