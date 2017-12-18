//1601776 2EP4-58 MomoseNaoto

package FiveTest;

/**
 * 手に取られたフォークの数を数え、場合に取って取得を止める(trueを返す)
 */
public class Waiter {
    int UseForks = 0;

    public void UseForkCountUP() {
        UseForks++;
    }

    public void UseForkCountDown() {
        UseForks--;
    }

    /**
     * 現在手に取られているフォークの数が３本以上であるのならば止めようとする
     * @return Boolean型　
     * ３以上であるのならば True それ以外は false を返す
     */
    public boolean DoUse() {
        if (UseForks < 3) {
            return false;
        } else {
            return true;
        }
    }

}
