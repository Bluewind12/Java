//1601776 2EP4-58 MomoseNaoto

package FiveTest;

import java.io.PrintStream;
import javafx.scene.paint.Color;

/**
 * フォークが現在どの状態にあるかを判別する
 */
public class Fork extends GUI {
    int id;
    boolean Use = false;//机の上:True 取得されている:false

    Fork(int i) {
        id = i;
        ChangeFork(id, Color.BLUE);
    }

    /**
     * 持ち上げられた時の動作を示すメソッド
     * 
     * 持ち上げることができた場合はフィールドの Use を True にする
     * 持ち上げられなかった場合はwaitする
     * 
     * @param i どの哲学者が保持しているかを示すID
     * CUI管理時に使用される
     */
    public synchronized void Forkup(int i) {
        //机上かの判別
        while (Use) {
            try {
                wait();
            } catch (InterruptedException interruptedexception) {
                System.out.println(interruptedexception);
            }
        }
        Use = true;
        System.out.println("哲学者" + i + "が持ち上げる:フォークid" + id);
        ChangeFork(id, Color.RED);
    }

    /**
     * 持ち上げられた時の動作を示すメソッド
     * 
     * フィールドの Use を false にする
     */
    public synchronized void Forkdown() {

        System.out.println("置く:フォークid" + id);
        Use = false;
        ChangeFork(id, Color.BLUE);
        notifyAll();
    }

}
