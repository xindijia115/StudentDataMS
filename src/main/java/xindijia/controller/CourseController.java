package xindijia.controller;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import xindijia.entity.Course;
import xindijia.service.CourseService;
import xindijia.service.impl.CourseServiceImpl;

import java.util.Optional;

/**
 * @author xia
 * @since 2023/12/17/13:01
 */
public class CourseController extends BaseController{

    private CourseService courseService = new CourseServiceImpl();
    /**
     * 添加课程
     */
    public void addCourse() {
        Dialog<Course> dialog = new Dialog<>();
        dialog.setTitle("添加课程");
        dialog.setHeaderText("请输入需要添加的课程信息:");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,60,10,10));

        TextField cid = new TextField();
        TextField major = new TextField();
        TextField courseName = new TextField();
        TextField type = new TextField();
        TextField startTerm = new TextField();
        TextField period = new TextField();
        TextField credit = new TextField();

        grid.add(new Label("课程号:"), 0, 0);
        grid.add(cid, 1, 0);
        grid.add(new Label("所属专业:"), 0, 1);
        grid.add(major, 1, 1);
        grid.add(new Label("课程名称:"), 0, 2);
        grid.add(courseName, 1, 2);
        grid.add(new Label("课程类型:"), 0, 3);
        grid.add(type, 1, 3);
        grid.add(new Label("开课学期:"), 0, 4);
        grid.add(startTerm, 1, 4);
        grid.add(new Label("学时数:"), 0, 5);
        grid.add(period, 1, 5);
        grid.add(new Label("学分:"), 0, 6);
        grid.add(credit, 1, 6);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(button -> {
            if(button == ButtonType.OK) {
                if (checkIsEmpty(grid)) {
                    return null;
                }
                return new Course(Integer.parseInt(cid.getText()), major.getText(), courseName.getText(),
                        type.getText(), startTerm.getText(), period.getText(), Double.parseDouble(credit.getText()));
            }
            return null;
        });
        Optional<Course> results = dialog.showAndWait();
        results.ifPresent(course -> {
            Course courseById = courseService.getCourseById(course.getCid());
            if (courseById != null) {
                alert("错误提示","课程号为【" + course.getCid() + "】的数据已经存在，无法添加！",null, Alert.AlertType.ERROR);
            } else {
                courseService.saveCourse(course);
                alert("成功提示","成功保存课程号为【" + course.getCid() + "】的课程数据！",null, Alert.AlertType.INFORMATION);
            }
        });
    }

    public void updateCourse() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("修改课程");
        textInputDialog.setHeaderText("请输入需要修改的课程的课程号:");
        textInputDialog.setContentText("课程号:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (checkIdIllegal(result.get())) {
                return;
            }
            Course courseById = courseService.getCourseById(Integer.parseInt(result.get()));
            if (courseById != null) {
                Dialog<Course> dialog = new Dialog<>();
                dialog.setTitle("课程数据");
                dialog.setHeaderText(null);

                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20,60,10,10));

                TextField cid = new TextField(courseById.getCid().toString());
                TextField major = new TextField(courseById.getMajor());
                TextField courseName = new TextField(courseById.getCourseName());
                TextField type = new TextField(courseById.getType());
                TextField startTerm = new TextField(courseById.getStartTerm());
                TextField period = new TextField(courseById.getPeriod());
                TextField credit = new TextField(courseById.getCredit().toString());

                grid.add(new Label("课程号:"), 0, 0);
                grid.add(cid, 1, 0);
                grid.add(new Label("所属专业:"), 0, 1);
                grid.add(major, 1, 1);
                grid.add(new Label("课程名称:"), 0, 2);
                grid.add(courseName, 1, 2);
                grid.add(new Label("课程类型:"), 0, 3);
                grid.add(type, 1, 3);
                grid.add(new Label("开课学期:"), 0, 4);
                grid.add(startTerm, 1, 4);
                grid.add(new Label("学时数:"), 0, 5);
                grid.add(period, 1, 5);
                grid.add(new Label("学分:"), 0, 6);
                grid.add(credit, 1, 6);

                dialog.getDialogPane().setContent(grid);
                Optional<Course> results = dialog.showAndWait();

                if(results.isPresent()){
                    courseById = new Course(Integer.parseInt(cid.getText()),major.getText(), courseName.getText(),type.getText(),
                            startTerm.getText(),period.getText(), Double.parseDouble(credit.getText()));

                    courseService.updateCourse(courseById);
                    alert("成功提示","成功修改课程号为【" + courseById.getCid() + "】的课程数据！",null, Alert.AlertType.INFORMATION);
                }
            } else {
                alert("错误提示","没有该课程的记录，无法修改！",null, Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * 删除课程
     */
    public void deleteCourse() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("删除课程");
        textInputDialog.setHeaderText("请输入需要删除的课程的课程号:");
        textInputDialog.setContentText("课程号:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if (checkIdIllegal(result.get())) {
                return;
            }
            Course courseById = courseService.getCourseById(Integer.parseInt(result.get()));
            if (courseById != null) {
                courseService.deleteCourse(courseById.getCid());
                alert("成功提示","成功删除课程号为【" + courseById.getCid() + "】的课程数据！",null, Alert.AlertType.INFORMATION);
            } else {
                alert("错误提示","没有该课程的记录，无法删除！",null, Alert.AlertType.ERROR);
            }
        }
    }
}
