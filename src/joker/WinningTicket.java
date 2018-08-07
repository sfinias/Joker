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
    private static int[] mainMaxOccurrences;
    private static int[] mainMinOccurrences;
    private static int[]  jokerMaxOccurrences;
    private static int[]  jokerMinOccurrences;

    public static final int MAXCOUNT=3;
    public static final int MINCOUNT=3;

//    static {
//        for (int i = 0; i< mainMinOccurrences.length; i++) mainMinOccurrences[i]=Integer.MAX_VALUE;
//    }



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
        mainMaxOccurrences = new int[MAXCOUNT];
        mainMinOccurrences=new int[MINCOUNT];
        jokerMaxOccurrences=new int[MAXCOUNT];
        jokerMinOccurrences=new int[MINCOUNT];
        updateData(mainCount,mainMaxOccurrences,mainMinOccurrences);
        updateData(jokerCount,jokerMaxOccurrences,jokerMinOccurrences);

//        for (int i=0;i<mainCount.length;i++){
//            for (int j = 0; j< mainMaxOccurrences.length; j++){
//                int number = mainMaxOccurrences[j];
//                if (number==0) {
//                    mainMaxOccurrences[j] = i + 1;
//                    break;
//                }else if (mainCount[i]>mainCount[number-1] ) {
//                    add(i+1, mainMaxOccurrences, j);
////                    mainMaxOccurrences[j] = i + 1;
//                    break;
//                }
//            }
//            for (int j = 0; j< mainMinOccurrences.length; j++){
//                int number = mainMinOccurrences[j];
//                if (number==0 && mainCount[i]!=0) {
//                    mainMinOccurrences[j] = i + 1;
//                    break;
//                }else if (mainCount[i]!=0 && mainCount[i]<mainCount[number-1] ) {
//                    add(i+1, mainMinOccurrences, j);
////                    mainMinOccurrences[j] = i + 1;
//                    break;
//                }
//            }
//        }
//        for (int i=0;i<mainCount.length;i++){
//            for (int j=0;j<mainMinOccurrences.length;j++){
//                int number = mainMinOccurrences[j];
//                if (mainCount[i]!=0 || mainCount[i]<mainCount[number-1]){
//                    mainMinOccurrences[j]=i+1;
//                    break;
//                }
//            }
//        }
        int number;
        System.out.println("The "+MAXCOUNT+"  main numbers with the the most occurrences are: ");
        for(int i = 0; i< mainMaxOccurrences.length; i++){
            number=mainMaxOccurrences[i];
            System.out.println(number+" which appeared "+mainCount[number-1]+" times");
        }
        System.out.println("The "+MINCOUNT+" main numbers with the the least occurrences are: ");
        for(int i=0;i<mainMinOccurrences.length;i++){
            number=mainMinOccurrences[i];
            System.out.println(number+" which appeared "+mainCount[number-1]+" times");
        }
        System.out.println("The "+MAXCOUNT+" jokers with the the most occurrences are: ");
        for(int i=0;i<jokerMaxOccurrences.length;i++){
            number=jokerMaxOccurrences[i];
            System.out.println(number+" which appeared "+jokerCount[number-1]+" times");
        }
        System.out.println("The "+MINCOUNT+" jokers with the the least occurrences are: ");
        for(int i=0;i<jokerMinOccurrences.length;i++){
            number=jokerMinOccurrences[i];
            System.out.println(number+" which appeared "+jokerCount[number-1]+" times");
        }
    }

    private static void updateData(int[] count, int[] arrayMax, int[] arrayMin ) {
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

    private static void add(int n, int[] array, int index){
        for (int i=index;i<array.length;i++){
            int temp = array[i];
            array[i]=n;
            n=temp;
        }
    }
}
