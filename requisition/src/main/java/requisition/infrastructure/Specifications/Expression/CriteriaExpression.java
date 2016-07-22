package com.tq.requisition.infrastructure.Specifications.Expression;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.tq.requisition.domain.Specification.expression.ICriteriaExpression;

/**
 * Criteria语句表达式
 * @author jjh
 * @time 2015-12-17 16:20
 *
 */
public final class CriteriaExpression extends ICriteriaExpression{
	private List<Criterion> restrictions;
	
	public CriteriaExpression()
	{
		restrictions = new ArrayList<Criterion>();
	}
	
	public CriteriaExpression setRestriction(Criterion _restriction) {
		restrictions.add(_restriction);
		return this;
	}
	
	public List<Criterion> getRestriction() {
		return restrictions;
	}
}
