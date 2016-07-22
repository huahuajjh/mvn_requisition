package com.tq.requisition.domain.model.resource;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 资源聚合根
 * @author jjh
 * @time 2015-12-19 15:41
 */
public class Resource extends AggregateRoot{
	/*private fields*/
	/**资源title*/
	private String title;
	/**资源link*/
	private String link;
	/**资源icon*/
	private String icon;
	/**资源类型*/
	private Integer type;
	/**排序*/
	private Integer order;
	/**是否可见*/
	private boolean visible;
	/**父资源id*/
	private UUID parentResourceId;
	/**是否有子节点*/
	private boolean children;
	/**祖先路径序列*/
	private String path;
	/**htmlid*/
	private String htmlId;
	/**html*/
	private String html;
	/**资源层级*/
	private Integer hierarchy;
	/**模块名称*/
	private String moudle;
	
	/*gettsers and setters*/		
	public String getTitle() {
		return title;
	}
	public String getHtmlId() {
		return htmlId;
	}
	public void setHtmlId(String htmlId) {
		this.htmlId = htmlId;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Integer getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean getChildren() {
		return children;
	}
	public void setChildren(boolean hasChildren) {
		this.children = hasChildren;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public UUID getParentResourceId() {
		return parentResourceId;
	}
	public void setParentResourceId(UUID parentResourceId) {
		this.parentResourceId = parentResourceId;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID _id) {
		id = _id;
	}
	public String getMoudle() {
		return moudle;
	}
	public void setMoudle(String moudle) {
		this.moudle = moudle;
	}
	
	/*override toString*/
	@Override
	public String toString() {
		return "Resource [title=" + title + ", link=" + link + ", icon=" + icon
				+ ", type=" + type + ", order=" + order + ", visible="
				+ visible + ", parentResourceId=" + parentResourceId
				+ ", hasChildren=" + children + ", path=" + path
				+ ", htmlId=" + htmlId + ", html=" + html + ", hierarchy="
				+ hierarchy + ", id=" + id + "]";
	}
	
	/*constructors*/
	public Resource(){}
	public Resource(UUID id,String title, String link, String icon, Integer type,
			Integer order, boolean visible, UUID parentResourceId,
			boolean hasChildren, String path,String htmlId,String html,Integer hierarchy) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.icon = icon;
		this.type = type;
		this.order = order;
		this.visible = visible;
		this.parentResourceId = parentResourceId;
		this.children = hasChildren;
		this.path = path;
		this.hierarchy = hierarchy;
		this.html = html;
		this.htmlId = htmlId;
	}
		
	/*public methods*/
	public static Resource obtain(String _title,String _link,String _icon,Integer _type,//
			Integer _order,UUID pid,boolean children,String _path,String _htmlid,String _html,Integer _hierarchy) {
		
		if(_title==null || _type == null || _hierarchy == null || _order ==null)
		{
			throw new NullPointerException("title等为null");
		}
		
		return new Resource(//
				UUID.randomUUID(), //
				_title, //
				_link,//
				_icon, //
				_type,//
				_order, //
				true,//
				pid,//
				children, //
				_path, //
				_htmlid, //
				_html, //
				_hierarchy);
	}
	
}
