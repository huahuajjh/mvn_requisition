package com.tq.requisition.domain.share;

import java.util.UUID;

import com.tq.requisition.exception.DomainException;

/**
 * 表明聚合根类型的基类型
 * 
 * @author jjh
 * @time 2015-12-14 13:30
 */
public abstract class AggregateRoot implements IAggregateRoot {
	/* domain实体的唯一标识 */
	protected UUID id;

	// public methods
	@Override
	public boolean equals(Object obj) {
		// TODO
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public UUID id() {
		return this.id;
	}

	@Override
	public void id(UUID _id) {
		this.id = _id;
	}

	public void check(Object str,String msg) throws DomainException {
		if (null == str || str.toString().trim().equals("")) {
			throw new DomainException(msg);
		}
	}
}
