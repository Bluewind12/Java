//1601776 2EP4-58 MomoseNaoto

package FiveTest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {
    private static Circle[] circles = { new Circle(25), new Circle(25), new Circle(25), new Circle(25),
            new Circle(25) };
    private static Circle[] food_circles = { new Circle(15), new Circle(15), new Circle(15), new Circle(15),
            new Circle(15) };
    private static Rectangle[] fork_Rectangles = { new Rectangle(15, 15), new Rectangle(15, 15), new Rectangle(15, 15),
            new Rectangle(15, 15), new Rectangle(15, 15) };
    private static Text[] name_texts = { new Text(""), new Text(""), new Text(""), new Text(""), new Text("") };
    private static Text[] foodCountTexts = { new Text("�c��:100%"), new Text("�c��:100%"), new Text("�c��:100%"),
            new Text("�c��:100%"), new Text("�c��:100%") };
    private static Text[] state_texts = { new Text(":             "), new Text(":             "),
            new Text(":             "), new Text(":             "), new Text(":             ") };
    private static Slider timeslider = new Slider(1, 5, 2);
    private static Slider foodslider = new Slider(0, 25, 5);

    private static Button Start_button = new Button("Start");

    public void start(Stage stage) throws Exception {
        stage.setTitle("Five philosophers");
        stage.setWidth(750);
        stage.setHeight(500);

        VBox textroot = new VBox(10);
        VBox stateroot = new VBox(10);
        Group drowroot = new Group();
        HBox mainroot = new HBox(30);

        Text[] desText = { new Text("==========="), new Text("�E�N�w�ҁF�傫����"), new Text("�ԐF�F�v�l��"), new Text("�F�F�t�H�[�N�P�{����"),
                new Text("�ΐF�F�H����"), new Text("�D�F�F�H���I��"), new Text("==========="), new Text("�E�t�H�[�N:�l�p"),
                new Text("�ԐF�F������Ă���"), new Text("�F�F�u����Ă���"), new Text("==========="), new Text("�E�H��:���̏�̊�"),
                new Text("���F�H���̎c��") };
        for (int i = 0; i < desText.length; i++) {
            textroot.getChildren().add(desText[i]);
        }
        Start_button.setPrefSize(100, 50);
        Start_button.setOnAction(event -> Start_button());
        textroot.getChildren().add(Start_button);
        Button End_button = new Button("End");
        End_button.setPrefSize(100, 50);
        End_button.setOnAction(event -> System.exit(0));
        textroot.getChildren().add(End_button);

        //�X���C�_�[
        stateroot.getChildren().add(new Text("�ҋ@����(s)"));
        timeslider.setShowTickLabels(true);
        timeslider.setShowTickMarks(true);
        timeslider.setMajorTickUnit(1);
        timeslider.setMinorTickCount(1);
        timeslider.setSnapToTicks(true);
        stateroot.getChildren().add(timeslider);

        stateroot.getChildren().add(new Text("=========="));
        stateroot.getChildren().add(new Text("�H����"));
        foodslider.setShowTickLabels(true);
        foodslider.setShowTickMarks(true);
        foodslider.setMajorTickUnit(5);
        foodslider.setMinorTickCount(4);
        foodslider.setSnapToTicks(true);
        stateroot.getChildren().add(foodslider);

        stateroot.getChildren().add(new Text("=========="));
        stateroot.getChildren().add(new Text("���݂̏��"));

        //���W�Ǘ�
        for (int i = 0; i < circles.length; i++) {
            int r = 150;
            double Delta = Math.PI / 2;
            double x = (r * Math.cos(2.0 * i * Math.PI / 5 + Delta));
            double y = -(r * Math.sin(2.0 * i * Math.PI / 5 + Delta));
            double tx = x * Math.cos(-36 * Math.PI / 180) - y * Math.sin(-36 * Math.PI / 180);
            double ty = x * Math.sin(-36 * Math.PI / 180) + y * Math.cos(-36 * Math.PI / 180);

            circles[i].setLayoutX(x);
            circles[i].setLayoutY(y);
            food_circles[i].setLayoutX(x * 0.55);
            food_circles[i].setLayoutY(y * 0.55);
            foodCountTexts[i].setLayoutX(x * 0.55 - 30);
            foodCountTexts[i].setLayoutY(y * 0.55);
            name_texts[i].setLayoutX(x);
            name_texts[i].setLayoutY(y - 30);
            fork_Rectangles[i].setLayoutX(tx * 0.55);
            fork_Rectangles[i].setLayoutY(ty * 0.55);
        }

        //�}
        for (int i = 0; i < circles.length; i++) {
            circles[i].setFill(Color.CYAN);
            drowroot.getChildren().add(circles[i]);
        }
        for (int i = 0; i < food_circles.length; i++) {
            food_circles[i].setFill(Color.ORANGE);
            food_circles[i].setStroke(Color.BLACK);
            drowroot.getChildren().add(food_circles[i]);
        }
        for (int i = 0; i < fork_Rectangles.length; i++) {
            drowroot.getChildren().add(fork_Rectangles[i]);
        }
        for (int i = 0; i < name_texts.length; i++) {
            drowroot.getChildren().add(name_texts[i]);
        }
        for (int i = 0; i < name_texts.length; i++) {
            drowroot.getChildren().add(foodCountTexts[i]);
        }
        Circle tablCircle = new Circle(115, null);
        tablCircle.setStroke(Color.BLACK);

        //�e�[�u��
        drowroot.getChildren().add(tablCircle);

        //���
        for (int i = 0; i < state_texts.length; i++) {
            stateroot.getChildren().add(state_texts[i]);
        }
        mainroot.getChildren().addAll(textroot, drowroot, stateroot);
        Scene scene = new Scene(mainroot);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void ChangeP(int id, Color color) {
        circles[id].setFill(color);
    }

    public void ChangeState(int id, String state, String name) {
        switch (state) {
        case "Stop":
            state_texts[id].setText(name + ":�E�F�C�^�[�Ɏ~�߂�ꂽ");
            break;

        case "Think":
            state_texts[id].setText(name + ":�v�l��");
            break;

        case "One":
            state_texts[id].setText(name + ":�t�H�[�N�P�{����");
            break;

        case "Eat":
            state_texts[id].setText(name + ":�H����");
            break;

        case "End":
            state_texts[id].setText(name + ":�H���I��");
            break;
        }
    }

    public void SetName(int id, String name) {
        name_texts[id].setText(name);
    }

    public Double GetSilder(String choice) {
        switch (choice) {
        case "time":
            return (timeslider.getValue() * 1000);
        case "food":
            return foodslider.getValue();
        }
        return 1000.0;
    }

    public void ChangeFork(int id, Color color) {
        fork_Rectangles[id].setFill(color);
    }

    public void ChangeFood(int id, Double eat, Double eatMax) {

        if (eatMax != 0) {
            foodCountTexts[id].setText("�c��:" + ((eatMax - eat) / eatMax) * 100 + "%");
            food_circles[id].setOpacity((eatMax - eat) / eatMax);
        } else {
            foodCountTexts[id].setText("�c��:" + 0.0 + "%");
            food_circles[id].setOpacity(0);
        }
    }

    public void Start_button() {
        Start_button.setDisable(true);
        FxTest.ThreadStart();
    }
}