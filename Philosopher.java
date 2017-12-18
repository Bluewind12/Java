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

    /**
     * �N�w�҂̃t�B�[���h��ݒ肷��
     * �܂��A�N�w�҂��ǂ̃t�H�[�N���擾���邩��N�w�҂�ID����ݒ肷��
     * �i����FID�@�E��FID-1 �Ƃ���j
     * 
     * @param name �N�w�҂̖��O
     * @param obj ���ׂẴt�H�[�N��F�������邽�߂Ɏg�p
     * @param waiter �E�F�C�^�[��F�������邽�߂Ɏg�p
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
     * �H�����s���Ǘ����\�b�h
     */
    public void Eaten() {
        //�E�F�C�^�[�ɑ��k
        while (Tablewaiter.DoUse()) {
            try {
                //�ꎞ��~

                System.out.println("�N�w��" + id + ":�E�F�C�^�[�Ɏ~�߂�ꂽ");
                ChangeState(id, "Stop", Name);
                ChangeP(id, Color.BLACK);
                Thread.sleep(waittime);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //�����擾
        System.out.println("�N�w��" + id + ":�t�H�[�N��{�ڎ����グ");
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
        System.out.println("�N�w��" + id + ":�t�H�[�N��{�ڎ����グ");
        forks[UseRightHand].Forkup(id);
        ChangeP(id, Color.GREEN);
        ChangeState(id, "Eat", Name);
        try {
            Thread.sleep(waittime);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("�N�w��" + id + ":�H����");

        //�t�H�[�N��u��
        forks[UseLeftHand].Forkdown(id);
        Tablewaiter.UseForkCountDown();
        forks[UseRightHand].Forkdown(id);
        eatcount++;
        ChangeP(id, Color.RED);
    }

    /**
     * �H���Ԃ̍s���v�l�̂��߂̃��\�b�h
     */
    public void Thinking() {
        //�H���Ԃ̎v�l
        try {
            System.out.println("�N�w��" + id + "Think�Ȃ�");
            ChangeState(id, "Think", Name);
            Thread.sleep(waittime);

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * �N�w�҂̍s����\�����\�b�h
     * �X���b�h���s�����
     */
    public void run() {
        while ((eatMax - eatcount) > 0) {
            ChangeFood(id, eatcount, eatMax);
            Thinking();
            Eaten();
        }
        System.out.println("�N�w��" + id + ":�H���I��");
        ChangeFood(id, eatcount, eatMax);
        ChangeP(id, Color.GREY);
        ChangeState(id, "End", Name);
    }

}
