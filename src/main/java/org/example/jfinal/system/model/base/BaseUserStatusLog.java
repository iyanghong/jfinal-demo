package org.example.jfinal.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserStatusLog<M extends BaseUserStatusLog<M>> extends Model<M> implements IBean {

	/**
	 * 自增id
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	/**
	 * 自增id
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
	 * 用户
	 */
	public void setUser(java.lang.String user) {
		set("user", user);
	}
	
	/**
	 * 用户
	 */
	public java.lang.String getUser() {
		return getStr("user");
	}
	
	/**
	 * 当前状态
	 */
	public void setCurrentStatus(java.lang.Integer currentStatus) {
		set("current_status", currentStatus);
	}
	
	/**
	 * 当前状态
	 */
	public java.lang.Integer getCurrentStatus() {
		return getInt("current_status");
	}
	
	/**
	 * 变更后状态
	 */
	public void setChangeStatus(java.lang.Integer changeStatus) {
		set("change_status", changeStatus);
	}
	
	/**
	 * 变更后状态
	 */
	public java.lang.Integer getChangeStatus() {
		return getInt("change_status");
	}
	
	/**
	 * 备注
	 */
	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	/**
	 * 备注
	 */
	public java.lang.String getRemark() {
		return getStr("remark");
	}
	
	/**
	 * 状态结束时间
	 */
	public void setDuration(java.util.Date duration) {
		set("duration", duration);
	}
	
	/**
	 * 状态结束时间
	 */
	public java.util.Date getDuration() {
		return getDate("duration");
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

