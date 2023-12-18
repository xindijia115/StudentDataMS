package xindijia.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * @author xia
 * @since 2023/12/17/12:58
 */
public class BaseController {
    /**
     * 弹框
     */
    public void alert(String title, String content, String header, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * 检测ID合法
     */
    public boolean checkIdIllegal(String sid) {
        try {
            Integer.parseInt(sid);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("输入的数据不合法！");
            alert.showAndWait();
            return true;
        }
        if (sid.length() >= 10 || "".equals(sid)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("输入的数据不合法！");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public boolean checkIsEmpty(Pane pane) {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            if (pane.getChildren().get(i) instanceof TextField) {
                TextField textField = (TextField) pane.getChildren().get(i);
                if ("".equals(textField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("输入的数据不合法！");
                    alert.showAndWait();
                    return true;
                }
            }
        }
        return false;
    }
}
