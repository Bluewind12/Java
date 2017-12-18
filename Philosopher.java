//1601776 2EP4-58 MomoseNaoto

package FiveTest;

import java.io.PrintStream;
import javafx.scene.paint.Color;

/**
 * 哲学者の動きを管理する
 */
public class Philosopher extends GUI implements Runnable {
    int id;
    String Name;
    int UseLeftHand;
    int UseRightHand;
    int waittime = 1000;
    Fork forks[] = new Fork[5];
    Waiter Tablewaiter = new Waiter();
    Double eatMax = 5.0;
    Double eatcount = 0.0;

    Philosopher(int i) {
        this.id = i;
    }

    /**
     * 哲学者のフィールドを設定する
     * また、哲学者がどのフォークを取得するかを哲学者のIDから設定する
     * （左手：ID　右手：ID-1 とする）
     * 
     * @param name 哲学者の名前
     * @param obj すべてのフォークを認識させるために使用
     * @param waiter ウェイターを認識させるために使用
     */
    public void SetPhilosopher(String name, Fork[] obj, Waiter waiter) {
        Name = name;
        UseLeftHand = id;
        if (id != 0) {
            UseRightHand = id - 1;
        } else {
            UseRightHand = 4;
        }
        forks = obj;
        Tablewaiter = waiter;
        waittime = GetSilder("time").intValue() + (id * 10);
        eatMax = GetSilder("food");
        SetName(id, name);
    }

    /**
     * 食事を行う管理メソッド
     */
    public void Eaten() {
        //ウェイターに相談
        while (Tablewaiter.DoUse()) {
            try {
                //一時停止

                System.out.println("哲学者" + id + ":ウェイターに止められた");
                ChangeState(id, "Stop", Name);
                ChangeP(id, Color.BLACK);
                Thread.sleep(waittime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //左側取得
        System.out.println("哲学者" + id + ":フォーク一本目持ち上げ");
        forks[UseLeftHand].Forkup(id);
        Tablewaiter.UseForkCountUP();
        ChangeState(id, "One", Name);
        ChangeP(id, Color.BLUE);
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        //右側取得
        System.out.println("哲学者" + id + ":フォーク二本目持ち上げ");
        forks[UseRightHand].Forkup(id);
        ChangeP(id, Color.GREEN);
        ChangeState(id, "Eat", Name);
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("哲学者" + id + ":食事中");

        //フォークを置く
        forks[UseLeftHand].Forkdown(id);
        Tablewaiter.UseForkCountDown();
        forks[UseRightHand].Forkdown(id);
        eatcount++;
        ChangeP(id, Color.RED);
    }

    /**
     * 食事間の行う思考のためのメソッド
     */
    public void Thinking() {
        //食事間の思考
        try {
            System.out.println("哲学者" + id + "Thinkなう");
            ChangeState(id, "Think", Name);
            Thread.sleep(waittime);

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * 哲学者の行動を表すメソッド
     * スレッド実行される
     */
    public void run() {
        while ((eatMax - eatcount) > 0) {
            ChangeFood(id, eatcount, eatMax);
            Thinking();
            Eaten();
        }
        System.out.println("哲学者" + id + ":食事終了");
        ChangeFood(id, eatcount, eatMax);
        ChangeP(id, Color.GREY);
        ChangeState(id, "End", Name);
    }

}
