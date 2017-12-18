//1601776 2EP4-58 MomoseNaoto

package FiveTest;

/**
 * �����ݒ�,�X���b�h�̐���,���s���s��
 */
public class FxTest extends GUI {

    /**
     * �X���b�h���쐬���A���s���s�����\�b�h
     * �C���X�^���X�����������ōs���B
     */
    public static void ThreadStart() {
        //���L�I�u�W�F�N�g�쐬
        Fork fork[] = new Fork[5];
        Waiter waiter = new Waiter();
        Philosopher philosopher[] = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            fork[i] = new Fork(i);
            philosopher[i] = new Philosopher(i);
        }

        //�ݒ�
        //                          ���O   �t�H�[�N�@�E�F�C�^�[
        philosopher[0].SetPhilosopher("�h���o�[����", fork, waiter);
        philosopher[1].SetPhilosopher("�h���r�[����", fork, waiter);
        philosopher[2].SetPhilosopher("�h���u�[����", fork, waiter);
        philosopher[3].SetPhilosopher("�h���x�[����", fork, waiter);
        philosopher[4].SetPhilosopher("�h���{�[����", fork, waiter);
        //�X���b�h����
        Thread thread[] = new Thread[5];

        //�X���b�h���s
        for (int j = 0; j < thread.length; j++) {
            thread[j] = new Thread(philosopher[j]);
            thread[j].start();
        }
    }
}
