package xindijia.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author xia
 * @since 2023/12/17/13:07
 */
public class TopView {
    public static HBox top() {
        //顶部
        Text text = new Text("学生管理系统");
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setFont(Font.font(48));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(text);
        return hBox;
    }
}
