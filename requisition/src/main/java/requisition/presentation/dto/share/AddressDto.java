package com.tq.requisition.presentation.dto.share;

import java.util.UUID;

public class AddressDto {
	/**
	 * 节点id
	 */
	private UUID id;

	/** 父id */
	private UUID pid;

	/**
	 * 节点name
	 */
	private String name;


	/* getters and setters */
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getPid() {
		return pid;
	}

	public void setPid(UUID pid) {
		this.pid = pid;
	}

	/* constructors */
	public AddressDto() {
	}

	public AddressDto(UUID id, String name,UUID pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}

	/* public methods */
	public static AddressDto obtain(String title, UUID id,UUID pid) {
		return new AddressDto(id, title,pid);
	}
}
