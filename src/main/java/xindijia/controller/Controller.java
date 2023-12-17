//package xindijia.controller;
//
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.util.Callback;
//import xindijia.entity.Course;
//import xindijia.entity.Score;
//import xindijia.entity.Student;
//import xindijia.service.CourseService;
//import xindijia.service.ScoreService;
//import xindijia.service.StudentService;
//import xindijia.service.impl.CourseServiceImpl;
//import xindijia.service.impl.ScoreServiceImpl;
//import xindijia.service.impl.StudentServiceImpl;
//
//import java.util.Optional;
//import java.util.function.Consumer;
//
//
///**
// * @author xia
// * @since 2023/12/16/16:51
// */
//public class Controller {
//
//    private StudentService studentService = new StudentServiceImpl();
//
//    private CourseService courseService = new CourseServiceImpl();
//
//    private ScoreService scoreService = new ScoreServiceImpl();
//
//    /**
//     * 添加学生
//     */
//    public void addStudent() {
//        Dialog<Student> dialog = new Dialog<>();
//        dialog.setTitle("添加学生");
//        dialog.setHeaderText("请在下面输入要添加的学生信息：");
//        DialogPane dialogPane = dialog.getDialogPane();//获取对话框的对象
//        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20, 60, 10, 10));
//
//        TextField sid = new TextField();
//        TextField sClass = new TextField();
//        TextField name = new TextField();
//        TextField gender = new TextField();
//        TextField birth = new TextField();
//        TextField major = new TextField();
//
//        grid.add(new Label("学号:"), 0, 0);
//        grid.add(sid, 1, 0);
//        grid.add(new Label("班级:"), 0, 1);
//        grid.add(sClass, 1, 1);
//        grid.add(new Label("姓名:"), 0, 2);
//        grid.add(name, 1, 2);
//        grid.add(new Label("性别:"), 0, 3);
//        grid.add(gender, 1, 3);
//        grid.add(new Label("出生日期:"), 0, 4);
//        grid.add(birth, 1, 4);
//        grid.add(new Label("所在专业:"), 0, 5);
//        grid.add(major, 1, 5);
//
//        dialog.getDialogPane().setContent(grid);
//        dialog.setResultConverter(button -> {
//            if (button == ButtonType.OK) {//点击ok增加一条数据
//                if (checkIsEmpty(grid)) {
//                    return null;
//                }
//                return new Student(Integer.parseInt(sid.getText()), sClass.getText(), name.getText(), gender.getText(), birth.getText(), major.getText());
//            }
//            return null;
//        });
//
//        Optional<Student> result = dialog.showAndWait();
//        result.ifPresent(student -> {
//            Student studentById = studentService.getStudentById(student.getSid());
//            if (studentById != null) {
//                alert("失败提示", "学号为【" + student.getSid() + "】的学生数据已经存在，无法添加！", null, Alert.AlertType.ERROR);
//            } else {
//                //保存信息到数据库
//                studentService.saveStudent(student);
//                alert("成功提示", "成功保存学号为【" + student.getSid() + "】的学生数据！", null, Alert.AlertType.INFORMATION);
//            }
//        });
//    }
//
//    /**
//     * 修改学生信息
//     */
//    public void updateStudent() {
//        TextInputDialog textInputDialog = new TextInputDialog();//文本对话框
//        textInputDialog.setTitle("修改学生信息");
//        textInputDialog.setHeaderText("请输入需要修改信息的学生号:");
//        textInputDialog.setContentText("学号:");
//        Optional<String> result = textInputDialog.showAndWait();
//        if (result.isPresent()) {
//            if (checkIdIllegal(result.get())) {//id不合法
//                return;
//            }
//
//            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
//            if (student != null) {
//                Dialog<Student> dialog = new Dialog<>();
//                dialog.setTitle("修改信息");
//                dialog.setHeaderText("请在下面输入要修改的学生信息：");
//                DialogPane dialogPane = dialog.getDialogPane();//获取对话框的对象
//                dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//                GridPane grid = new GridPane();
//                grid.setHgap(10);
//                grid.setVgap(10);
//                grid.setPadding(new Insets(20, 60, 10, 10));
//
//                TextField sid = new TextField();
//                TextField sClass = new TextField();
//                TextField name = new TextField();
//                TextField gender = new TextField();
//                TextField birth = new TextField();
//                TextField major = new TextField();
//
//                grid.add(new Label("学号:"), 0, 0);
//                grid.add(sid, 1, 0);
//                grid.add(new Label("班级:"), 0, 1);
//                grid.add(sClass, 1, 1);
//                grid.add(new Label("姓名:"), 0, 2);
//                grid.add(name, 1, 2);
//                grid.add(new Label("性别:"), 0, 3);
//                grid.add(gender, 1, 3);
//                grid.add(new Label("出生日期:"), 0, 4);
//                grid.add(birth, 1, 4);
//                grid.add(new Label("所在专业:"), 0, 5);
//                grid.add(major, 1, 5);
//                dialog.getDialogPane().setContent(grid);
//
//                dialog.setResultConverter(button -> {
//                    if (button == ButtonType.OK) {//点击ok增加一条数据
//                        return new Student(Integer.parseInt(sid.getText()), sClass.getText(), name.getText(), gender.getText(), birth.getText(), major.getText());
//                    }
//                    return null;
//                });
//
//                Optional<Student> results = dialog.showAndWait();
//                results.ifPresent(student1 -> studentService.updateStudent(student1));
//                alert("成功提示", "成功修改学号为【" + student.getSid() + "】的学生数据！", null, Alert.AlertType.INFORMATION);
//            } else {
//                alert("错误提示", "没有该学生的记录，无法修改！", null, Alert.AlertType.ERROR);
//            }
//        }
//    }
//
//    /**
//     * 删除学生
//     */
//    public void deleteStudent() {
//        TextInputDialog textInputDialog = new TextInputDialog();
//        textInputDialog.setTitle("删除学生");
//        textInputDialog.setHeaderText("请输入要删除的学生学号：");
//        textInputDialog.setContentText("学号:");
//        Optional<String> result = textInputDialog.showAndWait();
//        if (result.isPresent()) {
//            if (checkIdIllegal(result.get())) {//不合法
//                return;
//            }
//            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
//            if (student != null) {
//                studentService.deleteStudentBy(student.getSid());
//                alert("成功提示", "成功删除学号为【" + student.getSid() + "】的学生数据！", null, Alert.AlertType.INFORMATION);
//            } else {
//                alert("错误提示", "没有该学生的记录，无法删除！", null, Alert.AlertType.ERROR);
//            }
//        }
//    }
//
//    /**
//     * 查询学生信息
//     */
//    public void selectStudent() {
//        TextInputDialog textInputDialog = new TextInputDialog();
//        textInputDialog.setTitle("查询学生");
//        textInputDialog.setHeaderText("请输入要查询的学生学号:");
//        textInputDialog.setContentText("学号:");
//        Optional<String> result = textInputDialog.showAndWait();
//        if (result.isPresent()) {
//            if (checkIdIllegal(result.get())) {
//                return;
//            }
//            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
//            if (student != null) {
//                Dialog<Student> dialog = new Dialog<>();
//                dialog.setTitle("学生数据");
//                dialog.setHeaderText(null);
//
//                DialogPane dialogPane = dialog.getDialogPane();
//                dialogPane.getButtonTypes().add(ButtonType.OK);
//
//                GridPane grid = new GridPane();
//                grid.setHgap(10);
//                grid.setVgap(10);
//                grid.setPadding(new Insets(20,60,10,10));
//
//                TextField sid = new TextField(student.getSid().toString());
//                sid.setEditable(false);
//                TextField sClass = new TextField(student.getSClass());
//                sClass.setEditable(false);
//                TextField name = new TextField(student.getName());
//                name.setEditable(false);
//                TextField gender = new TextField(student.getGender());
//                gender.setEditable(false);
//                TextField birth = new TextField(student.getBirth());
//                birth.setEditable(false);
//                TextField major = new TextField(student.getMajor());
//                major.setEditable(false);
//
//                grid.add(new Label("学号:"), 0, 0);
//                grid.add(sid, 1, 0);
//                grid.add(new Label("班级:"), 0, 1);
//                grid.add(sClass, 1, 1);
//                grid.add(new Label("姓名:"), 0, 2);
//                grid.add(name, 1, 2);
//                grid.add(new Label("性别:"), 0, 3);
//                grid.add(gender, 1, 3);
//                grid.add(new Label("出生日期:"), 0, 4);
//                grid.add(birth, 1, 4);
//                grid.add(new Label("所在专业:"), 0, 5);
//                grid.add(major, 1, 5);
//
//                dialog.getDialogPane().setContent(grid);
//                dialog.showAndWait();
//            } else {
//                alert("错误提示","没有该学生的记录，无法查询！",null, Alert.AlertType.ERROR);
//            }
//        }
//    }
//
//    /**
//     * 添加课程
//     */
//    public void addCourse() {
//        Dialog<Course> dialog = new Dialog<>();
//        dialog.setTitle("添加课程");
//        dialog.setHeaderText("请输入需要添加的课程信息:");
//        DialogPane dialogPane = dialog.getDialogPane();
//        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20,60,10,10));
//
//        TextField cid = new TextField();
//        TextField major = new TextField();
//        TextField courseName = new TextField();
//        TextField type = new TextField();
//        TextField startTerm = new TextField();
//        TextField period = new TextField();
//        TextField credit = new TextField();
//
//        grid.add(new Label("课程号:"), 0, 0);
//        grid.add(cid, 1, 0);
//        grid.add(new Label("所属专业:"), 0, 1);
//        grid.add(major, 1, 1);
//        grid.add(new Label("课程名称:"), 0, 2);
//        grid.add(courseName, 1, 2);
//        grid.add(new Label("课程类型:"), 0, 3);
//        grid.add(type, 1, 3);
//        grid.add(new Label("开课学期:"), 0, 4);
//        grid.add(startTerm, 1, 4);
//        grid.add(new Label("学时数:"), 0, 5);
//        grid.add(period, 1, 5);
//        grid.add(new Label("学分:"), 0, 6);
//        grid.add(credit, 1, 6);
//
//        dialog.getDialogPane().setContent(grid);
//        dialog.setResultConverter(button -> {
//            if(button == ButtonType.OK) {
//                if (checkIsEmpty(grid)) {
//                    return null;
//                }
//                return new Course(Integer.parseInt(cid.getText()), major.getText(), courseName.getText(),
//                        type.getText(), startTerm.getText(), period.getText(), Double.parseDouble(credit.getText()));
//            }
//            return null;
//        });
//        Optional<Course> results = dialog.showAndWait();
//        results.ifPresent(course -> {
//            Course courseById = courseService.getCourseById(course.getCid());
//            if (courseById != null) {
//                alert("错误提示","课程号为【" + course.getCid() + "】的数据已经存在，无法添加！",null, Alert.AlertType.ERROR);
//            } else {
//                courseService.saveCourse(course);
//                alert("成功提示","成功保存课程号为【" + course.getCid() + "】的课程数据！",null, Alert.AlertType.INFORMATION);
//            }
//        });
//    }
//
//    public void updateCourse() {
//        TextInputDialog textInputDialog = new TextInputDialog();
//        textInputDialog.setTitle("修改课程");
//        textInputDialog.setHeaderText("请输入需要修改的课程的课程号:");
//        textInputDialog.setContentText("课程号:");
//        Optional<String> result = textInputDialog.showAndWait();
//        if (result.isPresent()) {
//            if (checkIdIllegal(result.get())) {
//                return;
//            }
//            Course courseById = courseService.getCourseById(Integer.parseInt(result.get()));
//            if (courseById != null) {
//                Dialog<Course> dialog = new Dialog<>();
//                dialog.setTitle("课程数据");
//                dialog.setHeaderText(null);
//
//                DialogPane dialogPane = dialog.getDialogPane();
//                dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//                GridPane grid = new GridPane();
//                grid.setHgap(10);
//                grid.setVgap(10);
//                grid.setPadding(new Insets(20,60,10,10));
//
//                TextField cid = new TextField(courseById.getCid().toString());
//                TextField major = new TextField(courseById.getMajor());
//                TextField courseName = new TextField(courseById.getCourseName());
//                TextField type = new TextField(courseById.getType());
//                TextField startTerm = new TextField(courseById.getStartTerm());
//                TextField period = new TextField(courseById.getPeriod());
//                TextField credit = new TextField(courseById.getCredit().toString());
//
//                grid.add(new Label("课程号:"), 0, 0);
//                grid.add(cid, 1, 0);
//                grid.add(new Label("所属专业:"), 0, 1);
//                grid.add(major, 1, 1);
//                grid.add(new Label("课程名称:"), 0, 2);
//                grid.add(courseName, 1, 2);
//                grid.add(new Label("课程类型:"), 0, 3);
//                grid.add(type, 1, 3);
//                grid.add(new Label("开课学期:"), 0, 4);
//                grid.add(startTerm, 1, 4);
//                grid.add(new Label("学时数:"), 0, 5);
//                grid.add(period, 1, 5);
//                grid.add(new Label("学分:"), 0, 6);
//                grid.add(credit, 1, 6);
//
//                dialog.getDialogPane().setContent(grid);
//                Optional<Course> results = dialog.showAndWait();
//
//                if(results.isPresent()){
//                    courseById = new Course(Integer.parseInt(cid.getText()),major.getText(), courseName.getText(),type.getText(),
//                            startTerm.getText(),period.getText(), Double.parseDouble(credit.getText()));
//
//                    courseService.updateCourse(courseById);
//                    alert("成功提示","成功修改课程号为【" + courseById.getCid() + "】的课程数据！",null, Alert.AlertType.INFORMATION);
//                }
//            } else {
//                alert("错误提示","没有该课程的记录，无法修改！",null, Alert.AlertType.ERROR);
//            }
//        }
//    }
//
//    /**
//     * 删除课程
//     */
//    public void deleteCourse() {
//        TextInputDialog textInputDialog = new TextInputDialog();
//        textInputDialog.setTitle("删除课程");
//        textInputDialog.setHeaderText("请输入需要删除的课程的课程号:");
//        textInputDialog.setContentText("课程号:");
//        Optional<String> result = textInputDialog.showAndWait();
//        if (result.isPresent()) {
//            if (checkIdIllegal(result.get())) {
//                return;
//            }
//            Course courseById = courseService.getCourseById(Integer.parseInt(result.get()));
//            if (courseById != null) {
//                courseService.deleteCourse(courseById.getCid());
//                alert("成功提示","成功删除课程号为【" + courseById.getCid() + "】的课程数据！",null, Alert.AlertType.INFORMATION);
//            } else {
//                alert("错误提示","没有该课程的记录，无法删除！",null, Alert.AlertType.ERROR);
//            }
//        }
//    }
//
//    /**
//     * 添加成绩
//     */
//    public void addScore() {
//        Dialog<Score> dialog = new Dialog<>();
//        dialog.setTitle("添加成绩");
//        dialog.setHeaderText("请输入对应学号，课程号和成绩");
//        DialogPane dialogPane = dialog.getDialogPane();
//        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20,60,10,10));
//
//        TextField sid = new TextField();
//        TextField courseId = new TextField();
//        TextField score = new TextField();
//
//        grid.add(new Label("学号:"), 0, 0);
//        grid.add(sid, 1, 0);
//        grid.add(new Label("课程号:"), 0, 1);
//        grid.add(courseId, 1, 1);
//        grid.add(new Label("成绩:"), 0, 2);
//        grid.add(score, 1, 2);
//
//        dialog.getDialogPane().setContent(grid);
//
//        dialog.setResultConverter((ButtonType button) -> {
//            if (button == ButtonType.OK) {
//                if (checkIsEmpty(grid)) {
//                    return null;
//                }
//                return new Score(Integer.parseInt(sid.getText()), Integer.parseInt(courseId.getText()), Double.parseDouble(score.getText()));
//            }
//            return null;
//        });
//
//        Optional<Score> results = dialog.showAndWait();
//        results.ifPresent(score1 -> {
//            //判断该记录是否存在
//            Score scoreBySidAndCid = scoreService.getScoreBySidAndCid(score1.getSid(), score1.getCourseId());
//            if (scoreBySidAndCid != null) {
//                alert("失败提示","该学生的这门课已有成绩，无法添加！",null, Alert.AlertType.ERROR);
//                return;
//            }
//            //判断课程是否存在
//            Course courseById = courseService.getCourseById(score1.getCourseId());
//            if (courseById == null) {
//                alert("失败提示", "该门课不存在，无法添加！", null, Alert.AlertType.ERROR);
//                return;
//            }
//            score1.setCredit(courseById.getCredit());
//            scoreService.saveScore(score1);
//            alert("成功提示","成功保存此门成绩！",null, Alert.AlertType.INFORMATION);
//        });
//    }
//
//    /**
//     * 修改成绩
//     */
//    public void updateScore() {
//        Dialog<Score> dialog = new Dialog<>();
//        dialog.setTitle("修改成绩");
//        dialog.setHeaderText("请输入对应学号、课程号和成绩：");
//
//        DialogPane dialogPane = dialog.getDialogPane();
//        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20,60,10,10));
//
//        TextField sid = new TextField();
//        TextField cid = new TextField();
//        TextField score = new TextField();
//
//        grid.add(new Label("学号:"), 0, 0);
//        grid.add(sid, 1, 0);
//        grid.add(new Label("课程号:"), 0, 1);
//        grid.add(cid, 1, 1);
//        grid.add(new Label("成绩:"), 0, 2);
//        grid.add(score, 1, 2);
//
//        dialog.getDialogPane().setContent(grid);
//
//        dialog.setResultConverter((ButtonType button) -> {
//            if (button == ButtonType.OK) {
//                if (checkIsEmpty(grid)) {
//                    return null;
//                }
//                return new Score(Integer.parseInt(sid.getText()), Integer.parseInt(cid.getText()), Double.parseDouble(score.getText()));
//            }
//            return null;
//        });
//
//        Optional<Score> results = dialog.showAndWait();
//        results.ifPresent(score1 -> {
//            //判断是否有该条记录
//            Score scoreBySidAndCid = scoreService.getScoreBySidAndCid(score1.getSid(), score1.getCourseId());
//            if (scoreBySidAndCid == null) {
//                alert("失败提示","成绩不存在，无法修改！",null, Alert.AlertType.ERROR);
//            } else {
//                scoreBySidAndCid.setScore(score1.getScore());
//                scoreService.updateScore(scoreBySidAndCid);
//                alert("成功提示","成功修改成绩！",null, Alert.AlertType.INFORMATION);
//            }
//
//        });
//    }
//
//    /**
//     * 删除成绩
//     */
//    public void deleteScore() {
//        Dialog<Score> dialog = new Dialog<>();
//        dialog.setTitle("删除成绩");
//        dialog.setHeaderText("请输入要对应的学号，课程号：");
//
//        DialogPane dialogPane = dialog.getDialogPane();
//        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20,60,10,10));
//
//        TextField sid = new TextField();
//        TextField cid = new TextField();
//
//        grid.add(new Label("学号:"), 0, 0);
//        grid.add(sid, 1, 0);
//        grid.add(new Label("课程号:"), 0, 1);
//        grid.add(cid, 1, 1);
//
//        dialog.getDialogPane().setContent(grid);
//
//        dialog.setResultConverter((ButtonType button) -> {
//            if (button == ButtonType.OK) {
//                if (checkIsEmpty(grid)) {
//                    return null;
//                }
//                Score scoreBySidAndCid = scoreService.getScoreBySidAndCid(Integer.parseInt(sid.getText()), Integer.parseInt(cid.getText()));
//                if (scoreBySidAndCid == null) {
//                    alert("失败提示","没有这门课，无法删除！",null, Alert.AlertType.ERROR);
//                } else {
//                    scoreService.deleteScore(scoreBySidAndCid.getSid(), scoreBySidAndCid.getCourseId());
//                    alert("成功提示","成功删除这门成绩！",null, Alert.AlertType.INFORMATION);
//                }
//            }
//            return null;
//        });
//        dialog.showAndWait();
//    }
//
//    /**
//     * 弹框
//     */
//    private void alert(String title, String content, String header, Alert.AlertType type) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(header);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//
//    /**
//     * 检测ID合法
//     */
//    private boolean checkIdIllegal(String sid) {
//        if (sid.length() >= 10 || "".equals(sid)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("输入的数据不合法！");
//            alert.showAndWait();
//            return true;
//        }
//        return false;
//    }
//
//    private boolean checkIsEmpty(Pane pane) {
//        for (int i = 0; i < pane.getChildren().size(); i++) {
//            if (pane.getChildren().get(i) instanceof TextField) {
//                TextField textField = (TextField) pane.getChildren().get(i);
//                if ("".equals(textField.getText())) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error");
//                    alert.setHeaderText(null);
//                    alert.setContentText("输入的数据不合法！");
//                    alert.showAndWait();
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}
