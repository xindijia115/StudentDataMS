import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
        VBox vBox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 80, 10, 90));

        TextField number = new TextField();
        PasswordField password = new PasswordField();


        Text t1 = new Text("账号:");
        t1.setFont(Font.font("宋体", FontWeight.BOLD, FontPosture.ITALIC, 18));
        t1.setStroke(Color.BLACK);
        t1.setFill(Color.WHITE);
        Text t2 = new Text("密码:");
        t2.setFont(Font.font("宋体", FontWeight.BOLD, FontPosture.ITALIC, 18));
        t2.setStroke(Color.BLACK);
        t2.setFill(Color.WHITE);


        grid.add(t1, 0, 0);
        grid.add(number, 1, 0);
        grid.add(t2, 0, 1);
        grid.add(password, 1, 1);

        Text text = new Text("欢迎使用学生管理系统");
        text.setStroke(Color.BLACK);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("宋体", FontWeight.BOLD, FontPosture.ITALIC, 36));

        Button login = new Button("登录");
        login.setFont(Font.font(16));
        login.setOnAction(event -> {
            try {
                if (loginController.login(number.getText(), password.getText())) {
                    new Main().start(stage);
                }
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
        //scene.setFill(Color.TRANSPARENT);
        stage.getIcons().add(new Image("file:src/main/resources/images/icon.jpg"));
        BackgroundImage backgroundImage =
                new BackgroundImage(new Image("file:src/main/resources/images/background.jpg"),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(1.0, 1.0, true, true, true, false));
        Background background = new Background(backgroundImage);
        hBox.setBackground(background);
        stage.setTitle("学生管理系统");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
