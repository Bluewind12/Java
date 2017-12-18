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

/**
 * GUIの設定,変更を行う
 */
public class GUI extends Application {

    //哲学者(円)
    private static Circle[] circles = { new Circle(25), new Circle(25), new Circle(25), new Circle(25),
            new Circle(25) };

    //食事(円)
    private static Circle[] food_circles = { new Circle(15), new Circle(15), new Circle(15), new Circle(15),
            new Circle(15) };

    //フォーク(四角)
    private static Rectangle[] fork_Rectangles = { new Rectangle(15, 15), new Rectangle(15, 15), new Rectangle(15, 15),
            new Rectangle(15, 15), new Rectangle(15, 15) };

    //哲学者の上に表記される名前
    private static Text[] name_texts = { new Text(""), new Text(""), new Text(""), new Text(""), new Text("") };

    //食事の残りパーセント
    private static Text[] foodCountTexts = { new Text("残り:100%"), new Text("残り:100%"), new Text("残り:100%"),
            new Text("残り:100%"), new Text("残り:100%") };

    //状態表示
    private static Text[] state_texts = { new Text(":             "), new Text(":             "),
            new Text(":             "), new Text(":             "), new Text(":             ") };

    //スライダー(時間設定)
    private static Slider timeslider = new Slider(1, 5, 2);
    //スライダー(食事回数設定)
    private static Slider foodslider = new Slider(0, 25, 5);

    //スタートボタン
    private static Button Start_button = new Button("Start");

    /**
     * GUI表示のための設定を行うメソッド。
     * 座標の計算、色の設定などを行う。
     */
    public void start(Stage stage) throws Exception {
        //ステージの設定(タイトル,サイズ)
        stage.setTitle("Five philosophers");
        stage.setWidth(750);
        stage.setHeight(500);

        //rootの作成
        VBox textroot = new VBox(10); //説明を設置用
        VBox stateroot = new VBox(10); //スライダー,状態説明設置用
        Group drowroot = new Group(); //図を設置用
        HBox mainroot = new HBox(50); //全体まとめ用

        //説明用テキスト
        Text[] desText = { new Text("================"), new Text("・哲学者：大きい丸"), new Text("赤色：思考中"),
                new Text("青色：フォーク１本所持"), new Text("緑色：食事中"), new Text("灰色：食事終了"), new Text("================"),
                new Text("・フォーク:四角"), new Text("赤色：持たれている"), new Text("青色：置かれている"), new Text("================"),
                new Text("・食事:机の上の丸"), new Text("％：食事の残り") };
        //rootにセット
        for (int i = 0; i < desText.length; i++) {
            textroot.getChildren().add(desText[i]);
        }

        //ボタン設定
        Start_button.setPrefSize(100, 50);
        Start_button.setOnAction(event -> Start_button());
        textroot.getChildren().add(Start_button);
        Button End_button = new Button("End");
        End_button.setPrefSize(100, 50);
        End_button.setOnAction(event -> System.exit(0));
        textroot.getChildren().add(End_button);

        //時間設定スライダー設定,rootにセット(説明含め)
        stateroot.getChildren().add(new Text("待機時間(s)"));
        timeslider.setShowTickLabels(true);
        timeslider.setShowTickMarks(true);
        timeslider.setMajorTickUnit(1);
        timeslider.setMinorTickCount(1);
        timeslider.setSnapToTicks(true);
        stateroot.getChildren().add(timeslider);

        //食事回数設定スライダー設定,rootにセット(説明含め)
        stateroot.getChildren().add(new Text("=========="));
        stateroot.getChildren().add(new Text("食事回数"));
        foodslider.setShowTickLabels(true);
        foodslider.setShowTickMarks(true);
        foodslider.setMajorTickUnit(5);
        foodslider.setMinorTickCount(4);
        foodslider.setSnapToTicks(true);
        stateroot.getChildren().add(foodslider);

        //座標計算・管理
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
            name_texts[i].setLayoutX(x - 20);
            name_texts[i].setLayoutY(y - 30);
            fork_Rectangles[i].setLayoutX(tx * 0.55);
            fork_Rectangles[i].setLayoutY(ty * 0.55);
        }

        //テーブル作成・セット
        Circle tablCircle = new Circle(115, null);
        tablCircle.setStroke(Color.BROWN);
        tablCircle.setStrokeWidth(2.5);
        drowroot.getChildren().add(tablCircle);

        //図をセット
        //哲学者
        for (int i = 0; i < circles.length; i++) {
            circles[i].setFill(Color.CYAN);
            drowroot.getChildren().add(circles[i]);
        }
        //食事
        for (int i = 0; i < food_circles.length; i++) {
            food_circles[i].setFill(Color.ORANGE);
            food_circles[i].setStroke(Color.BLACK);
            drowroot.getChildren().add(food_circles[i]);
        }
        //フォーク
        for (int i = 0; i < fork_Rectangles.length; i++) {
            drowroot.getChildren().add(fork_Rectangles[i]);
        }
        //哲学者名前
        for (int i = 0; i < name_texts.length; i++) {
            drowroot.getChildren().add(name_texts[i]);
        }
        //食事残りパーセント
        for (int i = 0; i < name_texts.length; i++) {
            drowroot.getChildren().add(foodCountTexts[i]);
        }

        //状態
        stateroot.getChildren().add(new Text("=========="));
        stateroot.getChildren().add(new Text("現在の状態"));
        for (int i = 0; i < state_texts.length; i++) {
            stateroot.getChildren().add(state_texts[i]);
        }

        //全体まとめ
        mainroot.getChildren().addAll(textroot, drowroot, stateroot);
        //出力
        Scene scene = new Scene(mainroot);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * 状態表記用のテキストを書き換えるメソッド
     * 
     * @param id    哲学者のID
     * @param state 現在の状態を表記
     * @param name　対象(哲学者)の名前
     */
    public void ChangeState(int id, String state, String name) {
        switch (state) {
        case "Stop":
            state_texts[id].setText(name + ":ウェイターに止められた");
            break;

        case "Think":
            state_texts[id].setText(name + ":思考中");
            break;

        case "One":
            state_texts[id].setText(name + ":フォーク１本所持");
            break;

        case "Eat":
            state_texts[id].setText(name + ":食事中");
            break;

        case "End":
            state_texts[id].setText(name + ":食事終了");
            break;
        }
    }

    /**
     * 哲学者の上に表記される名前を設定するメソッド
     * 
     * @param　id 哲学者のID
     * @param name 哲学者の名前
     */
    public void SetName(int id, String name) {
        name_texts[id].setText(name);
    }

    /**
     * スライダーの値を読み取るメソッド
     * @param choice 読み取るスライダーの名称
     * @return Double型で返す
     * timeはmsで返すため、スライダーの値に1000をかけている
     * 存在しないスライダーを指した場合は1000.0を返す　
     */
    public Double GetSilder(String choice) {
        switch (choice) {
        case "time":
            return (timeslider.getValue() * 1000);
        case "food":
            return foodslider.getValue();
        }
        return 1000.0;
    }

    /**
    * 哲学者の円の色を変更するメソッド
    * 
    * @param id 哲学者のID
    * @param color 変更する色
    */
    public void ChangeP(int id, Color color) {
        circles[id].setFill(color);
    }

    /**
    * フォークの四角の色を変更するメソッド
    * 
    * @param id フォークのID
    * @param color 変更する色
    */
    public void ChangeFork(int id, Color color) {
        fork_Rectangles[id].setFill(color);
    }

    /**
     * 食事の残りのパーセントを計算して、表示するメソッド
     * テキストで残りパーセントを出力
     * 食事の透過度を変更する(1:不透過　0:完全に透過)
     * 
     * @param id 食事のID
     * @param　eat 現在の食事回数
     * @param　eatMax 指定された食事回数
     */
    public void ChangeFood(int id, Double eat, Double eatMax) {

        if (eatMax != 0) {
            foodCountTexts[id].setText("残り:" + ((eatMax - eat) / eatMax) * 100 + "%");
            food_circles[id].setOpacity((eatMax - eat) / eatMax);
        } else {
            foodCountTexts[id].setText("残り:" + 0.0 + "%");
            food_circles[id].setOpacity(0);
        }
    }

    /**
     * ボタンが押された時に起動するメソッド
     * ボタンを実行不可にしたのち、スレッドのための設定関数を起動する
     */
    public void Start_button() {
        Start_button.setDisable(true);
        FxTest.ThreadStart();
    }
}