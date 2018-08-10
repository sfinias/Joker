package joker;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WinningTicket extends RandomTicket {

    private static Set<WinningTicket> winningTickets = new HashSet<>();
    private static int[] mainCount = new int[MAINNUMBERPOOL];
    private static int[] jokerCount = new int[JOKERUMBERPOOL];

    private static final int MAXCOUNT = 3;
    private static final int MINCOUNT = 3;


    public WinningTicket() {
        super(IDGen.getWinningId(), spinTheWheel(MAINNUMBERPOOL, MINPICKEDMAIN, mainCount), spinTheWheel(JOKERUMBERPOOL, MINPICKEDJOKER, jokerCount));
        System.out.println("New winning ticket created at " + this.getTime() + " with id:" + super.getId());
        System.out.println(this);
        winningTickets.add(this);
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("The winning numbers are ");
        for (int i : getMainNumbers()) {
            st.append(i).append(" ");
        }
        st.append("and the joker is ");
        for (int i : getJokerNumbers()) {
            st.append(i).append(" ");
        }
        return st.toString();
    }

    //Same as the overloaded method for picking random numbers,
    // it just also gathers data for each occurrence of the numbers of the different tickets
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

    //Shows the data we gathered about the highest and lowest occurrences from every winning ticket.
    //If we didn't create 20 winning tickets during the run, it automatically creates them
    public static void showData() {
        if (winningTickets.size() < 20) {
            System.out.println("Only " + winningTickets.size() + " winning tickets were created, creating " + (20 - winningTickets.size()) + " more");
            while (winningTickets.size() < 20) {
                winningTickets.add(new WinningTicket());
            }
        }
        int[] mainMaxOccurrences = new int[MAXCOUNT];
        int[] mainMinOccurrences = new int[MINCOUNT];
        int[] jokerMaxOccurrences = new int[MAXCOUNT];
        int[] jokerMinOccurrences = new int[MINCOUNT];
        updateData(mainCount, mainMaxOccurrences, mainMinOccurrences);
        updateData(jokerCount, jokerMaxOccurrences, jokerMinOccurrences);
        System.out.println("The " + MAXCOUNT + "  main numbers with the the most occurrences are: ");
        for (int number : mainMaxOccurrences) {
            System.out.println(number + " which appeared " + mainCount[number - 1] + " times");
        }
        System.out.println("The " + MINCOUNT + " main numbers with the the least occurrences are: ");
        for (int number : mainMinOccurrences) {
            System.out.println(number + " which appeared " + mainCount[number - 1] + " times");
        }
        System.out.println("The " + MAXCOUNT + " jokers with the the most occurrences are: ");
        for (int number : jokerMaxOccurrences) {
            System.out.println(number + " which appeared " + jokerCount[number - 1] + " times");
        }
        System.out.println("The " + MINCOUNT + " jokers with the the least occurrences are: ");
        for (int number : jokerMinOccurrences) {
            System.out.println(number + " which appeared " + jokerCount[number - 1] + " times");
        }
    }

    //This method goes through the count array which hold all the occurrences of each number
    //It searches for the highest and lowest numbers and puts them to arrayMax and arrayMin respectively
    private static void updateData(int[] count, int[] arrayMax, int[] arrayMin) {
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < arrayMax.length; j++) {
                int number = arrayMax[j];
                if (number == 0) {
                    arrayMax[j] = i + 1;
                    break;
                } else if (count[i] > count[number - 1]) {
                    add(i + 1, arrayMax, j);
                    break;
                }
            }
            for (int j = 0; j < arrayMin.length; j++) {
                int number = arrayMin[j];
                if (number == 0 && count[i] != 0) {
                    arrayMin[j] = i + 1;
                    break;
                } else if (count[i] != 0 && count[i] < count[number - 1]) {
                    add(i + 1, arrayMin, j);
                    break;
                }
            }
        }
    }

    //Method to add a number to the array and changing the order of the other entries
    private static void add(int n, int[] array, int index) {
        for (int i = index; i < array.length; i++) {
            int temp = array[i];
            array[i] = n;
            n = temp;
        }
    }
}