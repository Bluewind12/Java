//1601776 2EP4-58 MomoseNaoto

package FiveTest;

/**
 * ��Ɏ��ꂽ�t�H�[�N�̐��𐔂��A�ꍇ�Ɏ���Ď擾���~�߂�(true��Ԃ�)
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
     * ���ݎ�Ɏ���Ă���t�H�[�N�̐����R�{�ȏ�ł���̂Ȃ�Ύ~�߂悤�Ƃ���
     * @return Boolean�^�@
     * �R�ȏ�ł���̂Ȃ�� True ����ȊO�� false ��Ԃ�
     */
    public boolean DoUse() {
        if (UseForks < 3) {
            return false;
        } else {
            return true;
        }
    }

}
