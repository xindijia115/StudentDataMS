//package xindijia.view;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
//import javafx.scene.layout.VBox;
//import xindijia.controller.CourseController;
//import xindijia.controller.ScoreController;
//import xindijia.controller.StudentController;
//
///**
// * @author xia
// * @since 2023/12/17/13:15
// */
//public class LeftView {
//    private static StudentController studentController = new StudentController();
//
//    private static ScoreController scoreController = new ScoreController();
//
//    private static CourseController courseController = new CourseController();
//    public static TabPane left() {
//        //左侧
//        TabPane tabPane = new TabPane();
//        //学生栏
//        Tab studentTab = new Tab("学生");
//        studentTab.setClosable(false);
//        VBox studentBox = new VBox(10);
//        Button add = new Button("添加学生");
//        add.setOnAction(event -> {
//            studentController.addStudent();
//        });
//
//        Button update = new Button("修改信息");
//        update.setOnAction(event -> {
//            studentController.updateStudent();
//        });
//
//        Button delete = new Button("删除学生");
//        delete.setOnAction(event -> {
//            studentController.deleteStudent();
//        });
//
//        Button select = new Button("查询信息");
//        select.setOnAction(event -> {
//            studentController.selectStudent();
//        });
//
//        studentBox.getChildren().addAll(add, update, delete, select);
//        studentBox.setAlignment(Pos.CENTER);
//        studentBox.setPadding(new Insets(10, 10, 10, 10));
//        studentTab.setContent(studentBox);
//        tabPane.getTabs().add(studentTab);
//
//        //课程栏
//        Tab courseTab = new Tab("课程");
//        courseTab.setClosable(false);
//        VBox courseBox = new VBox(10);
//
//        Button addCourse = new Button("添加课程");
//        addCourse.setOnAction(event -> {
//            courseController.addCourse();
//        });
//        Button updateCourse = new Button("修改课程");
//        updateCourse.setOnAction(event -> {
//            courseController.updateCourse();
//        });
//        Button deleteCourse = new Button("删除课程");
//        deleteCourse.setOnAction(event -> {
//            courseController.deleteCourse();
//        });
//
//        courseBox.getChildren().addAll(addCourse, updateCourse, deleteCourse);
//        courseBox.setAlignment(Pos.CENTER);
//        courseTab.setContent(courseBox);
//        tabPane.getTabs().add(courseTab);
//
//        //成绩栏
//        Tab scoreTab = new Tab("成绩");
//        scoreTab.setClosable(false);
//        VBox scoreBox = new VBox(10);
//
//        Button addScore = new Button("添加成绩");
//        addScore.setOnAction(event -> {
//            scoreController.addScore();
//        });
//        Button updateScore = new Button("修改成绩");
//        updateScore.setOnAction(event -> {
//            scoreController.updateScore();
//        });
//        Button deleteScore = new Button("删除成绩");
//        deleteScore.setOnAction(event -> {
//            scoreController.deleteScore();
//        });
//        scoreBox.getChildren().addAll(addScore, updateScore, deleteScore);
//        scoreBox.setAlignment(Pos.CENTER);
//        scoreTab.setContent(scoreBox);
//        tabPane.getTabs().add(scoreTab);
//        return tabPane;
//    }
//}
