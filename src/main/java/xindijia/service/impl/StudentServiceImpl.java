package xindijia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import xindijia.entity.Student;
import xindijia.mapper.StudentMapper;
import xindijia.service.StudentService;
import xindijia.utils.MybatisUtil;

import java.util.List;

/**
 * @author xia
 * @since 2023/12/16/12:16
 */
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    private StudentMapper mapper = MybatisUtil.getSqlSession().getMapper(StudentMapper.class);
    @Override
    public List<Student> getStudentList() {
        return mapper.selectList(null);
    }

    @Override
    public Student getStudentById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public void saveStudent(Student student) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.insert(student);
        sqlSession.commit();
    }

    @Override
    public void updateStudent(Student student) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateById(student);
        sqlSession.commit();
    }

    @Override
    public void deleteStudentBy(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteById(id);
    }
}
