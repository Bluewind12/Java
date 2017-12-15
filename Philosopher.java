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

    //名前,IDなど set
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

    public void Eaten() {
        //ウェイターに相談
        while (Tablewaiter.DoUse()) {
            try {
                //一時停止
                System.out.println(Name + "Stop");
                ChangeState(id, "Stop", Name);
                ChangeP(id, Color.BLACK);
                Thread.sleep(waittime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //左側取得
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
        forks[UseRightHand].Forkup(id);
        ChangeP(id, Color.GREEN);
        ChangeState(id, "Eat", Name);
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(Name + "Eatなう");

        //フォークを置く
        forks[UseLeftHand].Forkdown();
        Tablewaiter.UseForkCountDown();
        forks[UseRightHand].Forkdown();
        eatcount++;
        ChangeP(id, Color.RED);
    }

    public void Thinking() {
        //食事間の思考
        try {
            System.out.println(Name + "Thinkなう");
            ChangeState(id, "Think", Name);
            Thread.sleep(waittime);

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void run() {
        while ((eatMax - eatcount) > 0) {
            ChangeFood(id, eatcount, eatMax);
            Thinking();
            Eaten();
        }
        ChangeFood(id, eatcount, eatMax);
        ChangeP(id, Color.GREY);
        ChangeState(id, "End", Name);
    }

}
