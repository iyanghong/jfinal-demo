package org.example.jfinal.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePermissionGroup<M extends BasePermissionGroup<M>> extends Model<M> implements IBean {

	/**
	 * 权限组id
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	/**
	 * 权限组id
	 */
	public java.lang.Integer getId() {
		return getInt("id");
	}
	
	/**
	 * uuid
	 */
	public void setUuid(java.lang.String uuid) {
		set("uuid", uuid);
	}
	
	/**
	 * uuid
	 */
	public java.lang.String getUuid() {
		return getStr("uuid");
	}
	
	/**
	 * 所属服务
	 */
	public void setService(java.lang.String service) {
		set("service", service);
	}
	
	/**
	 * 所属服务
	 */
	public java.lang.String getService() {
		return getStr("service");
	}
	
	/**
	 * 权限组名称
	 */
	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	/**
	 * 权限组名称
	 */
	public java.lang.String getName() {
		return getStr("name");
	}
	
	/**
	 * 权限组描述
	 */
	public void setDescription(java.lang.String description) {
		set("description", description);
	}
	
	/**
	 * 权限组描述
	 */
	public java.lang.String getDescription() {
		return getStr("description");
	}
	
	/**
	 * 创建者id
	 */
	public void setCreator(java.lang.String creator) {
		set("creator", creator);
	}
	
	/**
	 * 创建者id
	 */
	public java.lang.String getCreator() {
		return getStr("creator");
	}
	
	/**
	 * 创建时间
	 */
	public void setCreatedAt(java.util.Date createdAt) {
		set("created_at", createdAt);
	}
	
	/**
	 * 创建时间
	 */
	public java.util.Date getCreatedAt() {
		return getDate("created_at");
	}
	
	/**
	 * 修改时间
	 */
	public void setUpdatedAt(java.util.Date updatedAt) {
		set("updated_at", updatedAt);
	}
	
	/**
	 * 修改时间
	 */
	public java.util.Date getUpdatedAt() {
		return getDate("updated_at");
	}
	
}
