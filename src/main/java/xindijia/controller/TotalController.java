package xindijia.controller;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import xindijia.entity.Course;
import xindijia.entity.Score;
import xindijia.entity.Student;
import xindijia.service.CourseService;
import xindijia.service.ScoreService;
import xindijia.service.StudentService;
import xindijia.service.impl.CourseServiceImpl;
import xindijia.service.impl.ScoreServiceImpl;
import xindijia.service.impl.StudentServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 统计分析功能
 * @author xia
 * @since 2023/12/18/16:05
 */
public class TotalController extends BaseController{

    private StudentService studentService = new StudentServiceImpl();

    private ScoreService scoreService = new ScoreServiceImpl();

    private CourseService courseService = new CourseServiceImpl();
    /**
     * 统计学生的总分
     */
    public void statistic() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("统计学分");
        textInputDialog.setHeaderText("请输入需要统计学生的学号:");
        textInputDialog.setContentText("学号:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            if ("".equals(result.get())) {
                alert("错误提示","学号不可为空!",null, Alert.AlertType.ERROR);
                return;
            }
            Student student = studentService.getStudentById(Integer.parseInt(result.get()));
            if (student == null) {
                alert("错误提示","没有该学生的记录，无法统计！",null, Alert.AlertType.ERROR);
            } else {
                alert("学分统计",result.get() + student.getName() + "的总学分为:" + String.format("%.4s",scoreService.getTotalCreditById(student.getSid())),
                        null, Alert.AlertType.INFORMATION);
            }
        }
    }

    /**
     * 成绩分析功能
     */
    public void analysis() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("成绩分析");
        dialog.setHeaderText("请输入要分析的班级和课程:");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,60,10,10));

        TextField clazz = new TextField();
        TextField course = new TextField();

        grid.add(new Label("班级:"), 0, 0);
        grid.add(clazz, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(course, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                if ("".equals(clazz.getText()) || "".equals(course.getText())) {
                    alert("错误提示","数据错误!!",null, Alert.AlertType.ERROR);
                    return null;
                }
                return new Pair<>(clazz.getText(), course.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> results = dialog.showAndWait();
        results.ifPresent(stringStringPair -> {
            String clazzId = stringStringPair.getKey();
            String courseId = stringStringPair.getValue();
            Course courseById = courseService.getCourseById(Integer.parseInt(courseId));
            if (courseById == null) {
                alert("错误提示","没有该课程!",null, Alert.AlertType.ERROR);
            } else {
                //设置一个弹窗
                Dialog<Object> objectDialog = new Dialog<>();
                objectDialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

                //设置x，y坐标轴
                CategoryAxis x = new CategoryAxis();
                x.setLabel("成绩分布");
                String[] s = {"0~60", "60~80", "80~90", "90~99", "100"};
                x.setCategories(FXCollections.observableArrayList(Arrays.asList(s)));

                NumberAxis y = new NumberAxis();
                y.setLabel("人数");

                //设置坐标轴对象
                StackedBarChart<String, Number> barChart = new StackedBarChart<String, Number>(x, y);
                barChart.setTitle("成绩直方图");
                //x，y坐标轴数据对象
                Integer v1 = scoreService.countByCategoryScore(0, 60, Integer.parseInt(clazzId), Integer.parseInt(courseId));
                Integer v2 = scoreService.countByCategoryScore(60, 80, Integer.parseInt(clazzId), Integer.parseInt(courseId));
                Integer v3 = scoreService.countByCategoryScore(80, 90, Integer.parseInt(clazzId), Integer.parseInt(courseId));
                Integer v4 = scoreService.countByCategoryScore(90, 100, Integer.parseInt(clazzId), Integer.parseInt(courseId));
                Integer v5 = scoreService.countByCategoryScore(100, 101, Integer.parseInt(clazzId), Integer.parseInt(courseId));
                XYChart.Series<String, Number> stringNumberSeries = new XYChart.Series<>();
                stringNumberSeries.setName(courseById.getCourseName());
                stringNumberSeries.getData().add(new XYChart.Data<>("0~60", v1));
                stringNumberSeries.getData().add(new XYChart.Data<>("60~80", v2));
                stringNumberSeries.getData().add(new XYChart.Data<>("80~90", v3));
                stringNumberSeries.getData().add(new XYChart.Data<>("90~99", v4));
                stringNumberSeries.getData().add(new XYChart.Data<>("100", v5));

                barChart.getData().add(stringNumberSeries);
                //barChart.setLegendVisible(false);
                objectDialog.getDialogPane().setContent(barChart);
                objectDialog.showAndWait();
            }
        });
    }
}
