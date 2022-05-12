package com.lrr.eduService.service.front;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.*;
import com.lrr.eduService.bean.EduChapter;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.chapter.ChapterData;
import com.lrr.eduService.bean.vo.EduCoursePublish;
import com.lrr.eduService.bean.vo.FrontCourseQueryVo;
import com.lrr.eduService.service.after.EduChapterService;
import com.lrr.eduService.service.after.EduCourseService;
import net.sf.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/eduservice/front")
public class FrontCourseService extends Controller {
    private final EduCourseService eduCourseService = new EduCourseService();
    private final EduChapterService eduChapterService = new EduChapterService();
    public Map<String,Object> findCourseByPages(String current,String limit,String json){
        FrontCourseQueryVo frontCourseQueryVo = (FrontCourseQueryVo)new JSONObject().toBean(JSONObject.fromObject(json), FrontCourseQueryVo.class);
        Page<Record> data = null;
        if ("{}".equals(json) || "".equals(json)){
            //单纯排序
            data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course");
        }else{
            //nt式拼法。。。
            String sql = "from edu_course where  1=1";
            if("".equals(frontCourseQueryVo.getSubjectId()) == false)
                sql += " and subjectId = '"+frontCourseQueryVo.getSubjectId()+"'";
            if("".equals(frontCourseQueryVo.getSubjectParentId()) == false)
                sql += " and subjectParentId = '"+frontCourseQueryVo.getSubjectParentId()+"'";

            if("".equals(frontCourseQueryVo.getBuyCountSort()) == false && "".equals(frontCourseQueryVo.getPriceSort()) == false){
                sql += " order by price asc,buyCount desc";
                //按价格购买量降序,价格升序
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit),"select *",sql);
                //data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where subjectId = ? and subjectParentId = ? order by price desc,buyCount desc",frontCourseQueryVo.getSubjectId(),frontCourseQueryVo.getSubjectParentId());
            }else if("".equals(frontCourseQueryVo.getBuyCountSort()) == false){
                //按购买量降序
                sql += " order by buyCount desc";
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit),"select *",sql);
                //data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where subjectId = ? and subjectParentId = ? order by buyCount desc",frontCourseQueryVo.getSubjectId(),frontCourseQueryVo.getSubjectParentId());
            }else if("".equals(frontCourseQueryVo.getPriceSort()) == false){
                //按价格升序
                sql += " order by price asc";
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit),"select *",sql);
                //data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where subjectId = ? and subjectParentId = ? order by price desc",frontCourseQueryVo.getSubjectId(),frontCourseQueryVo.getSubjectParentId());
            }else{
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit),"select *",sql);
                //data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where subjectId = ? and subjectParentId = ?",frontCourseQueryVo.getSubjectId(),frontCourseQueryVo.getSubjectParentId());
            }
        }
        //封装分页数据
        ArrayList<EduCourse> eduCourses = new ArrayList<>();
        List<Record> list = data.getList();
        for(Record record : list){
            EduCourse eduCourse = new EduCourse();
            eduCourse = eduCourse.put(record);
            /*eduCourse.setId(record.get("id"));
            eduCourse.setTitle(record.get("title"));
            eduCourse.setStatus(record.get("status"));
            eduCourse.setPrice(record.getBigDecimal("price"));
            eduCourse.setBuyCount(record.getBigInteger("buyCount"));
            eduCourse.setCover(record.get("cover"));*/
            eduCourses.add(eduCourse);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("items",eduCourses);
        map.put("total",data.getTotalRow());
        return map;
    }
    public Map<String,Object> findCourseDetailById(String id){
        Map<String, Object> map = new HashMap<>();
        EduCoursePublish publishCourseInfo = eduCourseService.getPublishCourseInfo(id);//得到课程的发布信息
        map.put("item",publishCourseInfo);
        List<ChapterData> allChapterAndVideo = eduChapterService.findAllChapterAndVideo(id);
        map.put("chapterList",allChapterAndVideo);
        return map;
    }
    public Map<String,Object> searchCourse(String current,String limit,String title){//根据课程名搜索课程 模糊搜索
        List<EduCourse> items = new ArrayList<>();
        Kv title1 = Kv.of("title", title);
        Page<Record> search = Db.template("search", title1).paginate(Integer.valueOf(current), Integer.valueOf(limit));
        List<Record> records = search.getList();
        for(Record record:records){
            EduCourse eduCourse = new EduCourse();
            EduCourse put = eduCourse.put(record);
            items.add(put);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("items",items);
        map.put("total",search.getTotalRow());
        return map;
    }
}
