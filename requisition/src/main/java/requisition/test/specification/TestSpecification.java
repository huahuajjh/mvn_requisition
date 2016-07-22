package com.tq.requisition.test.specification;

import org.junit.Test;

import com.tq.requisition.domain.Specification.ISpecification;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;

public class TestSpecification {
	@Test
	public void hqlTest() {
		ISpecification<Account> s = new SpecificationExt<Account>(Account.class) {
			@Override
			public String getAbsHql() {
				return "";
			}
			@Override
			public String getAbsSql() {
				return "select * from tb_account where account='jjh48' and pwd='huahaujjh7'";
			}
			@Override
			public Object[] getAbsParameters() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public OperationType getAbsType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		System.out.println(s.getHqlExpression().getSql());
		
	}
	
	@Test
	public void hqlExp() {
		IHqlExpression expression = new IHqlExpression() {

			@Override
			public void setSql(java.lang.String hql) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setHql(java.lang.String hql) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public java.lang.String getSql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public java.lang.String getHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setParameters(Object... objects) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Object[] getParameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setType(OperationType type) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public OperationType getType() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		 
	}
}
