//package xindijia.view;
//
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.StackPane;
//import xindijia.entity.Course;
//import xindijia.entity.Score;
//import xindijia.entity.Student;
//
///**
// * @author xia
// * @since 2023/12/17/13:26
// */
//public class RightView {
//    public static StackPane right() {
//        //右侧
//        StackPane stackPane = new StackPane();
//        //学生视图
//        TableView<Student> studentTableView = new TableView<>();
//
//        TableColumn<Student, Integer> number = new TableColumn<>("学号");
//        number.setPrefWidth(125);
//        number.setEditable(false);
//        number.setCellValueFactory(new PropertyValueFactory<>("sid"));
//
//        TableColumn<Student, String> clazz = new TableColumn<>("班级");
//        clazz.setPrefWidth(125);
//        clazz.setEditable(false);
//        clazz.setCellValueFactory(new PropertyValueFactory<>("sClass"));
//
//        TableColumn<Student, String> name = new TableColumn<>("姓名");
//        name.setPrefWidth(125);
//        name.setEditable(false);
//        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        TableColumn<Student, String> gender = new TableColumn<>("性别");
//        gender.setPrefWidth(125);
//        gender.setEditable(false);
//        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
//
//        TableColumn<Student, String> birth = new TableColumn<>("出生日期");
//        birth.setPrefWidth(125);
//        birth.setEditable(false);
//        birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
//
//        TableColumn<Student, String> major = new TableColumn<>("专业");
//        major.setPrefWidth(125);
//        major.setEditable(false);
//        major.setCellValueFactory(new PropertyValueFactory<>("major"));
//
//        studentTableView.getColumns().addAll(number, clazz, name, gender, birth, major);
////        Student student = new Student();
////        student.setSid(202201);
////        student.setName("xindijia");
////        student.setMajor("信息学院");
////        student.setGender("男");
////        student.setBirth("2003-1-15");
////        student.setSClass("软件工程2202");
////        ObservableList<Student> students = FXCollections.observableArrayList(student);
////        studentTableView.setItems(students);
//
//        //课程视图
//        TableView<Course> courseTableView = new TableView<>();
//
//        TableColumn<Course, Integer> courseId = new TableColumn<>("课程号");
//        courseId.setPrefWidth(100);
//        courseId.setEditable(false);
//        courseId.setCellValueFactory(new PropertyValueFactory<>("cid"));
//
//        TableColumn<Course, String> majorColumn = new TableColumn<>("所属专业");
//        majorColumn.setPrefWidth(100);
//        majorColumn.setEditable(false);
//        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
//
//        TableColumn<Course, String> courseName = new TableColumn<>("课程名称");
//        courseName.setPrefWidth(100);
//        courseName.setEditable(false);
//        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
//
//        TableColumn<Course, String> type = new TableColumn<>("课程类型");
//        type.setPrefWidth(100);
//        type.setEditable(false);
//        type.setCellValueFactory(new PropertyValueFactory<>("type"));
//
//        TableColumn<Course, String> startTerm = new TableColumn<>("开课学期");
//        startTerm.setPrefWidth(100);
//        startTerm.setEditable(false);
//        startTerm.setCellValueFactory(new PropertyValueFactory<>("startTerm"));
//
//        TableColumn<Course, String> period = new TableColumn<>("学时数");
//        period.setPrefWidth(100);
//        period.setEditable(false);
//        period.setCellValueFactory(new PropertyValueFactory<>("period"));
//
//        TableColumn<Course, Double> credit = new TableColumn<>("学分");
//        credit.setPrefWidth(100);
//        credit.setEditable(false);
//        credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
//
//        courseTableView.getColumns().addAll(courseId, majorColumn, courseName, type, startTerm, period, credit);
//
//        //成绩视图
//        TableView<Score> scoreTableView = new TableView<>();
//
//        TableColumn<Score, Integer> sid = new TableColumn<>("学号");
//        sid.setPrefWidth(150);
//        sid.setEditable(false);
//        sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
//
//        TableColumn<Score, Integer> courseIdColumn = new TableColumn<>("课程号");
//        courseIdColumn.setPrefWidth(150);
//        courseIdColumn.setEditable(false);
//        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
//
//        TableColumn<Score, Double> score = new TableColumn<>("成绩");
//        score.setPrefWidth(150);
//        score.setEditable(false);
//        score.setCellValueFactory(new PropertyValueFactory<>("score"));
//
//        TableColumn<Score, Double> creditColumn = new TableColumn<>("学分");
//        creditColumn.setPrefWidth(150);
//        creditColumn.setEditable(false);
//        creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
//        scoreTableView.getColumns().addAll(sid, courseIdColumn, score, creditColumn);
//        return stackPane;
//    }
//}
