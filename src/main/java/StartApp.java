import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import xindijia.controller.LoginController;

/**
 * @author xia
 * @since 2023/12/17/13:41
 */
public class StartApp extends Application {

    private LoginController loginController = new LoginController();
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(20);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 60, 20, 90));

        TextField number = new TextField();
        PasswordField password = new PasswordField();


        grid.add(new Label("账号:"), 0, 0);
        grid.add(number, 1, 0);
        grid.add(new Label("密码:"), 0, 1);
        grid.add(password, 1, 1);

        Text text = new Text("欢迎使用学生管理里系统");
        text.setFill(Color.ROSYBROWN);
        text.setFont(Font.font("宋体", FontWeight.BOLD, FontPosture.ITALIC, 36));

        Button login = new Button("登录");
        login.setOnAction(event -> {
            loginController.login(number.getText(), password.getText());
            try {
                new Main().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text, grid, login);


        HBox hBox = new HBox(10);
        hBox.getChildren().add(vBox);
        hBox.setAlignment(Pos.CENTER);


        Scene scene = new Scene(hBox, 900, 600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
