//1601776 2EP4-58 MomoseNaoto

package FiveTest;

import java.io.PrintStream;
import javafx.scene.paint.Color;

/**
 * �N�w�҂̓������Ǘ�����
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

    //���O,ID�Ȃ� set
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
        //�E�F�C�^�[�ɑ��k
        while (Tablewaiter.DoUse()) {
            try {
                //�ꎞ��~
                System.out.println(Name + "Stop");
                ChangeState(id, "Stop", Name);
                ChangeP(id, Color.BLACK);
                Thread.sleep(waittime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //�����擾
        forks[UseLeftHand].Forkup(id);
        Tablewaiter.UseForkCountUP();
        ChangeState(id, "One", Name);
        ChangeP(id, Color.BLUE);
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        //�E���擾
        forks[UseRightHand].Forkup(id);
        ChangeP(id, Color.GREEN);
        ChangeState(id, "Eat", Name);
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(Name + "Eat�Ȃ�");

        //�t�H�[�N��u��
        forks[UseLeftHand].Forkdown();
        Tablewaiter.UseForkCountDown();
        forks[UseRightHand].Forkdown();
        eatcount++;
        ChangeP(id, Color.RED);
    }

    public void Thinking() {
        //�H���Ԃ̎v�l
        try {
            System.out.println(Name + "Think�Ȃ�");
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
