package com.lrr.eduService.bean.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTOrder<M extends BaseTOrder<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}
	
	/**
	 * 订单号
	 */
	public void setOrderNo(java.lang.String orderNo) {
		set("orderNo", orderNo);
	}
	
	/**
	 * 订单号
	 */
	public java.lang.String getOrderNo() {
		return getStr("orderNo");
	}
	
	/**
	 * 课程id
	 */
	public void setCourseId(java.lang.String courseId) {
		set("courseId", courseId);
	}
	
	/**
	 * 课程id
	 */
	public java.lang.String getCourseId() {
		return getStr("courseId");
	}
	
	/**
	 * 课程名称
	 */
	public void setCourseTitle(java.lang.String courseTitle) {
		set("courseTitle", courseTitle);
	}
	
	/**
	 * 课程名称
	 */
	public java.lang.String getCourseTitle() {
		return getStr("courseTitle");
	}
	
	/**
	 * 课程封面
	 */
	public void setCourseCover(java.lang.String courseCover) {
		set("courseCover", courseCover);
	}
	
	/**
	 * 课程封面
	 */
	public java.lang.String getCourseCover() {
		return getStr("courseCover");
	}
	
	/**
	 * 讲师名称
	 */
	public void setTeacherName(java.lang.String teacherName) {
		set("teacherName", teacherName);
	}
	
	/**
	 * 讲师名称
	 */
	public java.lang.String getTeacherName() {
		return getStr("teacherName");
	}
	
	/**
	 * 会员id
	 */
	public void setMemberId(java.lang.String memberId) {
		set("memberId", memberId);
	}
	
	/**
	 * 会员id
	 */
	public java.lang.String getMemberId() {
		return getStr("memberId");
	}
	
	/**
	 * 会员昵称
	 */
	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}
	
	/**
	 * 会员昵称
	 */
	public java.lang.String getNickname() {
		return getStr("nickname");
	}
	
	/**
	 * 会员手机
	 */
	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}
	
	/**
	 * 会员手机
	 */
	public java.lang.String getMobile() {
		return getStr("mobile");
	}
	
	/**
	 * 订单金额（分）
	 */
	public void setTotalFee(java.math.BigDecimal totalFee) {
		set("totalFee", totalFee);
	}
	
	/**
	 * 订单金额（分）
	 */
	public java.math.BigDecimal getTotalFee() {
		return get("totalFee");
	}
	
	/**
	 * 订单状态（0：未支付 1：已支付）
	 */
	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	/**
	 * 订单状态（0：未支付 1：已支付）
	 */
	public java.lang.Integer getStatus() {
		return getInt("status");
	}
	
}

