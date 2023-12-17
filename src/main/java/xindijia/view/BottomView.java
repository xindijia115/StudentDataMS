package xindijia.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * @author xia
 * @since 2023/12/17/13:11
 */
public class BottomView {
    public static HBox bottom() {
        //底部
        HBox hBox = new HBox(10);
        Button count = new Button("统计学分");
        count.setOnAction(event -> {

        });

        Button analysis = new Button("分析成绩");
        analysis.setOnAction(event -> {

        });

        hBox.setPadding(new Insets(10, 10, 20, 10));
        hBox.getChildren().addAll(count, analysis);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        return hBox;
    }
}
