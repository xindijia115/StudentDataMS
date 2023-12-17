package xindijia.controller;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import xindijia.entity.Student;
import xindijia.service.StudentService;
import xindijia.service.impl.StudentServiceImpl;

import java.util.Optional;

/**
 * @author xia
 * @since 2023/12/17/12:58
 */
public class StudentController extends BaseController{

    private StudentService studentService = new StudentServiceImpl();
    /**
     * 添加学生
     */
    public void addStudent() {
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("添加学生");
        dialog.setHeaderText("请在下面输入要添加的学生信息：");
        DialogPane dialogPane = dialog.getDialogPane();//获取对话框的对象
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 60, 10, 10));

        TextField sid = new TextField();
        TextField sClass = new TextField();
        TextField name = new TextField();
        TextField gender = new TextField();
        TextField birth = new TextField();
        TextField major = new TextField();

        grid.add(new Label("学号:"), 0, 0);
        grid.add(sid, 1, 0);
        grid.add(new Label("班级:"), 0, 1);
        grid.add(sClass, 1, 1);
        grid.add(new Label("姓名:"), 0, 2);
        grid.add(name, 1, 2);
        grid.add(new Label("性别:"), 0, 3);
        grid.add(gender, 1, 3);
        grid.add(new Label("出生日期:"), 0, 4);
        grid.add(birth, 1, 4);
        grid.add(new Label("所在专业:"), 0, 5);
        grid.add(major, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {//点击ok增加一条数据
                if (checkIsEmpty(grid)) {
                    return null;
                }
                return new Student(Integer.parseInt(sid.getText()), sClass.getText(), name.getText(), gender.getText(), birth.getText(), major.getText());
            }
            return null;
        });

        Optional<Student> result = dialog.showAndWait();
        result.ifPresent(student -> {
            Student studentById = studentService.getStudentById(student.getSid());
            if (studentById != null) {
                alert("失败提示", "学号为【" + student.getSid() + "】的学生数据已经存在，无法添加！", null, Alert.AlertType.ERROR);
            } else {
                //保存信息到数据库
                studentService.saveStudent(student);
                alert("成功提示", "成功保存学号为【" + student.getSid() + "】的学生数据！", null, Alert.AlertType.INFORMATION);
            }
        });
    }

    /**
     * 修改学生信息
     */
    public void updateStudent() {
        TextInputDialog textInputDialog = new TextInputDialog();//文本对话框
        textInputDialog.setTitle("修改学生信息");
        textInputDialog.setHeaderText("请输入需要修改信息的学生号:");
        textInputDialog.setContentText("学号:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (checkIdIllegal(result.get())) {//id不合法
                return;
            }

            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
            if (student != null) {
                Dialog<Student> dialog = new Dialog<>();
                dialog.setTitle("修改信息");
                dialog.setHeaderText("请在下面输入要修改的学生信息：");
                DialogPane dialogPane = dialog.getDialogPane();//获取对话框的对象
                dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 60, 10, 10));

                TextField sid = new TextField();
                TextField sClass = new TextField();
                TextField name = new TextField();
                TextField gender = new TextField();
                TextField birth = new TextField();
                TextField major = new TextField();

                grid.add(new Label("学号:"), 0, 0);
                grid.add(sid, 1, 0);
                grid.add(new Label("班级:"), 0, 1);
                grid.add(sClass, 1, 1);
                grid.add(new Label("姓名:"), 0, 2);
                grid.add(name, 1, 2);
                grid.add(new Label("性别:"), 0, 3);
                grid.add(gender, 1, 3);
                grid.add(new Label("出生日期:"), 0, 4);
                grid.add(birth, 1, 4);
                grid.add(new Label("所在专业:"), 0, 5);
                grid.add(major, 1, 5);
                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(button -> {
                    if (button == ButtonType.OK) {//点击ok增加一条数据
                        if (checkIsEmpty(grid)) {
                            return null;
                        }
                        return new Student(Integer.parseInt(sid.getText()), sClass.getText(), name.getText(), gender.getText(), birth.getText(), major.getText());
                    }
                    return null;
                });

                Optional<Student> results = dialog.showAndWait();
                results.ifPresent(student1 -> studentService.updateStudent(student1));
                alert("成功提示", "成功修改学号为【" + student.getSid() + "】的学生数据！", null, Alert.AlertType.INFORMATION);
            } else {
                alert("错误提示", "没有该学生的记录，无法修改！", null, Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * 删除学生
     */
    public void deleteStudent() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("删除学生");
        textInputDialog.setHeaderText("请输入要删除的学生学号：");
        textInputDialog.setContentText("学号:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (checkIdIllegal(result.get())) {//不合法
                return;
            }
            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
            if (student != null) {
                studentService.deleteStudentBy(student.getSid());
                alert("成功提示", "成功删除学号为【" + student.getSid() + "】的学生数据！", null, Alert.AlertType.INFORMATION);
            } else {
                alert("错误提示", "没有该学生的记录，无法删除！", null, Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * 查询学生信息
     */
    public void selectStudent() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("查询学生");
        textInputDialog.setHeaderText("请输入要查询的学生学号:");
        textInputDialog.setContentText("学号:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (checkIdIllegal(result.get())) {
                return;
            }
            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
            if (student != null) {
                Dialog<Student> dialog = new Dialog<>();
                dialog.setTitle("学生数据");
                dialog.setHeaderText(null);

                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.getButtonTypes().add(ButtonType.OK);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20,60,10,10));

                TextField sid = new TextField(student.getSid().toString());
                sid.setEditable(false);
                TextField sClass = new TextField(student.getSClass());
                sClass.setEditable(false);
                TextField name = new TextField(student.getName());
                name.setEditable(false);
                TextField gender = new TextField(student.getGender());
                gender.setEditable(false);
                TextField birth = new TextField(student.getBirth());
                birth.setEditable(false);
                TextField major = new TextField(student.getMajor());
                major.setEditable(false);

                grid.add(new Label("学号:"), 0, 0);
                grid.add(sid, 1, 0);
                grid.add(new Label("班级:"), 0, 1);
                grid.add(sClass, 1, 1);
                grid.add(new Label("姓名:"), 0, 2);
                grid.add(name, 1, 2);
                grid.add(new Label("性别:"), 0, 3);
                grid.add(gender, 1, 3);
                grid.add(new Label("出生日期:"), 0, 4);
                grid.add(birth, 1, 4);
                grid.add(new Label("所在专业:"), 0, 5);
                grid.add(major, 1, 5);

                dialog.getDialogPane().setContent(grid);
                dialog.showAndWait();
            } else {
                alert("错误提示","没有该学生的记录，无法查询！",null, Alert.AlertType.ERROR);
            }
        }
    }

}
