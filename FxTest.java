//1601776 2EP4-58 MomoseNaoto

package FiveTest;

public class FxTest extends GUI {
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
        philosopher[0].SetPhilosopher("Aさん", fork, waiter);
        philosopher[1].SetPhilosopher("Bさん", fork, waiter);
        philosopher[2].SetPhilosopher("Cさん", fork, waiter);
        philosopher[3].SetPhilosopher("Dさん", fork, waiter);
        philosopher[4].SetPhilosopher("Eさん", fork, waiter);
        //スレッド生成
        Thread thread[] = new Thread[5];

        //スレッド実行
        for (int j = 0; j < thread.length; j++) {
            thread[j] = new Thread(philosopher[j]);
            thread[j].start();
        }
    }
}
