#sql("findCoursePublishInfo")
SELECT
    c.id,
    c.title,
    c.cover,
    CONVERT(c.price, DECIMAL(8,2)) AS price,
    c.cover,
    c.buyCount,
    c.description,
    t.id AS teacherId,
    t.name AS teacherName,
    t.intro,
    t.avatar,
    s1.id AS subjectLevelOneId,
    s1.title AS subjectLevelOne,
    s2.id AS subjectLevelTwoId,
    s2.title AS subjectLevelTwo
FROM
    edu_course c
        LEFT JOIN edu_teacher t ON c.teacherId = t.id
        LEFT JOIN edu_subject s1 ON c.subjectParentId = s1.id
        LEFT JOIN edu_subject s2 ON c.subjectId = s2.id
WHERE
        c.id = ?
#end
#sql("findHotCourse")
select * from edu_course order by `buyCount` asc limit 8
#end
#sql("findHotTeacher")
select * from edu_teacher order by `level` asc,`id` desc limit 8
#end


#sql("search")
select * from edu_course where `title` like concat('%', #para(title), '%')
#end