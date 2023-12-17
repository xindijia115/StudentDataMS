package xindijia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xindijia.entity.Course;
import xindijia.entity.Score;

import java.util.List;

/**
 * @author xia
 * @since 2023/12/16/12:16
 */
public interface CourseService extends IService<Course> {
    List<Course> getCourseList();

    Course getCourseById(int id);

    void saveCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(int id);

}
