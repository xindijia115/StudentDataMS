package xindijia.controller;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import xindijia.entity.Course;
import xindijia.entity.Score;
import xindijia.service.CourseService;
import xindijia.service.ScoreService;
import xindijia.service.impl.CourseServiceImpl;
import xindijia.service.impl.ScoreServiceImpl;

import java.util.Optional;

/**
 * @author xia
 * @since 2023/12/17/13:01
 */
public class ScoreController extends BaseController{

    private ScoreService scoreService = new ScoreServiceImpl();

    private CourseService courseService = new CourseServiceImpl();

    /**
     * 添加成绩
     */
    public void addScore() {
        Dialog<Score> dialog = new Dialog<>();
        dialog.setTitle("添加成绩");
        dialog.setHeaderText("请输入对应学号，课程号和成绩");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,60,10,10));

        TextField sid = new TextField();
        TextField courseId = new TextField();
        TextField score = new TextField();

        grid.add(new Label("学号:"), 0, 0);
        grid.add(sid, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(courseId, 1, 1);
        grid.add(new Label("成绩:"), 0, 2);
        grid.add(score, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                if (checkIsEmpty(grid)) {
                    return null;
                }
                return new Score(Integer.parseInt(sid.getText()), Integer.parseInt(courseId.getText()), Double.parseDouble(score.getText()));
            }
            return null;
        });

        Optional<Score> results = dialog.showAndWait();
        results.ifPresent(score1 -> {
            //判断该记录是否存在
            Score scoreBySidAndCid = scoreService.getScoreBySidAndCid(score1.getSid(), score1.getCourseId());
            if (scoreBySidAndCid != null) {
                alert("失败提示","该学生的这门课已有成绩，无法添加！",null, Alert.AlertType.ERROR);
                return;
            }
            //判断课程是否存在
            Course courseById = courseService.getCourseById(score1.getCourseId());
            if (courseById == null) {
                alert("失败提示", "该门课不存在，无法添加！", null, Alert.AlertType.ERROR);
                return;
            }
            score1.setCredit(courseById.getCredit());
            scoreService.saveScore(score1);
            alert("成功提示","成功保存此门成绩！",null, Alert.AlertType.INFORMATION);
        });
    }

    /**
     * 修改成绩
     */
    public void updateScore() {
        Dialog<Score> dialog = new Dialog<>();
        dialog.setTitle("修改成绩");
        dialog.setHeaderText("请输入对应学号、课程号和成绩：");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,60,10,10));

        TextField sid = new TextField();
        TextField cid = new TextField();
        TextField score = new TextField();

        grid.add(new Label("学号:"), 0, 0);
        grid.add(sid, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(cid, 1, 1);
        grid.add(new Label("成绩:"), 0, 2);
        grid.add(score, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                if (checkIsEmpty(grid)) {
                    return null;
                }
                return new Score(Integer.parseInt(sid.getText()), Integer.parseInt(cid.getText()), Double.parseDouble(score.getText()));
            }
            return null;
        });

        Optional<Score> results = dialog.showAndWait();
        results.ifPresent(score1 -> {
            //判断是否有该条记录
            Score scoreBySidAndCid = scoreService.getScoreBySidAndCid(score1.getSid(), score1.getCourseId());
            if (scoreBySidAndCid == null) {
                alert("失败提示","成绩不存在，无法修改！",null, Alert.AlertType.ERROR);
            } else {
                scoreBySidAndCid.setScore(score1.getScore());
                scoreService.updateScore(scoreBySidAndCid);
                alert("成功提示","成功修改成绩！",null, Alert.AlertType.INFORMATION);
            }

        });
    }

    /**
     * 删除成绩
     */
    public void deleteScore() {
        Dialog<Score> dialog = new Dialog<>();
        dialog.setTitle("删除成绩");
        dialog.setHeaderText("请输入要对应的学号，课程号：");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,60,10,10));

        TextField sid = new TextField();
        TextField cid = new TextField();

        grid.add(new Label("学号:"), 0, 0);
        grid.add(sid, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(cid, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                if (checkIsEmpty(grid)) {
                    return null;
                }
                Score scoreBySidAndCid = scoreService.getScoreBySidAndCid(Integer.parseInt(sid.getText()), Integer.parseInt(cid.getText()));
                if (scoreBySidAndCid == null) {
                    alert("失败提示","没有这门课，无法删除！",null, Alert.AlertType.ERROR);
                } else {
                    scoreService.deleteScore(scoreBySidAndCid.getSid(), scoreBySidAndCid.getCourseId());
                    alert("成功提示","成功删除这门成绩！",null, Alert.AlertType.INFORMATION);
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
}
