package com.lrr.eduService.bean;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("crm_banner", "id", CrmBanner.class);
		arp.addMapping("edu_chapter", "id", EduChapter.class);
		arp.addMapping("edu_collect", "id", EduCollect.class);
		arp.addMapping("edu_course", "id", EduCourse.class);
		arp.addMapping("edu_subject", "id", EduSubject.class);
		arp.addMapping("edu_teacher", "id", EduTeacher.class);
		arp.addMapping("edu_video", "id", EduVideo.class);
		arp.addMapping("plc_member", "id", PlcMember.class);
		arp.addMapping("t_order", "id", TOrder.class);
		arp.addMapping("t_pay_log", "id", TPayLog.class);
		arp.addMapping("ucenter_member", "id", UcenterMember.class);
	}
}


