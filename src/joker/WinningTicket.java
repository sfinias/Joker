package joker;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WinningTicket extends RandomTicket {

    public static Set<WinningTicket> winningTickets = new HashSet<>();
    private static int[] mainCount = new int[MAINNUMBERPOOL];
    private static int[] jokerCount = new int[JOKERUMBERPOOL];


    public WinningTicket() {
        super(IDGen.getWinningId(), spinTheWheel(MAINNUMBERPOOL, MINPICKEDMAIN, mainCount), spinTheWheel(JOKERUMBERPOOL, MINPICKEDJOKER, jokerCount));
        System.out.println(this);
        winningTickets.add(this);
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("New winning ticket with id:" + super.getId() + ". The winning numbers are ");
        for (int i : getMainNumbers()) {
            st.append(i).append(" ");
        }
        st.append("and the joker is ");
        for (int i : getJokerNumbers()) {
            st.append(i).append(" ");
        }
        return st.toString();
    }

    private static Set<Integer> spinTheWheel(int poolSize, int pickedNumbers, int[] count) {
        List<Integer> pool = new ArrayList<>();
        for (int i = 1; i <= poolSize; i++) {
            pool.add(i);
        }
        Set<Integer> set = new TreeSet<>();
        int size = poolSize;
        for (int i = 0; i < pickedNumbers; i++) {
            int n = (int) (Math.random() * size);
            int number = pool.get(n);
            set.add(number);
            pool.remove(n);
            count[number - 1]++;
            size--;
        }
        return set;
    }

    public static void showData() {
        if (winningTickets.size() < 20) {
            System.out.println("Only " + winningTickets.size() + " winning tickets were created, creating " + (20 - winningTickets.size()) + " more");
            while (winningTickets.size() < 20) {
                winningTickets.add(new WinningTicket());
            }
        }
        System.out.println("Data collected for main numbers:");
        for (int i = 0; i < mainCount.length; i++) {
            System.out.println((i + 1) + " found " + mainCount[i] + " times");
        }
        System.out.println("==============================");
        System.out.println("Data collected for jokers");
        for (int i = 0; i < jokerCount.length; i++) {
            System.out.println((i + 1) + " found " + jokerCount[i] + " times");
        }
        System.out.println("==============================");
    }
}
