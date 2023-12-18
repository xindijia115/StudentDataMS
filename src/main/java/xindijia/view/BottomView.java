package xindijia.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import xindijia.controller.TotalController;

/**
 * @author xia
 * @since 2023/12/17/13:11
 */
public class BottomView {
    private static TotalController totalController = new TotalController();
    public static HBox bottom() {
        //底部
        HBox hBox = new HBox(10);
        Button count = new Button("统计学分");
        count.setFont(Font.font(16));
        count.setOnAction(event -> {
            totalController.statistic();
        });

        Button analysis = new Button("分析成绩");
        analysis.setFont(Font.font(16));
        analysis.setOnAction(event -> {
            totalController.analysis();
        });

        hBox.setPadding(new Insets(10, 10, 20, 10));
        hBox.getChildren().addAll(count, analysis);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        return hBox;
    }
}
