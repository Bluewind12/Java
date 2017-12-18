//1601776 2EP4-58 MomoseNaoto

package FiveTest;

import java.io.PrintStream;
import javafx.scene.paint.Color;

/**
 * �t�H�[�N�����݂ǂ̏�Ԃɂ��邩�𔻕ʂ���
 */
public class Fork extends GUI {
    int id;
    boolean Use = false;//���̏�:True �擾����Ă���:false

    Fork(int i) {
        id = i;
        ChangeFork(id, Color.BLUE);
    }

    /**
     * �����グ��ꂽ���̓�����������\�b�h
     * 
     * �����グ�邱�Ƃ��ł����ꍇ�̓t�B�[���h�� Use �� True �ɂ���
     * �����グ���Ȃ������ꍇ��wait����
     * 
     * @param i �ǂ̓N�w�҂��ێ����Ă��邩������ID
     * CUI�Ǘ����Ɏg�p�����
     */
    public synchronized void Forkup(int i) {
        //���ォ�̔���
        while (Use) {
            try {
                wait();
            } catch (InterruptedException interruptedexception) {
                System.out.println(interruptedexception);
            }
        }
        Use = true;
        System.out.println("�N�w��" + i + "�������グ��:�t�H�[�Nid" + id);
        ChangeFork(id, Color.RED);
    }

    /**
     * �����グ��ꂽ���̓�����������\�b�h
     * 
     * �t�B�[���h�� Use �� false �ɂ���
     */
    public synchronized void Forkdown() {

        System.out.println("�u��:�t�H�[�Nid" + id);
        Use = false;
        ChangeFork(id, Color.BLUE);
        notifyAll();
    }

}
