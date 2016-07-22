package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

/**
 * 资源DTO
 * 
 * @author Bless
 * @time 2015-12-24 15:36
 */
public class ResDto {
	private UUID id;
	private String name;
	private String link;
	private UUID pId;
	private String htmlId;
	private String html;
	private Integer order;
	private int hierarchy;
	private Integer type;
	private String path;
	
	/**
	 * 获取资源祖先节点序列
	 * @return
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * 设置资源祖先节点序列
	 * @return
	 */

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取资源类型
	 * 
	 * @return 资源类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置资源类型
	 * 
	 * @param type
	 *            资源类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	public int getHierarchy() {
		return hierarchy;
	}

	/**
	 * 设置层级
	 * 
	 * @param hierarchy
	 *            层级
	 */
	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}

	/**
	 * 获取资源标识
	 * 
	 * @return 资源标识
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * 写入资源标识
	 * 
	 * @param id
	 *            资源标识
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * 获取资源名称
	 * 
	 * @return 资源名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 写入资源名称
	 * 
	 * @param name
	 *            资源名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取资源链接
	 * 
	 * @return 资源链接
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 写入资源链接
	 * 
	 * @param link
	 *            资源链接
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 获取资源父标识
	 * 
	 * @return 资源父标识
	 */
	public UUID getpId() {
		return pId;
	}

	/**
	 * 写入资源父标识
	 * 
	 * @param pId
	 *            资源父标识
	 */
	public void setpId(UUID pId) {
		this.pId = pId;
	}

	/**
	 * 获取资源Html标识
	 * 
	 * @return 资源Html标识
	 */
	public String getHtmlId() {
		return htmlId;
	}

	/**
	 * 写入资源Html标识
	 * 
	 * @param htmlId
	 *            资源Html标识
	 */
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}

	/**
	 * 获取Html资源
	 * 
	 * @return Html资源
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * 写入Html资源
	 * 
	 * @param html
	 *            Html资源
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * 获取资源排序
	 * 
	 * @return 资源排序
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * 写入资源排序
	 * 
	 * @param order
	 *            资源排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ResDto [id=" + id + ", name=" + name + ", link=" + link
				+ ", pId=" + pId + ", htmlId=" + htmlId + ", html=" + html
				+ ", order=" + order + ", hierarchy=" + hierarchy + "]";
	}

	public ResDto(UUID id, String name, String link, UUID pId, String htmlId,
			String html, int order, int hierarchy,Integer type,String path) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.pId = pId;
		this.htmlId = htmlId;
		this.html = html;
		this.order = order;
		this.hierarchy = hierarchy;
		this.type = type;
		this.path = path;
	}
	
	public ResDto(){}
	
	/*public methods*/
	public static ResDto obtain(//
			UUID _id,String _name,String _link,UUID _pid,String _htmlid,//
			String _html,Integer _order,Integer _hierarchy,Integer _type,//
			String _path
			){
			
		return new ResDto(//
				_id,//
				_name, //
				_link, //
				_pid, //
				_htmlid, //
				_html, //
				_order,//
				_hierarchy, //
				_type,//
				_path);
	}

}
