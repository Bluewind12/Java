//1601776 2EP4-58 MomoseNaoto
package FiveTest;

public class Waiter {
    int UseForks = 0;

    public void UseForkCountUP() {
        UseForks++;
    }

    public void UseForkCountDown() {
        UseForks--;
    }

    public boolean DoUse() {
        if (UseForks < 3) {
            return false;
        } else {
            return true;
        }
    }

}
