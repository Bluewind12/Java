//1601776 2EP4-58 MomoseNaoto
package FiveTest;

import java.io.PrintStream;
import javafx.scene.paint.Color;

public class Fork extends GUI {
    int id;
    boolean Use = false;

    Fork(int i) {
        id = i;
        System.out.println((new StringBuilder()).append(i).append("Fork Set").toString());
        ChangeFork(id, Color.BLUE);
    }

    public synchronized void Forkup(int i) {
        while (Use) {
            try {
                wait();
            } catch (InterruptedException interruptedexception) {
                System.out.println(interruptedexception);
            }
        }
        Use = true;
        ChangeFork(id, Color.RED);
    }

    public synchronized void Forkdown() {
        Use = false;
        ChangeFork(id, Color.BLUE);
        notifyAll();
    }

}
