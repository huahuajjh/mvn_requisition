package com.tq.requisition.infrastructure.Specifications.project;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，根据项目审批号判断是否存在
 * @author jjh
 * @time 2016-01-15 17:48
 *
 */
public class ProExistsByApprovalNumSpecification extends Specification<Project>{
		private String approval;
		public ProExistsByApprovalNumSpecification(Class<Project> _t,String approvalNum) {
			super(_t);
			this.approval = approvalNum;
		}
		
		@Override
		public IHqlExpression getHqlExpression() {
			IHqlExpression expression = new HqlExpression();
			expression.setSql("select count(1) from tb_project where approval_number=?");
			expression.setParameters(approval);
			expression.setType(OperationType.SQL);
			return expression;
		}
}
