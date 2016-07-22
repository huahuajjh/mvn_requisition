package com.tq.requisition.presentation.dto.project;

/**
 * 项目类型dto
 * @author jjh
 *
 */
public class ProTypeDto {
	/*private fields*/
	/**类型代码*/
	private int code;
	/**类型name*/
	private String name;
	
	/*override*/
	@Override
	public String toString() {
		return "ProTypeDto [id=" + code + ", name=" + name + "]";
	}

	/*constructors*/
	public ProTypeDto(){}
	public ProTypeDto(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	/*public methods*/
	public static ProTypeDto obtain(int code,String name) {		
		return new ProTypeDto(code, name);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
