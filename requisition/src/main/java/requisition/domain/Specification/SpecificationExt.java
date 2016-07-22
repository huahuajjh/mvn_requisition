package com.tq.requisition.domain.Specification;

import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;

/**
 * 规约抽象扩展类，实现匿名规约，如果规约并不复杂，可以实例化该抽象类。
 * @author jjh
 * @param <T>
 * 		满足规约的对象
 * @date 2015-12-21 22:39
 *
 */
public abstract class SpecificationExt<T>  extends Specification<T>{	
	/**
	 * 请实现该方法，该方法应该返回一个hql表达式
	 */
	public abstract String getAbsHql();
	
	/**
	 * 请实现该方法，该方法应该返回一个sql表达式
	 */
	public abstract String getAbsSql();
	
	/**
	 * 请实现该方法，该方法应该返回hql或者sql表达式所需要的参数
	 * @return
	 */
	public abstract Object[] getAbsParameters();
	
	/**
	 * 请实现该方法，该方法返回操作表达式的操作类型
	 * @return
	 */
	public abstract OperationType getAbsType();
	
	/**
	 * {@inheritDoc}
	 * @param t
	 */
	public SpecificationExt(Class<T> t) {
		super(t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new IHqlExpression() {
			@Override
			public void setHql(String hql) {}
			
			@Override
			public void setSql(String sql) {}
			
			@Override
			public String getSql() {
				return getAbsSql();
			}
			
			@Override
			public String getHql() {
				return getAbsHql();
			}

			@Override
			public void setParameters(Object... objects) {}

			@Override
			public Object[] getParameters() {
				return getAbsParameters();
			}

			
			@Override
			public void setType(OperationType type) {}

			
			@Override
			public OperationType getType() {				
				if(getAbsType()==null)
				{					
					return OperationType.SQL;
				}
				return getAbsType();
			}			
		};
		return expression;
	}
}
