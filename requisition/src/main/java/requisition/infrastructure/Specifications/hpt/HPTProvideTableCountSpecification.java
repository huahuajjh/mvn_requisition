package com.tq.requisition.infrastructure.Specifications.hpt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;

/**
 * 根据model查询发放台账总记录数规约
 * @author jjh
 * @time 2015-01-04 1:55
 *
 */
public class HPTProvideTableCountSpecification extends Specification<HousePuraseTicket>{
	private HPTFuzzyQueryModel queryModel;
	
	public HPTProvideTableCountSpecification(Class<HousePuraseTicket> _t,HPTFuzzyQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_ticket_provider pv ");
		sb.append(" inner join tb_housepurchase_ticket h ");
		sb.append(" inner join tb_family_item fi ");
		sb.append(" where pv.ticket_id=h.id and h.fml_item_id=fi.id and pv.del=0 and h.del=0 ");
		if(queryModel.getProName() != null){
			sb.append("and fi.pro_name like '%" + queryModel.getProName() + "%'");
		}
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
