package xindijia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import xindijia.entity.Course;
import xindijia.entity.Score;
import xindijia.mapper.CourseMapper;
import xindijia.mapper.ScoreMapper;
import xindijia.service.CourseService;
import xindijia.utils.MybatisUtil;

import java.util.List;


/**
 * @author xia
 * @since 2023/12/16/12:18
 */
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Override
    public List<Course> getCourseList() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        return mapper.selectList(null);
    }

    @Override
    public Course getCourseById(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        return mapper.selectById(id);
    }

    @Override
    public void saveCourse(Course course) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        mapper.insert(course);
        sqlSession.commit();
    }

    @Override
    public void updateCourse(Course course) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        mapper.updateById(course);
        sqlSession.commit();
    }

    @Override
    public void deleteCourse(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        mapper.deleteById(id);
    }

}
