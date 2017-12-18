//1601776 2EP4-58 MomoseNaoto

package FiveTest;

/**
 * 初期設定,スレッドの生成,実行を行う
 */
public class FxTest extends GUI {

    /**
     * スレッドを作成し、実行を行うメソッド
     * インスタンス生成もここで行う。
     */
    public static void ThreadStart() {
        //共有オブジェクト作成
        Fork fork[] = new Fork[5];
        Waiter waiter = new Waiter();
        Philosopher philosopher[] = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            fork[i] = new Fork(i);
            philosopher[i] = new Philosopher(i);
        }

        //設定
        //                          名前   フォーク　ウェイター
        philosopher[0].SetPhilosopher("ドリバーくん", fork, waiter);
        philosopher[1].SetPhilosopher("ドリビーくん", fork, waiter);
        philosopher[2].SetPhilosopher("ドリブーくん", fork, waiter);
        philosopher[3].SetPhilosopher("ドリベーくん", fork, waiter);
        philosopher[4].SetPhilosopher("ドリボーくん", fork, waiter);
        //スレッド生成
        Thread thread[] = new Thread[5];

        //スレッド実行
        for (int j = 0; j < thread.length; j++) {
            thread[j] = new Thread(philosopher[j]);
            thread[j].start();
        }
    }
}
