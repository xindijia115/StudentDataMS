import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import xindijia.controller.CourseController;
import xindijia.controller.ScoreController;
import xindijia.controller.StudentController;
import xindijia.entity.Course;
import xindijia.entity.Score;
import xindijia.entity.Student;
import xindijia.service.CourseService;
import xindijia.service.ScoreService;
import xindijia.service.StudentService;
import xindijia.service.impl.CourseServiceImpl;
import xindijia.service.impl.ScoreServiceImpl;
import xindijia.service.impl.StudentServiceImpl;
import xindijia.view.BottomView;
import xindijia.view.TopView;

import java.util.List;

/**
 * @author xia
 * @since 2023/12/16/12:19
 */
public class Main extends Application {

    private StudentService studentService = new StudentServiceImpl();

    private CourseService courseService = new CourseServiceImpl();

    private ScoreService scoreService = new ScoreServiceImpl();

    private StudentController studentController = new StudentController();

    private ScoreController scoreController = new ScoreController();

    private CourseController courseController = new CourseController();
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();

        //顶部
        HBox top = TopView.top();
        borderPane.setTop(top);

        //底部
        HBox bottom = BottomView.bottom();
        borderPane.setBottom(bottom);

        //左侧
        TabPane tabPane = new TabPane();
        //学生栏
        Tab studentTab = new Tab("学生");
        studentTab.setClosable(false);
        VBox studentBox = new VBox(10);
        Button add = new Button("添加学生");
        add.setOnAction(event -> {
            studentController.addStudent();
        });

        Button update = new Button("修改信息");
        update.setOnAction(event -> {
            studentController.updateStudent();
        });

        Button delete = new Button("删除学生");
        delete.setOnAction(event -> {
            studentController.deleteStudent();
        });

        Button select = new Button("查询信息");
        select.setOnAction(event -> {
            studentController.selectStudent();
        });

        studentBox.getChildren().addAll(add, update, delete, select);
        studentBox.setAlignment(Pos.CENTER);
        studentBox.setPadding(new Insets(10, 10, 10, 10));
        studentTab.setContent(studentBox);
        tabPane.getTabs().add(studentTab);

        //课程栏
        Tab courseTab = new Tab("课程");
        courseTab.setClosable(false);
        VBox courseBox = new VBox(10);

        Button addCourse = new Button("添加课程");
        addCourse.setOnAction(event -> {
            courseController.addCourse();
        });
        Button updateCourse = new Button("修改课程");
        updateCourse.setOnAction(event -> {
            courseController.updateCourse();
        });
        Button deleteCourse = new Button("删除课程");
        deleteCourse.setOnAction(event -> {
            courseController.deleteCourse();
        });

        courseBox.getChildren().addAll(addCourse, updateCourse, deleteCourse);
        courseBox.setAlignment(Pos.CENTER);
        courseTab.setContent(courseBox);
        tabPane.getTabs().add(courseTab);

        //成绩栏
        Tab scoreTab = new Tab("成绩");
        scoreTab.setClosable(false);
        VBox scoreBox = new VBox(10);

        Button addScore = new Button("添加成绩");
        addScore.setOnAction(event -> {
            scoreController.addScore();
        });
        Button updateScore = new Button("修改成绩");
        updateScore.setOnAction(event -> {
            scoreController.updateScore();
        });
        Button deleteScore = new Button("删除成绩");
        deleteScore.setOnAction(event -> {
            scoreController.deleteScore();
        });
        scoreBox.getChildren().addAll(addScore, updateScore, deleteScore);
        scoreBox.setAlignment(Pos.CENTER);
        scoreTab.setContent(scoreBox);
        tabPane.getTabs().add(scoreTab);

        borderPane.setLeft(tabPane);

        //右侧
        StackPane stackPane = new StackPane();
        //学生视图
        TableView<Student> studentTableView = new TableView<>();

        TableColumn<Student, Integer> number = new TableColumn<>("学号");
        number.setPrefWidth(125);
        number.setEditable(false);
        number.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<Student, String> clazz = new TableColumn<>("班级");
        clazz.setPrefWidth(125);
        clazz.setEditable(false);
        clazz.setCellValueFactory(new PropertyValueFactory<>("sClass"));

        TableColumn<Student, String> name = new TableColumn<>("姓名");
        name.setPrefWidth(125);
        name.setEditable(false);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> gender = new TableColumn<>("性别");
        gender.setPrefWidth(125);
        gender.setEditable(false);
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, String> birth = new TableColumn<>("出生日期");
        birth.setPrefWidth(125);
        birth.setEditable(false);
        birth.setCellValueFactory(new PropertyValueFactory<>("birth"));

        TableColumn<Student, String> major = new TableColumn<>("专业");
        major.setPrefWidth(125);
        major.setEditable(false);
        major.setCellValueFactory(new PropertyValueFactory<>("major"));

        studentTableView.getColumns().addAll(number, clazz, name, gender, birth, major);


        //课程视图
        TableView<Course> courseTableView = new TableView<>();

        TableColumn<Course, Integer> courseId = new TableColumn<>("课程号");
        courseId.setPrefWidth(100);
        courseId.setEditable(false);
        courseId.setCellValueFactory(new PropertyValueFactory<>("cid"));

        TableColumn<Course, String> majorColumn = new TableColumn<>("所属专业");
        majorColumn.setPrefWidth(100);
        majorColumn.setEditable(false);
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        TableColumn<Course, String> courseName = new TableColumn<>("课程名称");
        courseName.setPrefWidth(100);
        courseName.setEditable(false);
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Course, String> type = new TableColumn<>("课程类型");
        type.setPrefWidth(100);
        type.setEditable(false);
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Course, String> startTerm = new TableColumn<>("开课学期");
        startTerm.setPrefWidth(100);
        startTerm.setEditable(false);
        startTerm.setCellValueFactory(new PropertyValueFactory<>("startTerm"));

        TableColumn<Course, String> period = new TableColumn<>("学时数");
        period.setPrefWidth(100);
        period.setEditable(false);
        period.setCellValueFactory(new PropertyValueFactory<>("period"));

        TableColumn<Course, Double> credit = new TableColumn<>("学分");
        credit.setPrefWidth(100);
        credit.setEditable(false);
        credit.setCellValueFactory(new PropertyValueFactory<>("credit"));

        courseTableView.getColumns().addAll(courseId, majorColumn, courseName, type, startTerm, period, credit);

        //成绩视图
        TableView<Score> scoreTableView = new TableView<>();

        TableColumn<Score, Integer> sid = new TableColumn<>("学号");
        sid.setPrefWidth(150);
        sid.setEditable(false);
        sid.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<Score, Integer> courseIdColumn = new TableColumn<>("课程号");
        courseIdColumn.setPrefWidth(150);
        courseIdColumn.setEditable(false);
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));

        TableColumn<Score, Double> score = new TableColumn<>("成绩");
        score.setPrefWidth(150);
        score.setEditable(false);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<Score, Double> creditColumn = new TableColumn<>("学分");
        creditColumn.setPrefWidth(150);
        creditColumn.setEditable(false);
        creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
        scoreTableView.getColumns().addAll(sid, courseIdColumn, score, creditColumn);

        borderPane.setRight(stackPane);

        //监听事件
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == studentTab) {
                if (stackPane.getChildren().contains(studentTableView)) {//移除原有的
                    stackPane.getChildren().remove(studentTableView);
                }
                //更新数据,查询数据库
                List<Student> studentList = studentService.getStudentList();
                ObservableList<Student> students = FXCollections.observableArrayList(studentList);
                studentTableView.setItems(students);
                stackPane.getChildren().add(studentTableView);
            } else if (newValue == courseTab) {
                if(stackPane.getChildren().contains(courseTableView)) {
                    stackPane.getChildren().remove(courseTableView);
                }
                List<Course> courseList = courseService.getCourseList();
                ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
                courseTableView.setItems(courses);
                stackPane.getChildren().add(courseTableView);
            } else {
                if (stackPane.getChildren().contains(scoreTableView)) {
                    stackPane.getChildren().remove(scoreTableView);
                }
                List<Score> scoreList = scoreService.getScoreList();
                ObservableList<Score> scores = FXCollections.observableArrayList(scoreList);
                scoreTableView.setItems(scores);
                stackPane.getChildren().add(scoreTableView);
            }
        });

        if (stackPane.getChildren().contains(studentTableView)) {//移除原有的
            stackPane.getChildren().remove(studentTableView);
        }
        List<Student> studentList = studentService.getStudentList();
        ObservableList<Student> students = FXCollections.observableArrayList(studentList);
        studentTableView.setItems(students);
        stackPane.getChildren().add(studentTableView);


        Scene scene = new Scene(borderPane, 900, 600);
        stage.setScene(scene);
        stage.setTitle("学生管理系统");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
