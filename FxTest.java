//1601776 2EP4-58 MomoseNaoto

package FiveTest;

public class FxTest extends GUI {
    public static void ThreadStart() {
        Fork fork[] = new Fork[5];
        Waiter waiter = new Waiter();
        Philosopher philosopher[] = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            fork[i] = new Fork(i);
            philosopher[i] = new Philosopher(i);
        }

        philosopher[0].SetPhilosopher("A", fork, waiter);
        philosopher[1].SetPhilosopher("B", fork, waiter);
        philosopher[2].SetPhilosopher("C", fork, waiter);
        philosopher[3].SetPhilosopher("D", fork, waiter);
        philosopher[4].SetPhilosopher("E", fork, waiter);
        Thread thread[] = new Thread[5];

        for (int j = 0; j < thread.length; j++) {
            thread[j] = new Thread(philosopher[j]);
            thread[j].start();
        }
    }
}
