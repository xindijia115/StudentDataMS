package xindijia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xindijia.entity.Student;

import java.util.List;

/**
 * @author xia
 * @since 2023/12/16/12:15
 */
public interface StudentService extends IService<Student> {
    //返回所有学生的信息
    List<Student> getStudentList();

    Student getStudentById(Integer id);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentBy(int id);
}
